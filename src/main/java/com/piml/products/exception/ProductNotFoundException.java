package com.piml.products.exception;

import javax.persistence.EntityNotFoundException;

public class ProductNotFoundException extends EntityNotFoundException{

    public ProductNotFoundException(String message) {
        super(message);
    }
}
