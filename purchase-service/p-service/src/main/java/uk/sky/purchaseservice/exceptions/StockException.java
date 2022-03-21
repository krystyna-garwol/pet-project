package uk.sky.purchaseservice.exceptions;

import lombok.Getter;

@Getter
public class StockException extends RuntimeException {
    private int itemsAvailable;
    private String message;

    public StockException(int itemsAvailable) {
        this.message = "There is not enough stock available right now, however you can still order: " + itemsAvailable + " items.";
    }
}
