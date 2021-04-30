package courses.client.api;

import courses.utils.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class ClientExchanges {

    private final Socket socketOfClient;
    private DataExchange exchange;
    private String token;

    public ClientExchanges(String host, int port) throws IOException {
        socketOfClient = new Socket(host, port);
        //exchange = new DataExchange();
    }

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
            token = data.getToken();
            System.out.println("Connexion accepted");
        } else {
            // TODO close connection
            // socketOfClient.close();
            System.out.println("Unable to connect");
        }

        return data;
    }

    public void disconnect() throws IOException {
        socketOfClient.close();
    }

    public DefaultData<?>  registrationStudent( String INE,String fName, String lName, String pemail, String phone, String address1, String address2, String c, String zipeCode) throws IOException {
        // creating data to be sent
        DefaultData<Object> data = new DefaultData<>();
        data.setAction(ActionEnum.POST);
        data.setDataType(DataTypeEnum.STUDENT);
        data.setObject(new Object() {
            public final String ine = INE;
            public final String nom = lName;
            public final String prenom = fName;
            public final String email = pemail;
            public final String telephone = phone;
            public final String adresse1 = address1;
            public final String adresse2 = address2;
            public final String ville = c;
            public final String cp = zipeCode;
        });
        return sendData(data, "Student registered success", "Error while registering student");
    }

    public DefaultData<?> registrationTeacher(String num, String fName, String lName, String pemail, String phone, String address1, String address2, String c, String zipeCode) throws IOException {
        // creating data to be sent
        DefaultData<Object> data = new DefaultData<>();
        data.setAction(ActionEnum.POST);
        data.setDataType(DataTypeEnum.TEACHER);
        data.setObject(new Object() {
            public final String numen = num;
            public final String nom = lName;
            public final String prenom = fName;
            public final String email = pemail;
            public final String telephone = phone;
            public final String adresse1 = address1;
            public final String adresse2 = address2;
            public final String ville = c;
            public final String cp = zipeCode;
        });

        return sendData(data, "Teacher registered successfully", "Error while registering teacher");

    }

    public DefaultData<?> addActivity(String n, Date startDate, Date endDate, String subj, int idProm, int idProf) throws IOException {
        // creating data to be sent
        DefaultData<Object> data = new DefaultData<>();
        data.setAction(ActionEnum.POST);
        data.setDataType(DataTypeEnum.ACTIVITY);
        data.setObject(new Object(){
            public final String name = n;
            public final Date start = startDate;
            public final Date end = endDate;
            public final String subject = subj;
            public final int idPromotion = idProm;
            public final int idTeacher = idProf;
        });
        return sendData(data, "Activity added successfully", "Error while registering activity");
    }

    /**
     *
     * @param t type : L = Lesson, E = Exercise
     * @param n name
     * @param idAct activity Id
     * @param filePath path of the file
     * @throws IOException
     */
    public DefaultData<?> addLessonOrExercise(char t, String n, int idAct, String filePath) throws IOException {
        // creating data to be sent
        DefaultData<Object> data = new DefaultData<>();
        data.setAction(ActionEnum.POST);
        if (t == 'L') {
            data.setDataType(DataTypeEnum.LESSON);
        } else if (t == 'E') {
            data.setDataType(DataTypeEnum.EXERCISE);
        }
        data.setObject(new Object(){
            public final String name = n;
            public int idActivity;
        });
        data.setFilePath(filePath);
        return sendData(data, data.getDataType() + " added successfully", "Error while registering " + data.getDataType());
    }

    public void AddStudentWork(int idEx,String filePath) throws IOException {
        // creating data to be sent
        DefaultData<Object> data = new DefaultData<>();
        data.setAction(ActionEnum.POST);
        //data.setDataType(DataTypeEnum.ACTIVITY); // TODO defined data type
        data.setObject(new Object(){
            public final int idExercice = idEx;
        });
        data.setFilePath(filePath);
        sendData(data, "Student work added successfully", "Error occurred while adding student work");
    }

    public DefaultData<?> addGrade(int idStdt, int idAct, int grd) throws IOException {
        // creating data to be sent
        DefaultData<Object> data = new DefaultData<>();
        data.setAction(ActionEnum.POST);
        data.setDataType(DataTypeEnum.GRADE);
        data.setObject(new Object(){
            public final int idStudent = idStdt;
            public final int idActivity = idAct;
            public final int grade = grd;
        });
        return sendData(data, "Grade added successfully", "Error occurred while adding student grade");
    }

    public ArrayList<Object> requestGrades() throws IOException {
        DefaultData<Object> data = new DefaultData<>();
        data.setAction(ActionEnum.READ);
        data.setDataType(DataTypeEnum.GRADE);

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
        ArrayList<Object> grades = new ArrayList<>();
        while ((responseLine = is.readLine()) != null) {
            if (responseLine.equals("end")) {
                break;
            } else {
                grades.add(JsonUtils.jsonToObject(responseLine));
                System.out.println("erreur lors de la connexion");
            }
        }
        return grades;
    }

    private DefaultData<?> sendData(DefaultData<Object> data, String success, String error) {
        data.setToken(token);
        exchange.setData(data);
        exchange.send();
        exchange.receiveData();
        DefaultData<?> response = exchange.getData();
        if (response != null) {
            System.out.println(success);
            return response;
        } else {
            System.out.println(error);
            return null;
        }
    }

}
