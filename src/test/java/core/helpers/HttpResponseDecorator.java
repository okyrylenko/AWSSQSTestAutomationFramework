package core.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import core.interfaces.IHttpResponseDecorator;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;

public class HttpResponseDecorator implements IHttpResponseDecorator {

    HttpResponse response;

    public HttpResponseDecorator setResponse(HttpResponse response){
        this.response = response;
        return this;
    }
    public <T> T getBody(Class<T> clazz) throws JsonProcessingException {
        String s = this.response.body().toString();
        return Convert.stringToObject(s,clazz);
    }
}
