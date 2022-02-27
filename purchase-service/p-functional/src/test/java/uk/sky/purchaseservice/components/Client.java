package uk.sky.purchaseservice.components;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class Client {

    private HttpClient client;
    private Response response;

    public Client(HttpClient client, Response response) {
        this.client = client;
        this.response = response;
    }

    private String host = "http://localhost:8081";

    private HttpRequest createRequest(String method, String endpoint) {
        URI uri = URI.create(host + "/" + endpoint);
        if(method.equals("POST")) {
            return HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .uri(uri)
                    .build();
        }
        return HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
    }

    public Response sendRequest(String method, String endpoint) {
        HttpRequest request = createRequest(method, endpoint);
        Response httpResponse = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(res -> {
                    response.setStatusCode(res.statusCode());
                    response.setResponseBody(res.body());
                    return response;
                }).join();
        return httpResponse;
    }
}
