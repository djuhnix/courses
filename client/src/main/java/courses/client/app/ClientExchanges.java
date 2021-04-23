package courses.client.app;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.Serializers;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class ClientExchanges{
    final static String serverHost = "localhost";
    static Socket  client = null;
    public static void main(String args[]){
        //testToJsonClass test= new testToJsonClass();
        try{
            client = createConnexion("bob","mdp");
            registrationStrudent("BBI","v","t","rrr","","","","","");
            //ClientExchanges.sendFile("C:\\Users\\sseba\\OneDrive\\Documents\\cnam\\recherche\\edf\\lettre.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Object[] array ={test};
        //String[] res = sendSocket(array);
    }

    /*public static String[] sendObjects(Object[] ObjectsToSend) {
        Socket socketOfClient = null;
        BufferedWriter os = null;
        BufferedReader is = null;

        try {
            // Send a request to connect to the server is listening
            // on machine 'localhost' port 9999.
            socketOfClient = new Socket(serverHost, 1024);

            // Create output stream at the client (to send data to the server)
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));


            // Input stream at Client (Receive data from the server).
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + serverHost);
            return;
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + serverHost);
            return;
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
            os.write("I am Tom Cat");
            os.newLine();
            os.flush();
            os.write("QUIT");
            os.newLine();
            os.flush();



            // Read data sent from the server.
            // By reading the input stream of the Client Socket.
            String responseLine;
            while ((responseLine = is.readLine()) != null) {
                System.out.println("Server: " + responseLine);
                if (responseLine.indexOf("OK") != -1) {
                    break;
                }
            }

            os.close();
            is.close();
            socketOfClient.close();
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
        return dataReceived.toArray(new String[dataReceived.size()]);
    }*/

    public static Socket createConnexion(String log, String password) throws IOException {
        Socket socketOfClient = null;
        socketOfClient = new Socket(serverHost, 7777);
        //Converting the Object to JSONString


        Object auth =new Object(){
            public String login = log;
            public String mdp = password;
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



        String responseLine;
        while ((responseLine = is.readLine()) != null) {
            if(responseLine.equals("accepted")){
                System.out.println("accepted");
                break;
            }else if(responseLine.equals("refused") ){
                System.out.println("refused");
                os.close();
                is.close();
                client.close();
                break;
            }else{
                os.close();
                is.close();
                client.close();
                System.out.println("erreur lors de la connexion");
            }

        }

        return socketOfClient;


    }
    public static void registrationStrudent( String ine,String firstName, String lastName, String email, String phone, String address1, String address2, String c, String zipeCode) throws IOException {
        sendObject( new Object(){
            public String INE = ine;
            public String fname = firstName;
            public String lName = lastName;
            public String mail = email;
            public String phoneNumber = phone;
            public String addressLine1 = address1;
            public String addressLine2 = address2;
            public String city = c;
            public String zc = zipeCode;
        });

    }
    public static void registrationTeacher( String numen,String firstName, String lastName, String email, String phone, String address1, String address2, String c, String zipeCode) throws IOException {
        sendObject(new Object(){
            public String NUMEN = numen;
            public String fname = firstName;
            public String lName = lastName;
            public String mail = email;
            public String phoneNumber = phone;
            public String addressLine1 = address1;
            public String addressLine2 = address2;
            public String city = c;
            public String zc = zipeCode;
        });

    }

    public static void disconnection() throws IOException {
        client.close();
    }

    public static void addActivity(String n, Date start, Date end, String subj, int idProm) throws IOException {
        sendObject(new Object(){
            public String name = n;
            public Date startDate = start;
            public Date endDate =end;
            public String subject = subj;
            public int idPromotion = idProm;
        });


    }

    public static void addLessonOrExercise(String t, String n, int idAct, String filePath) throws IOException {
        sendString(t);// L for a lesson and E for an exercise
        sendObject(new Object(){
            public String name = n;
            public int idActivity;
        });
        sendFile(filePath);
    }

    public static void AddStudentWork(int idEx,String filePath) throws IOException {
        sendObject(new Object(){
            public int idExercice = idEx;
        });
        sendFile(filePath);
    }

    public static  void addGrade(int idStdt, int idAct, int grd) throws IOException {
        sendObject(new Object(){
            public int idStudent = idStdt;
            public int idActivity = idAct;
            public int grade = grd;
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

        os.write(id);
        os.newLine();
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
        System.out.println(buf.length);
        fos.write(buf);
    }

    public static void sendObject(Object objectToSend) throws IOException {
        // Create output stream at the client (to send data to the server)
        BufferedWriter os = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        String stringValue = objectToJson(objectToSend);
        os.write(stringValue);

        // End of line
        os.newLine();

        // Flush data.
        os.flush();


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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendString(String stringToSend) throws IOException {
        // Create output stream at the client (to send data to the server)
        BufferedWriter os = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        os.write(stringToSend);

        // End of line
        os.newLine();

        // Flush data.
        os.flush();


    }
    public static String objectToJson(Object obj) {
        ObjectWriter mapper = new ObjectMapper().writer();
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
