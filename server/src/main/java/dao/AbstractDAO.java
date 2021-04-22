package dao;

import jakarta.persistence.EntityManager;
import manager.DefaultEntityManager;

import java.util.List;

/**
 * Must be implemented by all DAO
 */
public abstract class AbstractDAO<T> {

    private EntityManager em;
    protected AbstractDAO() {
        em = DefaultEntityManager.getInstance().getEntityManager();
    }

    public abstract List<T> findAll();
    public abstract T findById(int id);

    //findBy
}
