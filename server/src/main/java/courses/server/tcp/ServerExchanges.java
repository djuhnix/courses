package courses.server.tcp;

import org.apache.shiro.SecurityUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExchanges {

    public static void start(int port) throws IOException {

        ServerSocket listener = null;

        System.out.println("Listening on port "  + port);
        System.out.println("Server is waiting to accept user...");
        int clientNumber = 0;

        // Try to open a server socket on port 7777
        // Note that we can't choose a port less than 1023 if we are not
        // privileged users (root)

        try {
            listener = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            Socket socketOfServer;
            do {
                // Accept client connection request
                // Get new Socket at Server.

                socketOfServer = listener.accept();
                new ServiceThread(socketOfServer, clientNumber++).start();
            } while (socketOfServer.isConnected());
        } finally {
            /*
            if (SecurityUtils.getSecurityManager() != null && SecurityUtils.getSubject().isAuthenticated()) {
                SecurityUtils.getSubject().logout();
            }*/
            listener.close();
        }

    }
}
