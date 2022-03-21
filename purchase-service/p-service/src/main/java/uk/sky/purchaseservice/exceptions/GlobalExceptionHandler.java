package uk.sky.purchaseservice.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private ResponseEntity<Error> getError(Error error) {
        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(value = StockException.class)
    private ResponseEntity<Error> handleStockException(StockException exception) {
        Error error = new Error(exception.getMessage());
        log.error(error.getMessage());
        return getError(error);
    }
}
