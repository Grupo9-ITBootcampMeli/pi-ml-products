package com.piml.products.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellerResponseDTO {
    private String name;
    private String cpf;
    private String username;
    private String email;
    private String role;

    public static SellerResponseDTO map(SellerDTO dto) {
        return SellerResponseDTO.builder()
                .name(dto.getName())
                .cpf(dto.getCpf())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .role(dto.getRole())
                .build();

    }
}
