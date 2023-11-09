package com.ghulam.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    @NotEmpty
    @Size(min = 1, max = 10, message = "firstName must be of size (1-10)")
    public String firstName;

    @NotEmpty
    @Size(min = 1, max = 10, message = "lastName must be of size (1-10)")
    public String lastName;

    @NotEmpty
    @Size(min = 3, max = 10, message = "username must be of size (3-10)")
    private String username;

    @Email(message = "please specify valid email")
    private String email;

    @NotEmpty
    @Size(min = 4, max = 8, message = "password must be of size (4-8)")
    // more pattern (if any)
    private String password;

    private String intro;
}
