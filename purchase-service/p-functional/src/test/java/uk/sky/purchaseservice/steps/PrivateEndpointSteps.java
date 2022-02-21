package uk.sky.purchaseservice.steps;

import io.cucumber.java.en.Given;


public class PrivateEndpointSteps {

    @Given("get status endpoint is called")
    public void getStatusCalled() {
        System.out.println("status called");
    }

}
