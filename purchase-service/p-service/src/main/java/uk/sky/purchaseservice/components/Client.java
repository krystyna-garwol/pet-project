package uk.sky.purchaseservice.components;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class Client {

    private HttpClient client;
    private String host = "http://localhost:8083";

    public Client(HttpClient client) {
        this.client = client;
    }

    private HttpRequest createGetRequest(String host, String endpoint) {
        URI uri = URI.create(host + "/" + endpoint);
        return HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
    }

    public HttpResponse<String> sendGetRequest(String host, String endpoint) {
        HttpResponse<String> response = null;
        try {
            HttpRequest request = createGetRequest(host, endpoint);
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch(Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
