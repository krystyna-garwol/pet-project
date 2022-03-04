package uk.sky.purchaseservice.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PrivateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenGetStatusCalled_shouldReturnAppropriateResponse() throws Exception {
        mockMvc.perform(get("/private/status"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("ok"));
    }

    @Test
    public void whenMetricsEndpointCalled_shouldReturnMetrics() throws Exception {
        mockMvc.perform(get("/private/metrics"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("jvm_memory_committed_bytes")))
                .andExpect(content().string(containsString("jvm_memory_used_bytes")));
    }

    @Test
    public void whenInfoEndpointCalled_shouldReturnAppInfo() throws Exception {
        mockMvc.perform(get("/private/info"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.app.name").value("Pet Project Purchase Service"))
                .andExpect(jsonPath("$.app.java.source").value(11))
                .andExpect(jsonPath("$.build.artifact").value("p-service"));
    }
}
