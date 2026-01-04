package com.ismaildurcan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class TcmbConfig {

    @Value("${TCMB.TOKEN}")
    private String apiToken;

    @Value("${TCMB.URL}")
    private String apiUrl;
}
