package courses.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonUtils {
    public static String objectToJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(obj);
            Logger.getLogger(JsonUtils.class.getName()).log(Level.INFO, "ResultingJSON = " + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static <T> T mapObject(Object object, Class<T> mapType) {
        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        return mapper.convertValue(object, mapType);
    }

    public static <T> T jsonToObject(String json, Class<T> objectClass) throws JsonMappingException {
        ObjectMapper mapper = new ObjectMapper();
        T instanceResult = null;
        try {
            instanceResult = mapper.readValue(json, objectClass);
        } catch (IOException e) {
            Logger.getLogger(JsonUtils.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            e.printStackTrace();
        }
        return instanceResult;
    }

    public static Object jsonToObject(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Object instanceResult = null;
        try {
            instanceResult = mapper.readValue(json, Object.class);
        } catch (IOException e) {
            Logger.getLogger(JsonUtils.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        return instanceResult;
    }
}
