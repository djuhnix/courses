package tcp;
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
        String line;
        BufferedReader br;
        BufferedWriter bw;
        Socket socketOfServer = null;

        // Try to open a server socket on port 9999
        // Note that we can't choose a port less than 1023 if we are not
        // privileged users (root)


        try {
            listener = new ServerSocket(1024);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        try {
            System.out.println("Server is waiting to accept user...");

            // Accept client connection request
            // Get new Socket at Server.
            socketOfServer = listener.accept();
            System.out.println("Accept a client!");

                // Open input and output streams

                InputStream is = new BufferedInputStream(socketOfServer.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
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
                dataReceived.toArray(new Object[dataReceived.size()]);

                //call functions to process data received
                //TODO

                // Write to socket of Server
                // (Send to client)
                bw.write(">> " + line);
                // End of line
                bw.newLine();
                // Flush data.
                bw.flush();


                // If users send QUIT (To end conversation).
                if (line.equals("QUIT")) {
                    bw.write(">> OK");
                    bw.newLine();
                    bw.flush();
                    break;
                }
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.println("Sever stopped!");
    }
}
