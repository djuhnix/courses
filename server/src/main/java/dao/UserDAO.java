package dao;

import entities.User;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {

    @Override
    public List<User> findAll() {
        /* TODO
        String sql = "SELECT e FROM User e ";
        Query query = em.createQuery(sql);
        List<User> users = query.getResultList();
        */
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }
}
