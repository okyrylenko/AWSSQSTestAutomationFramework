package core.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

public class DateTimeHelper {

    public static <T> String convertToString(T t, SimpleDateFormat format) throws JsonProcessingException {
        return new ObjectMapper().setDateFormat(format).writeValueAsString(t);
    }
}
