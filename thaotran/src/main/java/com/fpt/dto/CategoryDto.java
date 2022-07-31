package com.fpt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    @NotBlank(message = "code_category cannot be empty")
    @Pattern(regexp = "^CAT+[0-9]{3}", message = "code is invalid!")
    private String code;

    @NotBlank(message = "code_category cannot be empty")
    private String categoryName;

    private Integer createBy;

    private Integer updateBy;

    private Instant createAt;

    private Instant updateAt;

}
