package dataaccess.clients;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public abstract class HTTPClient{
    private HttpClient client;


    public HTTPClient(){
        client  = HttpClient.newHttpClient();
    }


    public HttpResponse post(HttpRequest request) throws IOException, InterruptedException {
        return client.send(request,HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse get(HttpRequest request) throws IOException, InterruptedException {
        return client.send(request,HttpResponse.BodyHandlers.ofString());
    }
}
