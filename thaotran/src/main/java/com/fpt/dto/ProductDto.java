package com.fpt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Integer idProduct;
    @NotNull(message = "'Name' cannot be null")
    @Size(max = 20, message = "Name must smaller or equal {max} character")
    private String nameProduct;
    @NotBlank(message = "category cannot be empty")
    private String category;
    @NotNull(message = "Quantity cannot be null")
    private Integer quantity;
    @NotBlank(message = "Image cannot be empty")
    private String imagePath;

    @NotNull(message = "producer cannot be null")
    private Integer producer;
    @NotNull(message = "supplier cannot be null")
    private Integer supplier;

    private Integer createBy;

    private Integer updateBy;

    private Instant createAt;

    private Instant updateAt;


}
