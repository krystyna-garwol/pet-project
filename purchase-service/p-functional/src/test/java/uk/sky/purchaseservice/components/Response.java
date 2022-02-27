package uk.sky.purchaseservice.components;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Response {

    private int statusCode;
    private String responseBody;

}
