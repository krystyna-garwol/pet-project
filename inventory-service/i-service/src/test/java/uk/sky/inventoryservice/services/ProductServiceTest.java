package uk.sky.inventoryservice.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.sky.inventoryservice.repositories.ProductRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;


    @Test
    public void whenGetStockCalled_shouldReturnStock() {
        int stock = 20;
        String productId = "3";

        when(productRepository.findStockByProductId(productId)).thenReturn(Optional.of(stock));
        int retrievedStock = productService.getStock(productId).get();

        assertThat(stock).isEqualTo(retrievedStock);
    }
}
