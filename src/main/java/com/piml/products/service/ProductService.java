package com.piml.products.service;

import com.piml.products.entity.Product;
import com.piml.products.interfaces.CategoryENUM;
import com.piml.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }

    /**
     * Create a new product.
     * @param product receives a request containing a product to create.
     * @return a product created and saved in repository.
     */
    public Product create(Product product) {
        return productRepository.save(product);
    }

    /**
     * Search Product by id.
     * @param id receives a product Id to make the search.
     * @return the product with the corresponding Id.
     */
    public Product getById(Long id)  {
        return this.productRepository.getById(id);
    }

    /**
     * Search all products by List<Long> of Ids.
     * @param productIds receives a List<Long> of productsId to be searched.
     * @return all products in the list of ids
     */
    public List<Product> getAllProducts(List<Long> productIds) {
        if(productIds != null) {
            return productRepository.findAllById(productIds);
        }
        List<Product> productList = productRepository.findAll();
        if(productList == null) {
            return new ArrayList<Product>();
        }
        return productList;
    }

    /**
     * Search all products by CategoryENUM of category.
     * @param category receives a CategoryENUM of category to be searched.
     * @return all products that contain the specified category
     */
    public List<Product> getByCategory(CategoryENUM category) {
       return productRepository.findByCategory(category.getCategoryDescription());
    }
}
