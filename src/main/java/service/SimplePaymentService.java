package service;

import domain.Payment;
import domain.PaymentDTO;
import domain.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.DAO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by romm on 01.02.17.
 */
@Service("paymentService")
public class SimplePaymentService implements PaymentService {

    @Autowired
    private DAO<Payment, Integer> paymentDAO;

    @Autowired
    private DAO<User, Integer> userDAO;

    private static final Logger logger = Logger.getLogger(SimplePaymentService.class);

    @Override
    @Transactional
    public void init() {
        makeGroupPayment(new PaymentDTO(1, new Integer[]{2, 3, 4}, new BigDecimal(200)));
        makeGroupPayment(new PaymentDTO(2, new Integer[]{3, 4}, new BigDecimal(300)));
    }

    @Override
    public boolean makePayment(User from, User to, BigDecimal amount) {
        Payment payment = new Payment(from, to, amount);
        Integer paymentID = paymentDAO.create(payment);
        return paymentID != null;
    }

    @Override
    @Transactional
    public boolean makeGroupPayment(PaymentDTO paymentDTO) {
        User userFrom = userDAO.get(paymentDTO.getUserFrom());
        BigDecimal amountPerUser = paymentDTO.getAmount().divide(new BigDecimal(paymentDTO.getUsersTo().length + paymentDTO.getShallIPayForMyself()), 2, BigDecimal.ROUND_CEILING);
        boolean success = true;
        for (Integer userToID : paymentDTO.getUsersTo()) {
            User userTo = userDAO.get(userToID);
            success &= makePayment(userFrom, userTo, amountPerUser);
        }
        return success;
    }

    @Transactional(readOnly = true)
    public Map<Integer, BigDecimal> getUserPayments(Integer userID) {
        User u = userDAO.get(userID);
        if (u == null) {
            logger.error("User with id = " + userID + " not found!");
            return null;
        }

        Map<Integer, BigDecimal> userDebts = new HashMap<>();

        Session session = paymentDAO.getSession();
        Query<Payment> query = session.createQuery(
                "from " + paymentDAO.getEntityClass().getSimpleName() + " t where t.userTo.id = :user_id",
                paymentDAO.getEntityClass()
        );
        query.setParameter("user_id", u.getId());
        List<Payment> payments = query.list();
        for (Payment payment : payments) {
            BigDecimal value = userDebts.get(payment.getUserFrom().getId());
            value = value == null ? new BigDecimal(0) : value;
            value = value.add(payment.getAmount());
            userDebts.put(payment.getUserFrom().getId(), value);
        }

        query = session.createQuery(
                "from " + paymentDAO.getEntityClass().getSimpleName() + " t where t.userFrom.id = :user_id",
                paymentDAO.getEntityClass()
        );
        query.setParameter("user_id", u.getId());
        payments = query.list();
        for (Payment payment : payments) {
            BigDecimal value = userDebts.get(payment.getUserTo().getId());
            value = value == null ? new BigDecimal(0) : value;
            value = value.subtract(payment.getAmount());
            userDebts.put(payment.getUserTo().getId(), value);
        }
        return userDebts;
    }
}
