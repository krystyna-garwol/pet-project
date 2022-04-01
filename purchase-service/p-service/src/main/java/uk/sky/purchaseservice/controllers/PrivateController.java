package uk.sky.purchaseservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.sky.purchaseservice.services.PrivateService;

import java.util.Map;

@RestController
@RequestMapping("/private")
public class PrivateController {

    private PrivateService privateService;

    public PrivateController(PrivateService privateService) {
        this.privateService = privateService;
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/healthcheck")
    public ResponseEntity<Map<String, Object>> getDownstreamsStatus() {
        Map<String, Object> map = privateService.getDownstreamsStatus();
        return ResponseEntity.ok(map);
    }
}
