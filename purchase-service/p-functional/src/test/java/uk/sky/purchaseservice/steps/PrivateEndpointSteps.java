package uk.sky.purchaseservice.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import uk.sky.purchaseservice.components.Client;
import uk.sky.purchaseservice.components.Response;

import static org.assertj.core.api.Assertions.assertThat;


public class PrivateEndpointSteps {

    private Client client;
    private Response response;

    public PrivateEndpointSteps(Client client, Response response) {
        this.client = client;
        this.response = response;
    }

    @When("{string} request is made to the {string} endpoint")
    public void sendRequest(String method, String endpoint)  {
      client.sendRequest(method, endpoint);
    }

    @Then("should return a status code of {int}")
    public void shouldReturnStatusCode(int status) {
        assertThat(response.getStatusCode()).isEqualTo(status);
    }

    @And("should return response body containing {string}")
    public void shouldReturnResponseBody(String responseBody) {
        assertThat(response.getResponseBody()).isEqualTo(responseBody);
    }

}