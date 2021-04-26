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
        String sql = "SELECT * FROM " + User.class.getSimpleName() + " " +
                "WHERE email = " + email;
        TypedQuery<User> query = em.createQuery(sql, User.class);

        return query.getSingleResult();
    }
}
