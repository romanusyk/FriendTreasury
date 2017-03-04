package repository;

import domain.Payment;
import org.springframework.stereotype.Repository;

/**
 * Created by romm on 01.02.17.
 */
@Repository("paymentDAO")
public class PaymentDAO extends AbstractDAO<Payment, Integer> {

    @Override
    public Class<Payment> getEntityClass() {
        return Payment.class;
    }
}
