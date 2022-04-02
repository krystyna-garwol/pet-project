package uk.sky.purchaseservice.steps;

import io.cucumber.java.en.Given;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import uk.sky.purchaseservice.components.Client;
import uk.sky.purchaseservice.components.Response;

public class PrivateSteps {

    @Autowired
    private Client client;

    @Autowired
    private Response response;

//    public PrivateSteps(Client client, Response response) {
//        this.client = client;
//        this.response = response;
//    }

    private String host = "http://localhost:9000";
    private String inventoryId = "bed3be78-ad5a-4b4a-aa81-38b2661ccda9";
    private String orderId = "5b8242be-7e44-48e1-b00e-210fafeb72bb";


    @Given("{string} downstream returns a status code of {int}")
    public void checkDownstreamStatus(String downstream, int status) throws JSONException {
        client.sendRequest("GET", host, downstream + "/private/status", null);

        if(status != 200) {
            String requestBody = "{\"request\": {\"method\": \"GET\"},\"response\": {\"status\": " + status + "}}";
            JSONObject json = new JSONObject(requestBody);
            String id = downstream.equals("inventory") ? inventoryId : orderId;
            client.sendRequest("PUT", host, "__admin/mappings/" + id, requestBody);
        }
    }
}
