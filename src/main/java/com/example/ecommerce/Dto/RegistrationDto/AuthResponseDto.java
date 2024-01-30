package com.example.ecommerce.Dto.RegistrationDto;

public class AuthResponseDto {

    private String accessToken;
    private String tokenType = "Bearer ";

    public AuthResponseDto(String accessToken){
        this.accessToken = accessToken;
    }
    public String getTokenType() {
        return tokenType;
    }
    public String getAccessToken(){
        return accessToken;
    }
}
