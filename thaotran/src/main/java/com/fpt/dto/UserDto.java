package com.fpt.dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {

    private Long userId;

    @NotBlank(message = "userName cannot be empty")
    @Size(max = 30, message = "UserName must smaller or equal {max} character")
    private String userName;

    @NotBlank(message = "fullName cannot be empty")
    @Size(max = 30, message = "FullName must smaller or equal {max} character")
    private String fullName;

    @NotBlank(message = "email cannot be empty")
    @Email(message = "email is invalid")
    private String email;

    @NotBlank(message = "password cannot be empty")
    @Size(max = 200, message = "Password must smaller or equal {max} character")
    private String password;

    @NotBlank(message = "role cannot be empty")
    @Size(min = 1, max = 1, message = "role must be a character")
    private String role;


}
