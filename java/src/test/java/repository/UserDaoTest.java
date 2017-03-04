package repository;

import domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by romm on 01.02.17.
 */
public class UserDaoTest {

    private UserDAO userDAO;

    @Before
    public void init() {
        userDAO = new UserDAO();
    }

    @Test
    public void testGetAll() {
        List<User> before = userDAO.getAll();
        User newUser = new User("TestUser");
        newUser.setId(userDAO.create(newUser));
        List<User> after = userDAO.getAll();
        Assert.assertEquals(before.size() + 1, after.size());
        Assert.assertFalse(before.contains(newUser));
        Assert.assertTrue(after.contains(newUser));
        userDAO.delete(newUser);
    }

    @Test
    public void getUserByID() {
        User newUser = new User("TestUser");
        newUser.setId(userDAO.create(newUser));
        User u = userDAO.get(newUser.getId());
        Assert.assertEquals(newUser, u);
        userDAO.delete(newUser);
    }

    @Test
    public void testCreate() {
        User newUser = new User("TestUser");
        newUser.setId(userDAO.create(newUser));
        Assert.assertNotNull(newUser.getId());
        userDAO.delete(newUser);
    }

    @Test
    public void updateUser() {
        User newUser = new User("TestUser");
        newUser.setId(userDAO.create(newUser));
        newUser.setUsername("Changed");
        userDAO.update(newUser);
        User u = userDAO.get(newUser.getId());
        Assert.assertEquals(u.getUsername(), "Changed");
        userDAO.delete(u);
    }

    @Test
    public void testDelete() {
        User newUser = new User("TestUser");
        newUser.setId(userDAO.create(newUser));
        Assert.assertNotNull(newUser.getId());
        userDAO.delete(newUser);
        Assert.assertNull(userDAO.get(newUser.getId()));
    }

}
