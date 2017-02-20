package service;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import repository.DAO;
import repository.UserDAO;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by romm on 01.02.17.
 */
@Service("userService")
public class SimpleUserService implements UserService {

    @Autowired
    private DAO<User, Integer> userDAO;

    @Override
    public void init() {
        userDAO.create(new User("Roma"));
        userDAO.create(new User("Vova"));
        userDAO.create(new User("Jura"));
        userDAO.create(new User("Geka"));
    }

    @Override
    public List<User> initTreasury(List<String> usernames) {
        List<User> users = new LinkedList<>();
        for (String username : usernames) {
            User u = new User(username);
            u.setId(userDAO.create(u));
            users.add(u);
        }
        return users;
    }

    @Override
    public User getUserByID(Integer id) {
        return userDAO.get(id);
    }

    @Override
    @Transactional
    public void createUser(User user) {
        user.setId(userDAO.create(user));
    }

}
