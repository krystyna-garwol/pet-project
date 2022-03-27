package uk.sky.purchaseservice.filters;

import io.prometheus.client.Histogram;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationLatencyFilterTest {

    @Mock
    private Histogram histogram;

    @InjectMocks
    private ApplicationLatencyFilter applicationLatencyFilter;

    @Test
    public void whenAnyEndpointCalled_shouldCreateApplicationLatencyMetrics() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/private/status");
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain chain = new MockFilterChain();
        String resourceName = request.getMethod() + "-" + request.getRequestURI();
        Histogram.Child child = mock(Histogram.Child.class);
        Histogram.Timer timer = mock(Histogram.Timer.class);

        when(histogram.labels(resourceName, String.valueOf(response.getStatus()))).thenReturn(child);
        when(child.startTimer()).thenReturn(timer);
        applicationLatencyFilter.doFilterInternal(request, response, chain);

        assertThat(response.getStatus()).isEqualTo(200);
        verify(histogram, times(1)).labels(resourceName, "200");
    }
}
