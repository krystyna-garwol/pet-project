package uk.sky.purchaseservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.sky.purchaseservice.models.BasketItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pet/shop")
public class PetShopController {

    @PostMapping("/basket/stock")
    public ResponseEntity<Map<String, String>> checkStock(@RequestBody List<BasketItem> items) {
        Map<String, String> map = new HashMap<>();
        map.put("message", "Item in stock");
        return ResponseEntity.ok().body(map);
    }
}
