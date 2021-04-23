package manager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DefaultEntityManager {
    private static DefaultEntityManager instance;
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private final EntityManager em;

    private DefaultEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = entityManagerFactory.createEntityManager();
    }

    public static synchronized DefaultEntityManager getInstance() {
        if (instance == null) {
            instance = new DefaultEntityManager();
        }

        return instance;
    }

    public EntityManager getEntityManager() {
        return em;
    }
}
