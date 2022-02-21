package uk.sky.purchaseservice.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
public class PrivateControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void whenGetStatusCalled_shouldReturnAppropriateResponse() throws Exception {
        mockMvc.perform(get("/private/status"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("ok"));
    }
}
