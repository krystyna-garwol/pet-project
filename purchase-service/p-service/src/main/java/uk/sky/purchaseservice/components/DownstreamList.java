package uk.sky.purchaseservice.components;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import uk.sky.purchaseservice.models.Downstream;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "downstreams")
@Getter
@Setter
public class DownstreamList {
    private List<Downstream> urls;
}
