package uk.sky.purchaseservice.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class Client {

    @Autowired
    private HttpClient client;

    String host = "http://localhost:8081";

    private HttpRequest createRequest(String method, String endpoint) {
        if(method.equals("POST")) {
            return HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .uri(URI.create(host + endpoint))
                    .build();
        }
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(host + endpoint))
                .build();
    }

    public HttpResponse<String> sendRequest(String method, String endpoint) throws IOException, InterruptedException {
        HttpRequest request = createRequest(method, endpoint);
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
