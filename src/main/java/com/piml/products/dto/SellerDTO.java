package com.piml.products.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerDTO {
    private String name;
    private String cpf;
    private String username;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String email;

    private final String role = "seller";

    @Override
    public String toString() {
        return "SellerDTO{" +
                "name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
