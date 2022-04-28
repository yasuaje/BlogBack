package com.asuajeyeison.blogApiRest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTAuthResponseDTO {

    private String tokenDeAcceso;
    public String  tipoDeToken = "Bearer";

    public JWTAuthResponseDTO(String tokenDeAcceso, String tipoDeToken) {
        tokenDeAcceso = tokenDeAcceso;
        this.tipoDeToken = tipoDeToken;
    }

    public JWTAuthResponseDTO(String tokenDeAcceso) {
        this.tokenDeAcceso = tokenDeAcceso;
    }
}
