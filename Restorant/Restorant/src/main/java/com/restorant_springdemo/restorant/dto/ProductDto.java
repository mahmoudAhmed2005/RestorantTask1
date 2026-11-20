
package com.restorant_springdemo.restorant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    private Long id;

    @NotBlank(message = "product.name.required")
    @Size(min = 2, max = 100, message = "product.name.size")
    private String name;

    private String imagePath;

    @NotBlank(message = "product.description.required")
    private String description;

    @NotNull(message = "product.price.required")
    @DecimalMin(
            value = "0.01",
            message = "product.price.min"
    )
    private Double price;

    @NotNull(message = "product.category.required")
    @Positive(message = "product.category.positive")
    private Long categoryId;

    private String categoryName;
}
