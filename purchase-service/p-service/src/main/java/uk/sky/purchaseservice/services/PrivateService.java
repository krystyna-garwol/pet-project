package uk.sky.purchaseservice.services;

import org.springframework.stereotype.Service;
import uk.sky.purchaseservice.components.Client;
import uk.sky.purchaseservice.components.DownstreamList;
import uk.sky.purchaseservice.enums.HealthStatus;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class PrivateService {

    private Client client;
    private DownstreamList downstreamList;

    public PrivateService(Client client, DownstreamList downstreamList) {
        this.client = client;
        this.downstreamList = downstreamList;
    }


    public Map<String, Object> getDownstreamsStatus() {
        Map<String, Object> toReturn = new HashMap<>();
        Map<String, String> components = new HashMap<>();
        String statusToReturn = "";

        downstreamList.getUrls().forEach(downstream-> {
            HttpResponse<String> httpResponse = client.sendGetRequest(downstream.getUrl(), "private/status");
            String status = String.valueOf(httpResponse.statusCode()).equals("200") ? HealthStatus.UP.name() : HealthStatus.DOWN.name();
            components.put(downstream.getName(), status);
        });

        for (String value : components.values()) {
            statusToReturn = value.equals(HealthStatus.UP.name()) ? HealthStatus.UP.name() : HealthStatus.DOWN.name();
        }

        toReturn.put("components", components);
        toReturn.put("status", statusToReturn);

        return toReturn;
    }


}
