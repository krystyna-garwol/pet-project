package uk.sky.purchaseservice.components;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

@Component
public class Client {

    private CloseableHttpClient client;
    private Response response;

    public Client(CloseableHttpClient client, Response response) {
        this.client = client;
        this.response = response;
    }

    private String host = "http://localhost:8081";

    private void execute(HttpUriRequest request) {
        try {
            HttpResponse httpResponse = client.execute(request);

            int statusCode = httpResponse.getStatusLine().getStatusCode();
            response.setStatusCode(statusCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            response.setResponseBody(reader.readLine());
        } catch(Exception e) {
            throw new RuntimeException();
        }
    }

    public void sendRequest(String method, String endpoint) {
        URI uri = URI.create(host + "/" + endpoint);
        switch(method) {
            case "GET":
                HttpGet httpGet = new HttpGet(uri);
                execute(httpGet);
                break;
            default: throw new RuntimeException("Unrecognised HTTP Method");
        }
    }
}
