package com.winnguyen1905.technologystore.service;

import com.winnguyen1905.technologystore.model.request.LoginRequest;
import com.winnguyen1905.technologystore.model.request.RegisterRequest;
import com.winnguyen1905.technologystore.model.response.AuthenResponse;

public interface IAuthService {
    public AuthenResponse handleLogin(LoginRequest loginRequest);
    public AuthenResponse handleRegister(RegisterRequest registerRequest);
    public AuthenResponse handleGetAuthenResponseByUsernameAndRefreshToken(String username, String refreshToken);
    public Void handleUpdateUsersRefreshToken(String username, String refreshToken);
    public Void handleLogout(String username);
}