package core.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import core.helpers.Convert;
import core.helpers.HttpResponseDecorator;

import java.net.http.HttpResponse;

public interface IHttpResponseDecorator {
    HttpResponseDecorator setResponse(HttpResponse response);
    <T> T getBody(Class<T> clazz) throws JsonProcessingException;
}
