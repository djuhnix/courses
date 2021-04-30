package courses.utils;

import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utils class to exchange data between server and client
 */
public class DataExchange implements Serializable {
    private final Socket socket;
    private DefaultData<?> data = null;

    public DataExchange(Socket socket, boolean withData) {
        this.socket = socket;
        if (withData) {
            this.receiveData();
        }
    }

    public void receiveData() {
        try {
            InputStream is = new BufferedInputStream(socket.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String line = br.readLine();

            // log line
            Logger.getLogger(getClass().getName()).log(Level.INFO, "Line : " + line);

            // read data
            data = JsonUtils.jsonToObject(line, DefaultData.class);

            Logger.getLogger(getClass().getName()).log(Level.INFO, "Data received");
            if (data.getFilePath() != null) {
                saveFile(data.getFilePath());
                Logger.getLogger(getClass().getName()).log(Level.INFO, "File saved");
            }
        } catch (JsonMappingException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Wrong data type given", e);
            e.printStackTrace();
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Unable to read data, an error occurred.", e);
            e.printStackTrace();
        }
    }

    public DataExchange(Socket socket, DefaultData<?> data) {
        this.socket = socket;
        this.data = data;
    }

    public void send() {
        if (data != null) {
            // Create output stream at the client (to send data to the server)
            try {
                BufferedWriter os =  new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                String stringValue = JsonUtils.objectToJson(data);
                os.write(stringValue);

                // End of line
                os.newLine();
                // Flush data.
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (data.getFilePath() != null) {
                sendFile();
            }
        } else {
            throw new IllegalArgumentException("Can't send DefaultData : data is null");
        }
    }

    private void sendFile() {
        try (BufferedOutputStream outFile = new BufferedOutputStream(socket.getOutputStream())){

            File file = new File(data.getFilePath());
            int length =(int) file.length();
            BufferedInputStream temp = new BufferedInputStream(new FileInputStream(file), length);
            byte[] tampon = new byte[length];
            int len = temp.read(tampon, 0, length);

            outFile.write(tampon, 0, len);
            outFile.flush();

            temp.close();

            Logger.getLogger(getClass().getName()).log(Level.INFO, len + " bytes sent successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFile(String rootPath) throws IOException {
        InputStream is = new BufferedInputStream(socket.getInputStream());
        FileOutputStream fos = new FileOutputStream(rootPath + File.separator + data.getFilePath());
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
        Logger.getLogger(getClass().getName()).log(Level.INFO, Integer.toString(buf.length));
        fos.write(buf);
        fos.close();
    }

    /*
    public DefaultData<?> getFromJson(String json) throws JsonMappingException {
        return JsonUtils.jsonToObject(json, DefaultData.class);
    }
     */

    public DefaultData<?> getData() {
        return data;
    }

    public void setData(DefaultData<?> data) {
        this.data = data;
    }
}
