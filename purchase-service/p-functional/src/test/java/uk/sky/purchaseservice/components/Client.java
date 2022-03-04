package uk.sky.purchaseservice.components;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class Client {

    private HttpClient client;
    private Response response;
    private String host = "http://localhost:8081";

    public Client(HttpClient client, Response response) {
        this.client = client;
        this.response = response;
    }

    private HttpRequest createGetRequest(String endpoint) {
        URI uri = URI.create(host + "/" + endpoint);
        return HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
    }

    private HttpRequest createPostRequest(String endpoint, String body) {
        URI uri = URI.create(host + "/" + endpoint);
        return HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .headers("Content-Type", "application/json")
                .uri(uri)
                .build();
    }

    public Response sendRequest(String method, String endpoint, String body) throws IOException, InterruptedException {
        HttpRequest request;
        if(method.equals("POST")) {
            request = createPostRequest(endpoint, body);
        } else {
            request = createGetRequest(endpoint);
        }

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        response.setResponseBody(httpResponse.body());
        response.setStatusCode(httpResponse.statusCode());
        return response;
    }
}
