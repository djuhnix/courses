package courses.client.app;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientExchanges {
    final static String serverHost = "localhost";
    static Socket  client = null;
    public static void main(String args[]){
        //testToJsonClass test= new testToJsonClass();
        try{
            client = ClientExchanges.createConnexion("bob","mdp");
            ClientExchanges.sendFile("C:\\Users\\sseba\\OneDrive\\Documents\\cnam\\recherche\\edf\\lettre.pdf");
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

                stringValue = objectToJson(value);
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

    public static Socket createConnexion(String login, String password) throws IOException {
        Socket socketOfClient = null;
        socketOfClient = new Socket(serverHost, 7777);
        Object auth = new Object(){
            String log = login;
            String mdp = password;
        };

        // Create output stream at the client (to send data to the server)
        BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));

        // Input stream at Client (Receive data from the server).
        BufferedReader is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

        String stringValue = objectToJson(auth);
        os.write(stringValue);

        // End of line
        os.newLine();

        // Flush data.
        os.flush();

        os.close();
        is.close();

        String responseLine;
        while ((responseLine = is.readLine()) != null) {
            if(responseLine =="accepted"){
                break;
            }else if(responseLine == "refused"){
                client.close();
                break;
            }else{
                client.close();
                System.out.println("erreur lors de la connexion");
            }
        }
        return socketOfClient;


    }
    public static void registrationStrudent( String ine,String firstName, String lastName, String email, String phone, String address1, String address2, String c, String zipeCode) throws IOException {
        sendObject( new Object(){
            String INE = ine;
            String fname = firstName;
            String lName = lastName;
            String mail = email;
            String phoneNumber = phone;
            String addressLine1 = address1;
            String addressLine2 = address2;
            String city = c;
            String zc = zipeCode;
        });

    }
    public static void registrationTeacher( String numen,String firstName, String lastName, String email, String phone, String address1, String address2, String c, String zipeCode) throws IOException {
        sendObject(new Object(){
            String NUMEN = numen;
            String fname = firstName;
            String lName = lastName;
            String mail = email;
            String phoneNumber = phone;
            String addressLine1 = address1;
            String addressLine2 = address2;
            String city = c;
            String zc = zipeCode;
        });

    }

    public static void disconnection() throws IOException {
        client.close();
    }

    public static void addActivity(String n, Date start, Date end, String subj, int idProm) throws IOException {
        sendObject(new Object(){
            String name = n;
            Date startDate = start;
            Date endDate =end;
            String subject = subj;
            int idPromotion = idProm;
        });


    }

    public static void addLessonOrExercise(char t, String n, int idAct, String filePath) throws IOException {
        sendObject(new Object(){
            char type =t; //L for lesson, E for exo
            String name = n;
            int idActivity;
        });
        sendFile(filePath);
    }

    public static void AddStudentWork(int idEx,String filePath) throws IOException {
        sendObject(new Object(){
            char type ='W';
            int idExercice = idEx;
        });
        sendFile(filePath);
    }

    public static  void addGrade(int idStdt, int idAct, int grd) throws IOException {
        sendObject(new Object(){
            int idStudent = idStdt;
            int idActivity = idAct;
            int grade = grd;
        });
    }

    public static ArrayList<Object> requestGrades() throws IOException {
        BufferedWriter os = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        // Input stream at Client (Receive data from the server).
        BufferedReader is = new BufferedReader(new InputStreamReader(client.getInputStream()));

        String stringValue ="G";
        os.write(stringValue);

        // End of line
        os.newLine();

        // Flush data.
        os.flush();
        String responseLine;
        ArrayList<Object> grades =new ArrayList<>();
        while ((responseLine = is.readLine()) != null) {
            if (responseLine == "end") {
                break;
            } else {
                grades.add(jsonToObject(responseLine));
                System.out.println("erreur lors de la connexion");
            }
        }
        os.close();
        is.close();
        return grades;
    }

    public static void requestFile(char type, int id, String savePath) throws IOException {
        BufferedWriter os = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        FileOutputStream fos = new FileOutputStream(savePath+"\\lesson.pdf");

        // Input stream at Client (Receive data from the server).
        InputStream is = new BufferedInputStream(client.getInputStream());

        String stringValue = "R"+type ;//RL for lesson, RE for exo and RW for work
        os.write(stringValue);

        // End of line
        os.newLine();

        // Flush data.
        os.flush();
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
        System.out.println(Integer.toString(buf.length));
        fos.write(buf);
        fos.close();
    }

    public static void sendObject(Object objectToSend) throws IOException {
        // Create output stream at the client (to send data to the server)
        BufferedWriter os = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        // Input stream at Client (Receive data from the server).
        BufferedReader is = new BufferedReader(new InputStreamReader(client.getInputStream()));

        String stringValue = objectToJson(objectToSend);
        os.write(stringValue);

        // End of line
        os.newLine();

        // Flush data.
        os.flush();

        os.close();
        is.close();


    }
    public static void sendFile(String path) {
        try
        {

            BufferedOutputStream outFichier = new BufferedOutputStream(client.getOutputStream());

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String objectToJson(Object obj) {
        ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
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
}
