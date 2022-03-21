package uk.sky.inventoryservice.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import uk.sky.inventoryservice.services.ProductService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void whenGetStockCalled_shouldReturnStock() throws Exception {
        int stock = 20;

        when(productService.getStock(anyString())).thenReturn(Optional.of(stock));

        mockMvc.perform(get("/inventory/stock/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stock").value(stock));
    }
}
