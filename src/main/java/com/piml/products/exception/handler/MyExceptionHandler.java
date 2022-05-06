package com.piml.products.exception.handler;

import com.piml.products.exception.SellerAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice(annotations = RestController.class)
public class MyExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<?> handleProductException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }
    @ExceptionHandler(SellerAlreadyExistsException.class)
    protected ResponseEntity<?> sellerAlreadyExistsException() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Seller already registered");
    }
}

