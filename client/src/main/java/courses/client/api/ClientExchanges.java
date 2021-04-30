package courses.client.api;

import courses.utils.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class ClientExchanges{

    private final Socket socketOfClient;
    private DataExchange exchange;

    public ClientExchanges(String host, int port) throws IOException {
        socketOfClient = new Socket(host, port);
        //exchange = new DataExchange();
    }
    /*
    public static void main(String[] args){
        //testToJsonClass test= new testToJsonClass();
        try{
            initConnexion("bob","mdp");
            registrationStrudent("BBI","v","t","rrr","","","","","");
            //ClientExchanges.sendFile("C:\\Users\\sseba\\OneDrive\\Documents\\cnam\\recherche\\edf\\lettre.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     */

    public DefaultData<?> initConnexion(String log, String password) throws IOException {

        // creating data to be sent
        DefaultData<?> data = new DefaultData<>();
        data.setAuth(log, password);
        data.setAction(ActionEnum.INIT);
        data.setDataType(DataTypeEnum.USER);
        exchange = new DataExchange(socketOfClient, data);
        exchange.send();

        exchange.receiveData();
        data = exchange.getData();
        if(data != null && data.isTokenSet()){
            System.out.println("Connexion accepted");
        } else {
            socketOfClient.close();
            System.out.println("Unable to connect");
        }

        return data;
    }

    public void registrationStudent( String ine,String firstName, String lastName, String email, String phone, String address1, String address2, String c, String zipeCode) throws IOException {
        // creating data to be sent
        DefaultData<Object> data = new DefaultData<>();
        data.setAction(ActionEnum.POST);
        data.setDataType(DataTypeEnum.STUDENT);
        data.setObject(new Object(){
            public final String INE = ine;
            public final String fname = firstName;
            public final String lName = lastName;
            public final String mail = email;
            public final String phoneNumber = phone;
            public final String addressLine1 = address1;
            public final String addressLine2 = address2;
            public final String city = c;
            public final String zc = zipeCode;
        });
        exchange.send();
    }
    public void registrationTeacher( String numen,String firstName, String lastName, String email, String phone, String address1, String address2, String c, String zipeCode) throws IOException {
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

    public void disconnection() throws IOException {
        socketOfClient.close();
    }

    public void addActivity(String n, Date start, Date end, String subj, int idProm) throws IOException {
        sendObject(new Object(){
            public final String name = n;
            public final Date startDate = start;
            public Date endDate =end;
            public String subject = subj;
            public int idPromotion = idProm;
        });


    }

    public void addLessonOrExercise(String t, String n, int idAct, String filePath) throws IOException {
        sendString(t);// L for a lesson and E for an exercise
        sendObject(new Object(){
            public String name = n;
            public int idActivity;
        });
        sendFile(filePath);
    }

    public void AddStudentWork(int idEx,String filePath) throws IOException {
        sendObject(new Object(){
            public int idExercice = idEx;
        });
        sendFile(filePath);
    }

    public void addGrade(int idStdt, int idAct, int grd) throws IOException {
        sendObject(new Object(){
            public int idStudent = idStdt;
            public int idActivity = idAct;
            public int grade = grd;
        });
    }

    public ArrayList<Object> requestGrades() throws IOException {
        BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));

        // Input stream at socketOfClient (Receive data from the server).
        BufferedReader is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

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
                grades.add(JsonUtils.jsonToObject(responseLine));
                System.out.println("erreur lors de la connexion");
            }
        }
        return grades;
    }

    public void requestFile(char type, int id, String savePath) throws IOException {
        BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
        FileOutputStream fos = new FileOutputStream(savePath+"\\lesson.pdf");

        // Input stream at socketOfClient (Receive data from the server).
        InputStream is = new BufferedInputStream(socketOfClient.getInputStream());

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

    private void sendObject(Object objectToSend) throws IOException {
        DefaultData<?> data = new DefaultData<>();
        data.setAction(ActionEnum.INIT);
        data.setDataType(DataTypeEnum.USER);
        exchange = new DataExchange(socketOfClient, data);
        exchange.send();
        // Create output stream at the socketOfClient (to send data to the server)
        BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));

        String stringValue = JsonUtils.objectToJson(objectToSend);
        os.write(stringValue);

        // End of line
        os.newLine();

        // Flush data.
        os.flush();


    }
    public void sendFile(String path) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendString(String stringToSend) throws IOException {
        // Create output stream at the socketOfClient (to send data to the server)
        BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));

        os.write(stringToSend);

        // End of line
        os.newLine();

        // Flush data.
        os.flush();


    }

}
