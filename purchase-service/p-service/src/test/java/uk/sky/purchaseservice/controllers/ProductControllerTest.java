package uk.sky.purchaseservice.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.sky.purchaseservice.models.Product;
import uk.sky.purchaseservice.services.ProductService;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private Product product = new Product("1234", 2);

    @Test
    public void whenCheckStockEndpointCalled_shouldReturnAppropriateResponse() {
        int stockDiff = 5;

        when(productService.checkStock(any())).thenReturn(stockDiff);
        ResponseEntity<Map<String, Integer>> response = productController.checkStock(product);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting("currentStock").isEqualTo(stockDiff);
    }
}
