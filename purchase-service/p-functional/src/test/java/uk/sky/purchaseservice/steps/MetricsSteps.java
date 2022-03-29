package uk.sky.purchaseservice.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import uk.sky.purchaseservice.components.Response;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class MetricsSteps {

    private SharedSteps sharedSteps;
    private Response response;

    public MetricsSteps(SharedSteps sharedSteps, Response response) {
        this.sharedSteps = sharedSteps;
        this.response = response;
    }

    double prevValue;

    public double getMetricValue(String metricsName, String resourceName, String status, double value) {
        sharedSteps.sendRequest("GET", "private/metrics");

        String metric = metricsName + "{resourceName=\"" + resourceName + "\",status=\"" + status + "\",} " + value;
        double countValue = 0.0;

        if(response.getResponseBody().contains(metric)) {
            Pattern pattern = Pattern.compile("[0-9]\\.0");
            Matcher matcher = pattern.matcher(metric);
            if(matcher.find()) countValue = Double.parseDouble(matcher.group(0));
        } else {
            countValue = 0.0;
        }

        return countValue;
    }

    @Given("{string} with {string} and {string} has value of {double}")
    public void checkMetricInitialValue(String metricsName, String resourceName, String status, double value) {
        double retrievedValue = getMetricValue(metricsName, resourceName, status, value);
        prevValue = retrievedValue;
        assertThat(retrievedValue).isEqualTo(value);
    }

    @Then("should increase {string} with {string} and {string} by {double}")
    public void shouldIncreaseMetricsCount(String metricsName, String resourceName, String status, double value) {
        double retrievedValue = getMetricValue(metricsName, resourceName, status, value);
        double diff = retrievedValue - prevValue;
        assertThat(diff).isEqualTo(value);
    }

}
