package uk.sky.purchaseservice.filters;

import io.prometheus.client.Histogram;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uk.sky.purchaseservice.config.MetricsConfig;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApplicationLatencyFilter extends OncePerRequestFilter {

    private MetricsConfig metrics;

    public ApplicationLatencyFilter(MetricsConfig metrics) {
        this.metrics = metrics;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String status = String.valueOf(response.getStatus());
        String resourceName = request.getMethod() + "-" + request.getRequestURI();

        if(!request.getRequestURI().equals("/private/metrics")) {
            Histogram.Timer requestTimer = metrics.applicationLatencyMetrics()
                    .labels(resourceName, String.valueOf(response.getStatus()))
                    .startTimer();

            requestTimer.observeDuration();
        }

        filterChain.doFilter(request, response);
    }
}
