package uk.sky.purchaseservice.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.sky.purchaseservice.components.Client;
import uk.sky.purchaseservice.components.DownstreamList;
import uk.sky.purchaseservice.models.Downstream;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PrivateServiceTest {

    @Mock
    private Client client;

    @Mock
    private DownstreamList downstreamList;

    @InjectMocks
    private PrivateService privateService;

    private Downstream d1 = new Downstream("inventory", "http://a");
    private Downstream d2 = new Downstream("order", "http://b");
    private List<Downstream> downstreams = new ArrayList<>();

    @BeforeAll
    public void beforeAll() {
        downstreams.add(d1);
        downstreams.add(d2);
    }

    @Test
    public void whenGetDownstreamsStatusCalledWith200DownstreamResponse_shouldReturnUPStatus() {
        HttpResponse<String> httpResponse = mock(HttpResponse.class);

        when(downstreamList.getUrls()).thenReturn(downstreams);
        when(client.sendGetRequest(anyString(), anyString())).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);
        Map<String, Object> retrievedReturn = privateService.getDownstreamsStatus();

        assertThat(retrievedReturn.get("status")).isEqualTo("UP");
    }

    @Test
    public void whenGetDownstreamsStatusCalledWith500DownstreamResponse_shouldReturnDOWNStatus() {
        HttpResponse<String> httpResponse = mock(HttpResponse.class);

        when(downstreamList.getUrls()).thenReturn(downstreams);
        when(client.sendGetRequest(anyString(), anyString())).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(500);
        Map<String, Object> retrievedReturn = privateService.getDownstreamsStatus();

        assertThat(retrievedReturn.get("status")).isEqualTo("DOWN");
    }

    @Test
    public void whenGetDownstreamsStatusCalledWith500And200DownstreamResponse_shouldReturnDOWNStatus() {
        HttpResponse<String> httpResponse = mock(HttpResponse.class);

        when(downstreamList.getUrls()).thenReturn(downstreams);
        when(client.sendGetRequest(anyString(), anyString())).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(500, 200);
        Map<String, Object> retrievedReturn = privateService.getDownstreamsStatus();

        assertThat(retrievedReturn.get("status")).isEqualTo("DOWN");
    }
}
