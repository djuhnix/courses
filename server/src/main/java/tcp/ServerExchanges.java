package tcp;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Aggregator;

import java.io.*;
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

                InputStream is = new BufferedInputStream(socketOfServer.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = br.readLine();
                log(line);
                //log request
                if (line.startsWith("log")){
                    jsonToObject(line);
                }else if(line.startsWith("blb")) {
                    FileOutputStream fos = new FileOutputStream("C:\\Users\\sseba\\OneDrive\\Documents\\cnam\\projet S2\\retour.pdf");
                    //File file = new File("C:\\Users\\sseba\\OneDrive\\Documents\\cnam\\projet S2\\retour.txt");
                    //BufferedWriter os = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\sseba\\OneDrive\\Documents\\cnam\\projet S2\\retour.txt")) );
                    byte[] buf = new byte[1];
                    int append = is.read(buf);
                    while (append != -1) {
                        byte[] buf2 = new byte[1];
                        append = is.read(buf2);
                        if (append != -1) {
                            byte[] temp = new byte[buf.length + buf2.length];
                            System.arraycopy(buf, 0, temp, 0, buf.length);
                            System.arraycopy(buf2, 0, temp, buf.length, buf2.length);
                            buf = temp;
                        }

                    }
                    log(Integer.toString(buf.length));
                    fos.write(buf);
                    fos.close();
                }

                //put all the Strings received from the client and transform them into Objects
                //save all the objects into an array
               /* while (true) {
                    byte[] line =is.readLine();
                    File file = new File(line);
                    /*if (line.equals("end")) {
                        break;
                    } else {

                        dataReceived.add(testJackson.jsonToObject(line));
                    }
                }
                //dataReceived.toArray(new Aggregator[dataReceived.size()]);

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
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String objectToJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(obj);
            System.out.println("ResultingJSONstring = " + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static Object jsonToObject(String json){
        ObjectMapper mapper = new ObjectMapper();
        Object instanceResult = null;
        try {
            instanceResult = mapper.readValue(json, Aggregator.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instanceResult;
    }
}
