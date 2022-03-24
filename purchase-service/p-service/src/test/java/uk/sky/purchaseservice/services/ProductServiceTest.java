package uk.sky.purchaseservice.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.sky.purchaseservice.components.Client;
import uk.sky.purchaseservice.models.Product;

import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private Client client;

    @InjectMocks
    private ProductService productService;

    @Test
    public void whenCheckStockCalled_shouldReturnStockLevel() {
        int stock = 18;
        String body = "{\"stock\":20}";
        Product product = new Product("1234", 2);
        HttpResponse httpResponse = mock(HttpResponse.class);

        when(client.sendGetRequest(anyString())).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn(body);
        int retrievedStock = productService.checkStock(product);

        assertThat(stock).isEqualTo(retrievedStock);
    }
}
