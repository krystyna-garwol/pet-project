package uk.sky.purchaseservice.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import uk.sky.purchaseservice.components.Client;

import java.io.IOException;


public class PrivateEndpointSteps {

    @Autowired
    private Client client;


    @When("{string} request is made to the {string} endpoint")
    public void sendRequest(String method, String endpoint) throws IOException, InterruptedException {
      client.sendRequest(method, endpoint);
    }

    @Then("should return a status code of {int}")
    public void shouldReturnStatusCode(int status) {
    }

    @And("should return response body containing {string}")
    public void shouldReturnResponseBody(String response) {
    }

}
