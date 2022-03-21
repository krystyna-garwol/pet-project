package uk.sky.inventoryservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.sky.inventoryservice.services.ProductService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/inventory")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/stock/{id}")
    public ResponseEntity<Map<String, Integer>> getStock(@PathVariable String id) {
        Map<String, Integer> map = new HashMap<>();
        int stock = productService.getStock(id).get();
        map.put("stock", stock);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
