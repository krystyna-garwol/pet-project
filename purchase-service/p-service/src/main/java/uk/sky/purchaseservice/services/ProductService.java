package uk.sky.purchaseservice.services;

import org.springframework.stereotype.Service;
import uk.sky.purchaseservice.components.Client;
import uk.sky.purchaseservice.exceptions.StockException;
import uk.sky.purchaseservice.models.Product;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProductService {

    private Client client;

    public ProductService(Client client) {
        this.client = client;
    }

    public int checkStock(Product product) {
        int retrievedStock = callInventoryService(product.getProductId());
        int stockDiff = retrievedStock - product.getBasket();

        if(stockDiff > 0 || product.getBasket() == retrievedStock) {
            return stockDiff;
        }
        throw new StockException(retrievedStock);
    }

    private int callInventoryService(String productId) {
        int stock = 0;
        String body = client.sendGetRequest("inventory/stock/" + productId).body().toString();
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(body);
        if(matcher.find()) stock = Integer.parseInt(matcher.group(0));
        return stock;
    }
}
