package uk.sky.purchaseservice.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.sky.purchaseservice.components.Client;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PrivateServiceTest {

    @MockBean
    private Client client;

    @Autowired
    private PrivateService privateService;

    @Test
    public void whenGetDownstreamsStatus_shouldReturnAppropriateResponse() {
        HttpResponse httpResponse = mock(HttpResponse.class);
        Map<String, String> components = new HashMap<>();
        components.put("inventory", "DOWN");
        components.put("order", "DOWN");
        Map<String, Object> toReturn = new HashMap<>();
        toReturn.put("components", components);
        toReturn.put("status", "DOWN");

        when(client.sendGetRequest(anyString(), anyString())).thenReturn(httpResponse);
        Map<String, Object> retrievedReturn = privateService.getDownstreamsStatus();

        assertThat(toReturn).isEqualTo(retrievedReturn);
    }
}
