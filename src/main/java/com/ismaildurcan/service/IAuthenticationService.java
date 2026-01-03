package com.ismaildurcan.service;

import com.ismaildurcan.dto.AuthRequest;
import com.ismaildurcan.dto.AuthResponse;
import com.ismaildurcan.dto.DtoUser;
import com.ismaildurcan.dto.RefreshTokenRequest;

public interface IAuthenticationService {

    public DtoUser register(AuthRequest authRequest);

    public AuthResponse authenticate(AuthRequest authRequest);

    public AuthResponse refreshToken(RefreshTokenRequest refreshToken);

}
