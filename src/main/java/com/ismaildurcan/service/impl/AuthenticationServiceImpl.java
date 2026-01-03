package com.ismaildurcan.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ismaildurcan.dto.AuthRequest;
import com.ismaildurcan.dto.AuthResponse;
import com.ismaildurcan.dto.DtoUser;
import com.ismaildurcan.dto.RefreshTokenRequest;
import com.ismaildurcan.exception.BaseException;
import com.ismaildurcan.exception.ErrorMessage;
import com.ismaildurcan.exception.MessageType;
import com.ismaildurcan.jwt.JWTService;
import com.ismaildurcan.model.RefreshToken;
import com.ismaildurcan.model.User;
import com.ismaildurcan.repository.RefreshTokenRepository;
import com.ismaildurcan.repository.UserRepository;
import com.ismaildurcan.service.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    private User createUser(AuthRequest authRequest) {
        User user = new User();
        user.setCreateTime(new Date());
        user.setUsername(authRequest.getUsername());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        return user;
    }

    private RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setCreateTime(new Date());
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        return refreshToken;
    }

    @Override
    public DtoUser register(AuthRequest authRequest) {
        DtoUser dtoUser = new DtoUser();
        User dbUser = userRepository.save(createUser(authRequest));

        BeanUtils.copyProperties(dbUser, dtoUser);
        return dtoUser;
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    authRequest.getUsername(), authRequest.getPassword());

            authenticationProvider.authenticate(authToken);

            Optional<User> byUsername = userRepository.findByUsername(authRequest.getUsername());

            String accessToken = jwtService.generateToken(byUsername.get());
            RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(byUsername.get()));

            return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());

        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));
        }
    }

    public boolean isTokenExpired(RefreshToken refreshToken) {
        return refreshToken.getExpiredDate().before(new Date());
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest refreshToken) {
        Optional<RefreshToken> optRefreshToken = refreshTokenRepository
                .findByRefreshToken(refreshToken.getRefreshToken());
        if (optRefreshToken.isEmpty()) {
            throw new BaseException(
                    new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, refreshToken.getRefreshToken()));
        }
        if (isTokenExpired(optRefreshToken.get())) {
            throw new BaseException(
                    new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, refreshToken.getRefreshToken()));
        }

        User user = optRefreshToken.get().getUser();
        String accessToken = jwtService.generateToken(user);
        RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(user));

        return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
    }

}
