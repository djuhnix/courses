package tcp;


import entities.Aggregator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;

public class ServerExchanges {

    public static void main(String args[]) throws IOException {

        ServerSocket listener = null;

        System.out.println("Server is waiting to accept user...");
        int clientNumber = 0;

        // Try to open a server socket on port 7777
        // Note that we can't choose a port less than 1023 if we are not
        // privileged users (root)

        try {
            listener = new ServerSocket(7777);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        try {
            while (true) {
                // Accept client connection request
                // Get new Socket at Server.

                Socket socketOfServer = listener.accept();
                new ServiceThread(socketOfServer, clientNumber++).start();
            }
        } finally {
            listener.close();
        }

    }

    private static void log(String message) {
        System.out.println(message);
    }

    private static class ServiceThread extends Thread {

        private int clientNumber;
        private Socket socketOfServer;

        public ServiceThread(Socket socketOfServer, int clientNumber) {
            this.clientNumber = clientNumber;
            this.socketOfServer = socketOfServer;

            // Log
            log("New connection with client# " + this.clientNumber + " at " + socketOfServer);
        }

        @Override
        public void run() {

            try {

                // Open input and output streams
                BufferedReader is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
                BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
                List<Object> dataReceived = new ArrayList<>();
                //put all the Strings received from the client and transform them into Objects
                //save all the objects into an array
                while (true) {
                    String line = is.readLine();
                    if (line.equals("end")) {
                        break;
                    } else {
                        dataReceived.add(testJackson.jsonToObject(line));
                    }
                }
                dataReceived.toArray(new Aggregator[dataReceived.size()]);

                //call functions to process data received
                //TODO

                Aggregator[] ObjectsToSend = new Aggregator[0];
                String stringValue;
                for (Object value : ObjectsToSend) {

                    stringValue = testJackson.objectToJson(value);
                    // Write data to the output stream of the Client Socket.
                    os.write(stringValue);

                    // End of line
                    os.newLine();

                    // Flush data.
                    os.flush();
                }
                os.write("end");
                os.newLine();
                os.flush();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
