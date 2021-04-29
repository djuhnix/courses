package tcp;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.*;

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
                BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
                String line = br.readLine();
                //log request
                log(line);
                if (line.startsWith("{\"login\"")){
                    Object auth = jsonToObject(line);
                    // créer la session


                    os.write("accepted");
                    os.newLine();
                    os.flush();


                }else if(line.startsWith("{\"INE\"")) {
                    Student student = (Student) jsonToObject(line);
                    //ajouter l'eleve à la bd
                }else if(line.startsWith("{\"NUMEN\"")) {
                    Teacher teacher = (Teacher) jsonToObject(line);
                    //ajouter l'enseignant à la bd
                }else if(line.startsWith("{\"name\"")) {
                    Activity activity = (Activity) jsonToObject(line);
                    //ajouter l'activité à la bd
                }else if(line.equals("L")) {

                    line= br.readLine();
                    Lesson lesson = (Lesson) jsonToObject(line);
                    //ajout de l'élement lesson à la bd
                    //récupération du fichier et enregistrement
                    FileOutputStream fos = new FileOutputStream("C:\\Users\\sseba\\OneDrive\\Documents\\cnam\\projet S2\\retour.pdf");
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
                }else if(line.equals("E")) {
                    //ajout de l'élement exercise à la bd
                    //récupération du fichier et enregistrement
                    line= br.readLine();
                    Exercise exercise = (Exercise) jsonToObject(line);

                    FileOutputStream fos = new FileOutputStream("C:\\Users\\sseba\\OneDrive\\Documents\\cnam\\projet S2\\retour.pdf");
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
                }else if(line.startsWith("{\"idStudent\"")) {
                    Graduation graduation = (Graduation) jsonToObject(line);
                    //ajouter le grade à la bd
                }else if(line.equals("RL")){
                        line = br.readLine();
                        //recuperer le path du fichier avec l'id de la lesson présent dans line
                    String path ="bidon";
                    sendFile(path,socketOfServer);
                }else if(line.equals("RE")){
                    line = br.readLine();
                    //recuperer le path du fichier avec l'id de l'exercice' présent dans line
                    String path ="bidon";
                    sendFile(path,socketOfServer);
                }else if(line.equals("RW")){
                    line = br.readLine();
                    //recuperer le path du fichier avec l'id du travail présent dans line
                    String path ="bidon";
                    sendFile(path,socketOfServer);
                }else if(line.equals("G")) {
                    Graduation[] grades ={};//recuperer les grades de l'étudiant
                    for(Graduation grade: grades){
                        os.write(objectToJson(grade));
                        os.newLine();
                        os.flush();
                    }
                }
                } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
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
            instanceResult = mapper.readValue(json, Object.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instanceResult;
    }

    public static void sendFile(String path, Socket socket) {
        try
        {

            BufferedOutputStream outFichier = new BufferedOutputStream(socket.getOutputStream());

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
