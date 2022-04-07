package uk.sky.purchaseservice.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import uk.sky.purchaseservice.components.Client;
import uk.sky.purchaseservice.components.DownstreamList;

import java.net.http.HttpResponse;
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
    private DownstreamList downstreamList;

    private PrivateService privateService;

    @BeforeEach
    public void beforeEach() {
        privateService = new PrivateService(client, downstreamList);
    }

    @Test
    public void whenGetDownstreamsStatusCalledWith200DownstreamResponse_shouldReturnUPStatus() {
        HttpResponse<String> httpResponse = mock(HttpResponse.class);

        when(client.sendGetRequest(anyString(), anyString())).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);
        Map<String, Object> retrievedReturn = privateService.getDownstreamsStatus();

        assertThat(retrievedReturn.get("status")).isEqualTo("UP");
    }

    @Test
    public void whenGetDownstreamsStatusCalledWith500DownstreamResponse_shouldReturnDOWNStatus() {
        HttpResponse<String> httpResponse = mock(HttpResponse.class);

        when(client.sendGetRequest(anyString(), anyString())).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(500);
        Map<String, Object> retrievedReturn = privateService.getDownstreamsStatus();

        assertThat(retrievedReturn.get("status")).isEqualTo("DOWN");
    }

    @Test
    public void whenGetDownstreamsStatusCalledWith500And200DownstreamResponse_shouldReturnDOWNStatus() {
        HttpResponse<String> httpResponse = mock(HttpResponse.class);

        when(client.sendGetRequest(anyString(), anyString())).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(500, 200);
        Map<String, Object> retrievedReturn = privateService.getDownstreamsStatus();

        assertThat(retrievedReturn.get("status")).isEqualTo("DOWN");
    }
}
