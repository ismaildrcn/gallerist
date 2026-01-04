package com.ismaildurcan.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ismaildurcan.controller.IRestCurrencyRateController;
import com.ismaildurcan.controller.RestBaseController;
import com.ismaildurcan.controller.RootEntity;
import com.ismaildurcan.dto.CurrencyRatesResponse;
import com.ismaildurcan.service.ICurrencyRateService;

@RestController
@RequestMapping("/rest/api/tcmb/")
public class RestCurrencyRateControllerImpl extends RestBaseController implements IRestCurrencyRateController {

    @Autowired
    private ICurrencyRateService currencyRateService;

    @Override
    @GetMapping("currency-rates")
    public RootEntity<CurrencyRatesResponse> getCurrencyRates(@RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        return ok(currencyRateService.getCurrencyRates(startDate, endDate));
    }

}
