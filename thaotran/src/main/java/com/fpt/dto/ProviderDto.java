package com.fpt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProviderDto {

    private Integer idProvider;

    @NotBlank(message = "'Name' cannot be empty")
    @Size(max = 30, message = "Name must smaller or equal {max} character")
    private String nameProvider;

    @NotBlank(message = "'Type' cannot be empty")
    @Size(max = 1, message = "Type Provider must be a character")
    private String typeProvider;


}
