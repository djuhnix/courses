package courses.server.dao;

import courses.server.entities.User;
import jakarta.persistence.TypedQuery;

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

    public User findByEmail(String email) {
        String sql = "SELECT t FROM " + User.class.getSimpleName() + " t " +
                "WHERE t.email = :email";
        TypedQuery<User> query = em.createQuery(sql, User.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    public String findUserSaltByEmail(String email) {
        return this.findByEmail(email).getSalt();
    }

    public String findUserPasswordByEmail(String email) {
        return this.findByEmail(email).getPasswordHash();
    }
}
