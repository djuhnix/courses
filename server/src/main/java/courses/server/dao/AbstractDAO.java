package courses.server.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import courses.server.manager.DefaultEntityManager;

import java.util.List;

/**
 * Must be implemented by all DAO
 */
public abstract class AbstractDAO<T> {

    protected EntityManager em;

    protected AbstractDAO() {
        em = DefaultEntityManager.getInstance().getEntityManager();
    }

    public abstract List<T> findAll();
    public abstract T findById(int id);
    //public abstract T create(T entityObject);

    /**
     * Find one occurrence of an entity in the database
     * @param id id of the entity
     * @param resultClass entity class
     * @return a single result from the database
     */
    public T findById(int id, Class<T> resultClass) {
        String sql = "SELECT t FROM " + resultClass.getSimpleName() + " t" +
                "WHERE t.id = :id";
        TypedQuery<T> query = em.createQuery(sql, resultClass);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    /**
     * Find all objects in database
     * @param resultClass the entity class
     * @return a list of object.
     */
    public List<T> findAll(Class<T> resultClass) {
        String sql = "SELECT t FROM " + resultClass.getSimpleName() + " t";
        TypedQuery<T> query = em.createQuery(sql, resultClass);

        return query.getResultList();
    }

    public void save(T entityObject) {
        em.getTransaction().begin();
        em.persist(entityObject);
        em.flush();
        em.getTransaction().commit();
    }

    public T update(T entityObject) {
        em.getTransaction().begin();
        T ret = em.merge(entityObject);
        em.flush();
        em.getTransaction().commit();
        return ret;
    }

    public void delete(T entityObject) {
        em.getTransaction().begin();
        em.remove(entityObject);
        em.flush();
        em.getTransaction().commit();
    }
}
