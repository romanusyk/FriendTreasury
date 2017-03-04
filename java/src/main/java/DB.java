import domain.PaymentDTO;
import domain.User;
import service.PaymentService;
import service.SimplePaymentService;
import service.SimpleUserService;
import service.UserService;

import javax.jws.soap.SOAPBinding;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Roman Usik
 */
public class DB {

//    private static final SessionFactory sessionFactory;
//
//    static {
//        try {
//            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
//                    .configure( "hibernate.cfg.xml" )
//                    .build();
//
//            Metadata metadata = new MetadataSources( standardRegistry )
//                    .getMetadataBuilder()
//                    .build();
//
//            sessionFactory = metadata.getSessionFactoryBuilder().build();
//        } catch (Throwable ex) {
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static Session getSession() throws HibernateException {
//        return sessionFactory.openSession();
//    }

    /*public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        User u1 = null;
        User u2 = null;
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            List users = session.createQuery("FROM User").list();
            if (users.isEmpty()) {
                System.out.println("Adding users");
                u1 = new User("roma");
                u1.setId((Integer)session.save(u1));
                u2 = new User("vova");
                u2.setId((Integer)session.save(u2));
            }
            else {
                u1 = (User) users.get(0);
                u2 = (User) users.get(1);
            }
            Payment payment = new Payment(u1, u2, new BigDecimal(100.10));
            payment.setId((Integer)session.save(payment));
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tr != null)
                tr.rollback();
        } finally {
            session.close();
        }
        System.out.println("id : " + u1.getId() + ", name : \"" + u1.getUsername() + "\"");
        System.out.println("id : " + u2.getId() + ", name : \"" + u2.getUsername() + "\"");
    }*/

}
