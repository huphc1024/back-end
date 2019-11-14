package com.report.entity.response;

import com.report.utils.Constants;

public class LoginResponse {
    private String accessToken;
    private String tokenType = Constants.TOKEN_PREFIX;

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public LoginResponse() {
        super();
    }
}
