package com.piml.products.controller;

import com.piml.products.dto.SellerDTO;
import com.piml.products.dto.SellerResponseDTO;
import com.piml.products.service.SellerAPIService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Seller")
@RestController
@RequestMapping
public class SellerController {
    @Autowired
    private SellerAPIService sellerAPIService;

    /**
     * POST method to register a new Seller
     * @param seller receives a request containing a seller to create
     * @return the response containing a new seller created
     */
    @ApiOperation(value = "Register a new Seller")
    @PostMapping("/seller/v1")
    public ResponseEntity<SellerResponseDTO> createSeller(@Valid @RequestBody SellerDTO seller) {
        SellerDTO createdSeller = sellerAPIService.create(seller);
        SellerResponseDTO returnSeller = SellerResponseDTO.map(createdSeller);
        return new ResponseEntity<SellerResponseDTO>(returnSeller, HttpStatus.CREATED);
    }

    /**
     * GET method to find Seller by ID
     * @param id receives the corresponding id of the seller to be found
     * @return the seller with the corresponding ID
     */
    @ApiOperation(value = "Find Seller by ID")
    @GetMapping("/seller/v1/{id}")
    public ResponseEntity<SellerDTO> getSellerById(@PathVariable Long id) {
        SellerDTO foundSeller = sellerAPIService.getById(id);
        return ResponseEntity.ok(foundSeller);
    }
}
