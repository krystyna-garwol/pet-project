package uk.sky.purchaseservice.config;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Histogram;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

    @Bean
    public CollectorRegistry collectorRegistry() {
        return new CollectorRegistry();
    }

    @Bean
    public Histogram applicationLatencyMetrics(CollectorRegistry collectorRegistry) {
        return Histogram.build()
                .name("application_latency_seconds")
                .help("Application latency in seconds")
                .buckets(0.01, 0.1, 0.3, 0.5, 1.0, 5.0, 10.0)
                .labelNames("resourceName", "status")
                .register(collectorRegistry);
    }
}
