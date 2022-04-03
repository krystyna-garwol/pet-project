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

    private HttpRequest createGetRequest(String host, String endpoint) {
        URI uri = URI.create(host + "/" + endpoint);
        return HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
    }

    private HttpRequest createPostOrPutRequest(String method, String host, String endpoint, String body) {
        URI uri = URI.create(host + "/" + endpoint);
        return HttpRequest.newBuilder()
                .method(method, HttpRequest.BodyPublishers.ofString(body))
                .headers("Content-Type", "application/json")
                .uri(uri)
                .build();
    }

    private HttpRequest createEmptyPostRequest(String host, String endpoint) {
        URI uri = URI.create(host + "/" + endpoint);
        System.out.println(uri);
        return HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.noBody())
                .uri(uri)
                .build();
    }

    public Response sendRequest(String method, String host, String endpoint, String body) {
        HttpRequest request;
        HttpResponse<String> httpResponse = null;
        if(endpoint.equals("__admin/mappings/reset")) {
            request = createEmptyPostRequest(host, endpoint);
        } else if(method.equals("POST") || method.equals("PUT")) {
            request = createPostOrPutRequest(method, host, endpoint, body);
        } else {
            request = createGetRequest(host, endpoint);
        }

        try {
            httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setResponseBody(httpResponse.body());
        response.setStatusCode(httpResponse.statusCode());
        return response;
    }
}
