package courses.server.tcp;

import courses.server.controllers.AbstractController;
import courses.server.controllers.ExerciseController;
import courses.server.controllers.StudentController;
import courses.server.controllers.UserController;
import courses.utils.DataExchange;
import courses.utils.DataTypeEnum;
import courses.utils.DefaultData;
import org.apache.shiro.subject.Subject;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

class ServiceThread extends Thread {

    private final Socket socketOfServer;
    private final Map<DataTypeEnum, AbstractController<?>> controllers;

    public ServiceThread(Socket socketOfServer, int clientNumber) {
        this.socketOfServer = socketOfServer;
        this.controllers = new HashMap<>();

        this.registerControllers();

        // Log
        log("New connection with client# " + clientNumber + " at " + socketOfServer);
    }
    private void registerControllers() {
        for (DataTypeEnum value : DataTypeEnum.values()) {
            switch (value) {
                case USER -> this.controllers.put(value, new UserController());
                case STUDENT -> this.controllers.put(value, new StudentController());
                case EXERCISE -> this.controllers.put(value, new ExerciseController());
                default -> this.controllers.put(value, null);
            }
        }
    }

    @Override
    public void run() {
        Subject user;
        // init data exchange and response
        DataExchange exchange = new DataExchange(socketOfServer, true);
        DefaultData<Object> response = new DefaultData<>();

        final DefaultData<?> data = exchange.getData();
        final DataTypeEnum dataType = data.getDataType();

        if (controllers.containsKey(dataType)
                && controllers.get(dataType) != null) {
            AbstractController<?> controller = controllers.get(dataType);

            // checking identity if the connection
            if(!data.isTokenSet() && data.isAuthSet()) {
                user = controller.logUser(data.getLogin(), data.getMdp());
                if (user != null) {
                    data.setToken((String) user.getSession().getAttribute("token"));
                    log("User logged successfully");
                    exchange.setData(data);
                    exchange.send();
                }
            } else {
                throw new IllegalArgumentException("Login information not set");
            }

            if (user != null && user.isAuthenticated()) {
                // execute controller method on action
                executeControllerOnAction(response, data, controller);

            } else {
                log("User login failed"); //, stopping...
                data.setMessage("Login ou mot de passe incorrect.");
                exchange.setData(data);
                exchange.send();
                //System.exit(0);
            }

            exchange.setData(response);
            exchange.send();
        } else {
            throw new IllegalArgumentException("Controller not initialise : " + dataType);
        }

    }

    private void executeControllerOnAction(DefaultData<Object> response, DefaultData<?> data, AbstractController<?> controller) {
        switch (data.getAction()) {
            case INIT -> {
                response.setMessage("Connected");
                response.setRequestStatus(true);
            }
            case READ -> {
                Object readResult;
                if (data.getObject() != null) {
                    readResult = controller.read((int) data.getObject());
                    response.setObject(readResult);
                } else {
                    try {
                        readResult = controller.read();
                        response.setObject(readResult);
                    } catch (IllegalAccessException e) {
                        readResult = null;
                        response.setRequestStatus(false);
                        response.setMessage("User not permitted to realise action");
                        e.printStackTrace();
                    }
                }
                if (readResult == null) {
                    response.setRequestStatus(false);
                    if (response.getMessage() != null) {
                        response.setMessage("Unable to read " + data.getDataType() + " : no result in request");
                    }
                } else {
                    response.setRequestStatus(true);
                    response.setMessage("Read " + data.getDataType() + " success");
                }
            }
            case POST -> {
                int postResult = controller.post(data);
                response.setObject(postResult);

                if (postResult == 0) {
                    response.setRequestStatus(false);
                    response.setMessage("Unable to create " + data.getDataType());
                } else {
                    response.setRequestStatus(true);
                    response.setMessage("Create " + data.getDataType() + " success");
                }
            }
            case UPDATE -> {
                Object updateResult = controller.update(data);
                response.setObject(updateResult);

                if (updateResult == null) {
                    response.setRequestStatus(false);
                    response.setMessage("Unable to update " + data.getDataType() + " : no result in request");
                } else {
                    response.setRequestStatus(true);
                    response.setMessage("Create " + data.getDataType() + " success");
                }
            }
            case DELETE -> {
                controller.delete((int) data.getObject());
                response.setRequestStatus(true);
                response.setMessage("Delete success");
            }
            default -> log("Action not found : " + data.getAction());// + ", stopping..."
            //System.exit(0);
        }
    }

    private void log(String message) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, message);
    }
}
