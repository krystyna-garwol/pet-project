package uk.sky.purchaseservice.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import org.json.JSONException;
import org.json.JSONObject;
import uk.sky.purchaseservice.components.Client;
import uk.sky.purchaseservice.components.Response;

public class PrivateSteps {

    private Client client;
    private Response response;

    public PrivateSteps(Client client, Response response) {
        this.client = client;
        this.response = response;
    }

    private String host = "http://localhost:9000";
    private String inventoryId = "bed3be78-ad5a-4b4a-aa81-38b2661ccda9";
    private String orderId = "5b8242be-7e44-48e1-b00e-210fafeb72bb";

    @After
    public void resetMappings() {
        client.sendRequest("POST", host, "/__admin/mappings/reset", null);
    }

    @Given("{string} downstream returns a status code of {int}")
    public void checkDownstreamStatus(String downstream, int status) throws JSONException {
        if(status == 200) {
            client.sendRequest("GET", host, downstream + "/private/status", null);
        } else {
            String requestBody = "{\"request\": {\"method\": \"GET\"},\"response\": {\"status\": " + status + "}}";
            JSONObject json = new JSONObject(requestBody);
            String id = downstream.equals("inventory") ? inventoryId : orderId;
            client.sendRequest("PUT", host, "__admin/mappings/" + id, requestBody);
        }
    }

    @Given("{string} downstream returns {int} for product id {int}")
    public void checkInventoryDownstreamStatusAndStock(String downstream, int status, int productId) throws JSONException {
        client.sendRequest("GET", host, downstream + "/stock" + productId, null);
    }
}
