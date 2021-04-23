package courses.client.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class testJackson {
    public static void main(String[] args) {
        testToJsonClass cat = new testToJsonClass();
        cat.setId(1);
        cat.setName("SiAm");
        cat.setColor("Cream");
        cat.setEyecolor("Blue");
        cat.setBreed("Siamese");
        String test = objectToJson(cat);
        System.out.println(test);
        Object test2 = jsonToObject(test);
        System.out.println(test2);

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
}
