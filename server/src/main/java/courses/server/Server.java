package courses.server;

//import courses.utils.security;
import courses.server.controllers.UserController;
import courses.server.dao.UserDAO;
import courses.server.entities.User;
import courses.server.security.Password;
import courses.server.security.RolesEnum;
import courses.server.tcp.ServerExchanges;
import org.apache.shiro.subject.Subject;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    /**
     *
     * @param args first arg is the port
     */
    public static void main(String[] args) {
        try {
            ServerExchanges.start(Integer.parseInt(args[0]));
        } catch (IOException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, "An error occurred, server stop", e);
            e.printStackTrace();
        }
    }
}
