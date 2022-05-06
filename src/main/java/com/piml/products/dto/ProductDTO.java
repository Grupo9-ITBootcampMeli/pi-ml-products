package com.piml.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.piml.products.entity.Product;
import com.piml.products.interfaces.CategoryENUM;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Minimum temperature is required")
    private Double minimumTemperature;

    @NotNull(message = "size is required")
    private Double size;

    private Long sellerId;

    @NotNull(message = "price is required")
    private BigDecimal price;

    @NotNull(message = "category is required")
    private CategoryENUM category;

    public Product map() {
        return Product.builder().name(this.name)
                .description(this.description)
                .minimumTemperature(this.minimumTemperature)
                .size(this.size)
                .sellerId(this.sellerId)
                .price(this.price)
                .category(this.category.getCategoryDescription()).build();
    }

    public static ProductDTO map(Product product) {
        return ProductDTO.builder()
                .name(product.getName())
                .id(product.getId())
                .description(product.getDescription())
                .minimumTemperature(product.getMinimumTemperature())
                .size(product.getSize())
                .sellerId(product.getSellerId())
                .price(product.getPrice())
                .category(CategoryENUM.convertToEnum(product.getCategory())).build();
    }

    public static List<ProductDTO> map(List<Product> productList) {
        return productList.stream().map(ProductDTO::map).collect(Collectors.toList());
    }
}
