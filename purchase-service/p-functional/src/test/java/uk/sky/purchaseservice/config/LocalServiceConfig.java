package uk.sky.purchaseservice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import uk.sky.purchaseservice.PurchaseServiceApplication;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class LocalServiceConfig {

    private ConfigurableApplicationContext serviceApplicationContext;

    @PostConstruct
    public void startUp() {
        serviceApplicationContext = SpringApplication.run(PurchaseServiceApplication.class);
    }

    @PreDestroy
    public void shutDown() {
        serviceApplicationContext.close();
    }

}
