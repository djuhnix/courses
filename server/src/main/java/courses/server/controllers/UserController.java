package courses.server.controllers;

import courses.server.dao.UserDAO;
import courses.server.entities.User;
import courses.server.security.Password;
import courses.utils.DefaultData;
import jakarta.persistence.NoResultException;

import java.util.List;

public class UserController extends AbstractController<User> {

    public UserController() {
        super(new UserDAO());
    }

    @Override
    public List<User> read() throws IllegalAccessException {
        if (isUserAdmin()) {
            return super.read();
        }
        throw new IllegalAccessException("Unable to read data : lacks of permission");
    }

    @Override
    public User read(int id) {
        User result = null;
        if (isUserAdmin()) {
            try {
                result = dao.findById(id);
            } catch (NoResultException ignored) {
            }
        }
        return result;
    }

    @Override
    public User update(DefaultData<?> object) {
        User updatedUser = (User) object.getObject();
        if (isUserAdmin()) {
            try {
                User actual = dao.findById(updatedUser.getId());
                if (!actual.equals(updatedUser)) {
                    updatedUser = dao.update(updatedUser);
                }
            } catch (NoResultException ignored) {
                updatedUser = null;
            }
        }
        return updatedUser;
    }

    @Override
    public int post(DefaultData<?> object) {
        User user = (User) object.getObject();
        int id = 0;
        if (isUserAdmin()) {
            Password.saveHashedPassword(user, user.getPassword());
            dao.save(user);
            id = user.getId();
        }
        return id;
    }
}
