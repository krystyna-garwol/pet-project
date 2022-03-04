package uk.sky.purchaseservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uk.sky.purchaseservice.models.BasketItem;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PetShopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private List<BasketItem> basketItems = new ArrayList<>();
    private BasketItem basketItem = new BasketItem("Purina", "cat");

    @BeforeAll
    public void beforeAll() {
        basketItems.add(basketItem);
    }

    @Test
    public void whenCheckStockEndpointCalled_shouldReturnAppropriateResponse() throws Exception {
        mockMvc.perform(post("/pet/shop/basket/stock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(basketItems)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Item in stock"));
    }
}
