package com.ismaildurcan.controller;

import com.ismaildurcan.dto.AuthRequest;
import com.ismaildurcan.dto.DtoUser;
import com.ismaildurcan.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

    public RootEntity<DtoUser> register(AuthRequest input);

    public RootEntity<?> authenticate(AuthRequest input);

    public RootEntity<?> refreshToken(RefreshTokenRequest refreshToken);

}
