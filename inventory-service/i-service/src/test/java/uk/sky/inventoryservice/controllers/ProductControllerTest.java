package uk.sky.inventoryservice.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.sky.inventoryservice.services.ProductService;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    public void whenGetStockCalled_shouldReturnStock() throws Exception {
        int stock = 20;
        String productId = "1234";

        when(productService.getStock(anyString())).thenReturn(java.util.Optional.of(stock));
        ResponseEntity<Map<String, Integer>> response = productController.getStock(productId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting("stock").isEqualTo(stock);
    }
}
