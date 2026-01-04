package com.ismaildurcan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ismaildurcan.config.TcmbConfig;
import com.ismaildurcan.dto.CurrencyRatesResponse;
import com.ismaildurcan.exception.BaseException;
import com.ismaildurcan.exception.ErrorMessage;
import com.ismaildurcan.exception.MessageType;
import com.ismaildurcan.service.ICurrencyRateService;

@Service
public class CurrencyRateServiceImpl implements ICurrencyRateService {

    @Autowired
    private TcmbConfig tcmbConfig;

    @Override
    public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate) {
        String series = "TP.DK.USD.A";
        String type = "type=json";

        String endPoint = tcmbConfig.getApiUrl() + "series=" + series + "&startDate=" + startDate + "&endDate="
                + endDate + "&" + type;

        HttpHeaders headers = new HttpHeaders();
        headers.set("key", tcmbConfig.getApiToken());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<CurrencyRatesResponse> response = restTemplate.exchange(endPoint, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<CurrencyRatesResponse>() {
                    });
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURRED, null));
        }
        return null;

    }

}
