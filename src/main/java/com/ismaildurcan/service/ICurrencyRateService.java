package com.ismaildurcan.service;

import com.ismaildurcan.dto.CurrencyRatesResponse;

public interface ICurrencyRateService {

    public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate);
}
