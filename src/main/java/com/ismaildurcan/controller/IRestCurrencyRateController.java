package com.ismaildurcan.controller;

import com.ismaildurcan.dto.CurrencyRatesResponse;

public interface IRestCurrencyRateController {

    public RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate, String endDate);

}
