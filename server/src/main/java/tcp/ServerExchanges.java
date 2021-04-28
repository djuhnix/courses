package tcp;
import entities.Aggregator;

import java.io.*;
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
                BufferedReader br = new BufferedInputStream(is);
                FileOutputStream fos = new FileOutputStream("C:\\Users\\sseba\\OneDrive\\Documents\\cnam\\projet S2\\retour.pdf");
                //File file = new File("C:\\Users\\sseba\\OneDrive\\Documents\\cnam\\projet S2\\retour.txt");
                //BufferedWriter os = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\sseba\\OneDrive\\Documents\\cnam\\projet S2\\retour.txt")) );
                List<Object> dataReceived = new ArrayList<>();
                byte[] buf = new byte[1];
                int append = is.read(buf);
                while(append !=-1) {
                    byte[] buf2 = new byte[1];
                    append = is.read(buf2);
                    if(append != -1){
                        byte[] temp = new byte[buf.length + buf2.length];
                        System.arraycopy(buf,0,temp,0,buf.length);
                        System.arraycopy(buf2,0,temp,buf.length,buf2.length);
                        buf = temp;
                    }

                }
                log(Integer.toString(buf.length));
                fos.write(buf);
                fos.close();
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
        System.out.println("Sever stopped!");
    }
}
