package uk.sky.purchaseservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.sky.purchaseservice.models.Product;
import uk.sky.purchaseservice.services.ProductService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/stock")
    public ResponseEntity<Map<String, Integer>> checkStock(@RequestBody Product product) {
        int stock = productService.checkStock(product);
        Map<String, Integer> map = new HashMap<>();
        map.put("currentStock", stock);
        return ResponseEntity.ok().body(map);
    }
}
