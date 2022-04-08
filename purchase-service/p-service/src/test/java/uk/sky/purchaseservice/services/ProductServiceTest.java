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
import uk.sky.purchaseservice.models.Product;
import uk.sky.purchaseservice.utils.Env;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductServiceTest {

    @Mock
    private Client client;

    @Mock
    private DownstreamList downstreamList;

    @Mock
    private Env env;

    @InjectMocks
    private ProductService productService;

    private Downstream d1 = new Downstream("inventory", "http://a");
    private Downstream d2 = new Downstream("order", "http://b");
    private List<Downstream> downstreams = new ArrayList<>();

    @BeforeAll
    public void beforeAll() {
        downstreams.add(d1);
        downstreams.add(d2);
    }

    @Test
    public void whenCheckStockCalled_shouldReturnStockLevel() {
        int stock = 18;
        String body = "{\"stock\":20}";
        Product product = new Product("1234", 2);
        HttpResponse<String> httpResponse = mock(HttpResponse.class);

        when(downstreamList.getUrls()).thenReturn(downstreams);
        when(env.checkSpringProfile()).thenReturn("local");
        when(client.sendGetRequest(anyString(), anyString())).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn(body);
        int retrievedStock = productService.checkStock(product);

        assertThat(stock).isEqualTo(retrievedStock);
    }
}
