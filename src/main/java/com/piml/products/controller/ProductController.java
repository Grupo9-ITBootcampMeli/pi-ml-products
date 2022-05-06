package com.piml.products.controller;

import com.piml.products.dto.ProductDTO;
import com.piml.products.entity.Product;
import com.piml.products.interfaces.CategoryENUM;
import com.piml.products.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Product")
@RestController
@RequestMapping
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * POST method for create a new product
     * @param dto receives a request containing a product to create
     * @return the response containing a new product created
     */

    @ApiOperation(value = "Register new Product")
    @PostMapping("/fresh-products")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO dto) {
        Product product = dto.map();
        ProductDTO createdProduct = ProductDTO.map(productService.create(product));
        return new ResponseEntity<ProductDTO>(createdProduct, HttpStatus.CREATED);
    }

    /**
     * GET method to find a product by ID
     * @param id receives the corresponding id of the product to be found
     * @return the product with the corresponding ID
     */
    @ApiOperation(value = "Find Product by ID")
    @GetMapping("/fresh-products/v1/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id){
        Product product = productService.getById(id);
        ProductDTO convertedProduct = ProductDTO.map(product);
        return ResponseEntity.ok(convertedProduct);
    }

    /**
     * GET method to return all products registered
     * @param productIds receives a List<Long> of product ids to find
     * @return a list containing all products corresponding IDs
     */
    @ApiOperation(value = "List all products optionally by IDs")
    @GetMapping("/fresh-products/v1")
    public ResponseEntity<List<ProductDTO>> getAllProducts(@RequestParam(name = "products", required = false) List<Long> productIds)  {
        List<Product> productList = productService.getAllProducts(productIds);
        return ResponseEntity.ok(ProductDTO.map(productList));
    }

    /**
     * GET method to find products by category
     * @param category receives a CategoryENUM category containing category to find
     * @return all products where contains their category
     */
    @ApiOperation(value = "Find Products by category")
    @GetMapping("/fresh-products/v1/list")
    public ResponseEntity<List<ProductDTO>> getByCategory(@RequestParam(name = "category") CategoryENUM category) {
        List<Product> productList = productService.getByCategory(category);
        return ResponseEntity.ok(ProductDTO.map(productList));
    }
}
