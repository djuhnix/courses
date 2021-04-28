package courses.server.security;

import courses.server.dao.UserDAO;

import javax.security.auth.callback.*;
import java.io.Console;
import java.io.IOException;

/**
 * @deprecated
 */
public class LoginCallbackHandler implements CallbackHandler {

    private final UserDAO userDAO;

    public LoginCallbackHandler() {
        userDAO = new UserDAO();
    }

    /**
     * Invoke an array of Callbacks.
     *
     * <p>
     *
     * @param callbacks an array of <code>Callback</code> objects which contain
     *                  the information requested by an underlying security
     *                  service to be retrieved or displayed.
     *
     * @exception java.io.IOException if an input or output error occurs. <p>
     *
     * @exception UnsupportedCallbackException if the implementation of this
     *                  method does not support one or more of the Callbacks
     *                  specified in the <code>callbacks</code> parameter.
     */
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            Console console;
            if (callback instanceof NameCallback) {
                NameCallback nameCallback = (NameCallback) callback;
                //nameCallback.setName(console.readLine(nameCallback.getPrompt()));
            } else if (callback instanceof PasswordCallback) {
                PasswordCallback passwordCallback = (PasswordCallback) callback;
                //passwordCallback.setPassword(console.readPassword(passwordCallback.getPrompt()));
            } else {
                throw new UnsupportedCallbackException(callback);
            }
        }
    }
}
