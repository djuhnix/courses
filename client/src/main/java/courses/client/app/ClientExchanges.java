package courses.client.app;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ClientExchanges {
    final static String serverHost = "localhost";
    public static void main(String args[]){
        testToJsonClass test= new testToJsonClass();
        Object[] array ={test};
        String[] res = sendSocket(array);
    }

    public static String[] sendSocket(Object[] ObjectsToSend){
        Socket socketOfClient = null;
        BufferedWriter os = null;
        BufferedReader is = null;
        List<String> dataReceived = new ArrayList<String>();

        try {
            // Send a request to connect to the server is listening
            // on machine 'localhost' port 7777.
            socketOfClient = new Socket(serverHost, 7777);

            // Create output stream at the client (to send data to the server)
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));

<<<<<<< HEAD
=======

>>>>>>> échanges entre serveur et client.
            // Input stream at Client (Receive data from the server).
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + serverHost);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + serverHost);
        }

        try {
            String stringValue;
            for( Object value : ObjectsToSend ) {

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

            // Read data sent from the server.
            // By reading the input stream of the Client Socket.

            String responseLine;
            while ((responseLine = is.readLine()) != null) {
                System.out.println("Server: " + responseLine);
                dataReceived.add(responseLine);
                if (responseLine.indexOf("end") != -1) {
                    break;
                }
            }

            os.close();
            is.close();
            socketOfClient.close();
            System.out.println("socket closed");
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
        return dataReceived.toArray(new String[dataReceived.size()]);
    }
}
