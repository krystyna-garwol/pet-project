package uk.sky.purchaseservice.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = ComponentConfig.class)
public class CucumberConfig {}
