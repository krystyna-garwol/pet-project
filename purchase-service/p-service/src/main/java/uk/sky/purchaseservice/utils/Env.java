package uk.sky.purchaseservice.utils;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Env {

    private Environment environment;

    public Env(org.springframework.core.env.Environment environment) {
        this.environment = environment;
    }

    public String checkSpringProfile() {
        String localProfile = "";

        String[] activeProfiles = environment.getActiveProfiles();
        for(String profile : activeProfiles) {
            localProfile = profile;
        }
        return localProfile;
    }
}
