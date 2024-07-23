package com.winnguyen1905.technologystore.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
public class RegisterRequest {
    @NotBlank
    @Email(message = "Email format must be 'prefix@subfix.domain'")
    private String email;

    @Size(min = 8, message = "The password must be length  > 7")
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
}