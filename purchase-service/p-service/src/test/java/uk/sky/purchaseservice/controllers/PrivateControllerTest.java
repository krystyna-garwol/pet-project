package uk.sky.purchaseservice.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.sky.purchaseservice.services.PrivateService;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PrivateControllerTest {

    @Mock
    private PrivateService privateService;

    @InjectMocks
    private PrivateController privateController;

    @Test
    public void whenGetStatusCalled_shouldReturnAppropriateResponse() {
        ResponseEntity<String> response = privateController.getStatus();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("ok");
    }

    @Test
    public void whenGetDownstreamsStatusCalled_shouldReturnAppropriateResponse() throws JSONException {
        JSONObject component = new JSONObject();
        component.put("inventory", "UP");
        component.put("order", "UP");

        Map<String, Object> map = new HashMap<>();
        map.put("components", component);
        map.put("status", "UP");

        when(privateService.getDownstreamsStatus()).thenReturn(map);
        ResponseEntity<Map<String, Object>> retrievedMap = privateController.getDownstreamsStatus();

        assertThat(retrievedMap.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(retrievedMap.getBody()).extracting("components").isEqualTo(component);
    }
}
