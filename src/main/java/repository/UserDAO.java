package repository;

import domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by romm on 01.02.17.
 */
@Repository("userDAO")
public class UserDAO extends AbstractDAO<User, Integer> {

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }
}
