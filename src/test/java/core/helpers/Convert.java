package core.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Convert {

    public static <T> String objectToString(T o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }

    public static <T> T stringToObject(String string, Class<T> o) throws JsonProcessingException {
        return  new ObjectMapper().readValue(string, o);
    }
}
