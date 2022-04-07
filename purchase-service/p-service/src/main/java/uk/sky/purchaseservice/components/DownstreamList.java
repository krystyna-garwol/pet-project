package uk.sky.purchaseservice.components;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import uk.sky.purchaseservice.models.Downstream;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "downstreams")
@Data
public class DownstreamList {
    private List<Downstream> urls;
}
