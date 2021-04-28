package courses.server.manager;

import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.spi.LoginModule;
import java.util.HashMap;

/**
 * @deprecated
 */
public class LoginConfigurationManager {
    private static LoginConfigurationManager instance;
    private final Configuration config;

    private LoginConfigurationManager(Class<LoginModule> loginModuleClass) {
        config = new Configuration() {
            @Override
            public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
                return new AppConfigurationEntry[]{
                        new AppConfigurationEntry(loginModuleClass.getName(),
                                AppConfigurationEntry.LoginModuleControlFlag.REQUIRED,
                                new HashMap<>())
                };
            }
        };
    }

    public static LoginConfigurationManager getInstance(Class<LoginModule> loginModuleClass) {
        if (instance == null) {
            instance = new LoginConfigurationManager(loginModuleClass);
        }

        return instance;
    }

    public Configuration getConfig() {
        return config;
    }
}
