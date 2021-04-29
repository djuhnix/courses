package courses.server.tcp;

import courses.server.controllers.AbstractController;
import courses.server.controllers.UserController;
import courses.server.entities.*;
import courses.utils.DataExchange;
import courses.utils.DataTypeEnum;
import courses.utils.DefaultData;
import courses.utils.JsonUtils;
import org.apache.shiro.subject.Subject;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

class ServiceThread extends Thread {

    private final Socket socketOfServer;
    private Map<DataTypeEnum, AbstractController<?>> controllers;

    public ServiceThread(Socket socketOfServer, int clientNumber) {
        this.socketOfServer = socketOfServer;

        this.registerControllers();

        // Log
        log("New connection with client# " + clientNumber + " at " + socketOfServer);
    }
    private void registerControllers() {
        for (DataTypeEnum value : DataTypeEnum.values()) {
            switch (value) {
                case USER -> {
                    this.controllers.put(value, new UserController());
                }
                default -> this.controllers.put(value, null);
            }
        }
    }

    @Override
    public void run() {

        try {
            Subject user;
            // init data exchange and response
            DataExchange exchange = new DataExchange(socketOfServer);
            DefaultData<Object> response = new DefaultData<>();

            final DefaultData<?> data = exchange.getData();
            final DataTypeEnum dataType = data.getDataType();

            if (controllers.containsKey(dataType)
                    && controllers.get(dataType) != null) {
                AbstractController<?> controller = controllers.get(dataType);

                // checking identity if the connection
                if(!data.isTokenSet() && data.isAuthSet()) {
                    user = controller.logUser(data.getLogin(), data.getMdp());
                } else {
                    throw new IllegalArgumentException("Login information not set");
                }

                if (user.isAuthenticated()) {
                    // execute controller method on action
                    switch (data.getAction()) {
                        case READ -> response.setObject(
                                controller.read(
                                        (int) data.getObject()
                                )
                        );
                        case POST -> response.setObject(
                                controller.post(
                                        data
                                )
                        );
                        case UPDATE -> response.setObject(
                                controller.update(
                                        data
                                )
                        );
                        case DELETE -> controller.delete((int) data.getObject());
                        default -> {
                            log("Action not found : " + data.getAction() + ", stopping...");
                            System.exit(0);
                        }
                    }
                } else {
                    log("User login failed, stopping...");
                    System.exit(0);
                }

                exchange.setData(response);
                exchange.send();
            } else {
                throw new IllegalArgumentException("Controller not initialise : " + dataType);
            }

        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

    private void log(String message) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, message);
    }
}
