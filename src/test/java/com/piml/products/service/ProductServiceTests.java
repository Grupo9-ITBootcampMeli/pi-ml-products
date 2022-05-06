package com.piml.products.service;

import com.piml.products.entity.Product;
import com.piml.products.interfaces.CategoryENUM;
import com.piml.products.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductServiceTests {
    private ProductService productService;
    private ProductRepository productRepositoryMock;

    @BeforeEach
    public void setupTests() {
        productRepositoryMock = Mockito.mock(ProductRepository.class);
        productService = new ProductService(productRepositoryMock);
    }

    @Test
    public void shouldCreateProduct() {

        Product createdProduct = Product.builder()
                .name("Test Product")
                .description("Description test")
                .minimumTemperature(10.0)
                .size(11.0)
                .price(new BigDecimal(10))
                .category("Category X")
                .sellerId(1L)
                .build();

        Mockito.when(productRepositoryMock.save(createdProduct)).thenReturn(createdProduct);

        Assertions.assertDoesNotThrow(() -> {
            Product result = productService.create(createdProduct);
            Assertions.assertEquals(createdProduct, result);
        });
    }

    @Test
    public void shouldGetProduct() {
        Product createdProduct = Product.builder()
                .name("Test Product")
                .description("Description test")
                .minimumTemperature(10.0)
                .size(11.0)
                .price(new BigDecimal(10))
                .category("Category X")
                .sellerId(1L)
                .build();
        Mockito.when(productRepositoryMock.getById(createdProduct.getId())).thenReturn(createdProduct);

        Assertions.assertDoesNotThrow(() -> {

            Assertions.assertEquals(createdProduct, productService.getById(createdProduct.getId()));
        });
    }

    @Test
    @DisplayName("Should return all products without filter")
    public void shouldGetAllProducts() {
        List<Product> productList = Arrays.asList(
                Product.builder()
                        .name("Test Product")
                        .description("Description test")
                        .minimumTemperature(10.0)
                        .size(11.0)
                        .price(new BigDecimal(10))
                        .category("Category X")
                        .sellerId(1L)
                        .build(),
                Product.builder()
                        .name("Test Product2")
                        .description("Description test")
                        .minimumTemperature(10.0)
                        .size(11.0)
                        .price(new BigDecimal(10))
                        .category("Category X")
                        .sellerId(1L)
                        .build()
        );

        Mockito.when(productRepositoryMock.findAll()).thenReturn(productList);

        Assertions.assertDoesNotThrow(() -> {
            List<Product> result = productService.getAllProducts(null);
            Assertions.assertEquals(productList, result);
        });
    }

    @Test
    @DisplayName("Should return only products with specific id")
    public void shouldGetAllProductsWithId() {
        List<Product> productList = new ArrayList<>();

        List<Long> listOfIds = new ArrayList<>();
        listOfIds.add(2L);
        listOfIds.add(3L);
        Mockito.when(productRepositoryMock.findAllById(listOfIds)).thenReturn(productList);

        Assertions.assertDoesNotThrow(() -> {
            List<Product> result = productService.getAllProducts(listOfIds);
            Assertions.assertEquals(productList, result);
            Mockito.verify(productRepositoryMock, Mockito.times(1)).findAllById(listOfIds);
        });
    }

    @Test
    @DisplayName("Should return a list of products by category")
    public void shouldGetAllProductsByCategory() {
        List<Product> productList = new ArrayList<>();
        CategoryENUM categoryFilter = CategoryENUM.valueOf("FF");

        Mockito.when(productRepositoryMock.findByCategory(categoryFilter.getCategoryDescription())).thenReturn(productList);

        Assertions.assertDoesNotThrow(() -> {
            List<Product> result = productService.getByCategory(categoryFilter);
            Assertions.assertEquals(productList, result);
            Mockito.verify(productRepositoryMock, Mockito.times(1)).findByCategory(categoryFilter.getCategoryDescription());
        });
    }
}
