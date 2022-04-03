package uk.sky.purchaseservice.services;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import uk.sky.purchaseservice.components.Client;
import uk.sky.purchaseservice.components.DownstreamList;
import uk.sky.purchaseservice.exceptions.StockException;
import uk.sky.purchaseservice.models.Product;

import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProductService {

    private Client client;
    private DownstreamList downstreamList;
    private Environment environment;

    public ProductService(Client client, DownstreamList downstreamList, Environment environment) {
        this.client = client;
        this.downstreamList = downstreamList;
        this.environment = environment;
    }

    public int checkStock(Product product) {
        int retrievedStock = callInventoryService(product.getProductId());
        int stockDiff = retrievedStock - product.getBasket();

        if(stockDiff > 0 || product.getBasket() == retrievedStock) {
            return stockDiff;
        } else {
            throw new StockException(retrievedStock);
        }
    }

    private int callInventoryService(String productId) {
        String inventoryHost = downstreamList.getUrls().get(0).getUrl();
        int stock = 0;
        String endpoint = checkSpringProfile().equals("local") ? "stock/" + productId : "inventory/stock/" + productId;

        HttpResponse<String> httpResponse = client.sendGetRequest(inventoryHost, endpoint);
        String body = httpResponse.body();

        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(body);
        if(matcher.find()) stock = Integer.parseInt(matcher.group(0));
        return stock;
    }

    private String checkSpringProfile() {
        String localProfile = "";

        String[] activeProfiles = environment.getActiveProfiles();
        for(String profile : activeProfiles) {
            localProfile = profile;
        }
        return localProfile;
    }
}
