package courses.server.manager;

import courses.server.dao.UserRealmDAO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;

import javax.annotation.PostConstruct;

public class DefaultSecurityManager {
    private static DefaultSecurityManager instance;
    private static final String SHIRO_INI = "classpath:shiro.ini";
    private final SecurityManager securityManager;

    private DefaultSecurityManager() {
        Realm realm = new UserRealmDAO();
        securityManager = new org.apache.shiro.mgt.DefaultSecurityManager(realm);
    }

    @PostConstruct
    public void initSecurityUtils() {
        SecurityUtils.setSecurityManager(securityManager);
    }

    public static synchronized DefaultSecurityManager getInstance() {
        if (instance == null) {
            instance = new DefaultSecurityManager();
        }

        return instance;
    }

    public SecurityManager getSecurityManager() {
        return securityManager;
    }
}
