package courses.client.app;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ClientExchanges {
    final static String serverHost = "localhost";
    public static void main(String args[]){
        //testToJsonClass test= new testToJsonClass();
        try{
            Socket client = ClientExchanges.createConnexion();
            ClientExchanges.sendFile("C:\\Users\\sseba\\OneDrive\\Documents\\cnam\\recherche\\edf\\lettre.pdf", client);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Object[] array ={test};
        //String[] res = sendSocket(array);
    }

    public static String[] sendObjects(Object[] ObjectsToSend) {
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

            // Input stream at Client (Receive data from the server).
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + serverHost);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + serverHost);
        }

        try {

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

    public static Socket createConnexion() throws IOException {
        Socket socketOfClient = null;
        socketOfClient = new Socket(serverHost, 7777);
        return socketOfClient;

    }


    public void sendAuthRequest(){
        //Todo
    }


    public static void sendFile(String path, Socket socketOfClient) {
        try
        {

            BufferedOutputStream outFichier = new BufferedOutputStream(socketOfClient.getOutputStream());

            try {
                File file = new File(path);
                int length =(int) file.length();
                BufferedInputStream temp = new BufferedInputStream(new FileInputStream(file),length);
                int len = 0;
                byte[] tampon = new byte[length];
                len = temp.read(tampon, 0, length);
                outFichier.write(tampon, 0, len);
                outFichier.flush();
                System.out.println(len + " bytes ont ete envoyes correctement" );
                //     }
            }
            catch
            (IOException e) {
                System.out.println("impossible d'ouvrir le fichier" );
            }
            outFichier.close();
            outFichier.close();
            socketOfClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
