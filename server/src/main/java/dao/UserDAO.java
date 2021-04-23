package dao;

import entities.User;

import java.util.List;

public class UserDAO extends AbstractDAO<User> {

    @Override
    public List<User> findAll() {
        return this.findAll(User.class);
    }

    @Override
    public User findById(int id) {
        return this.findById(id, User.class);
    }
}
