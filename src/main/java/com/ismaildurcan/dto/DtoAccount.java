package com.ismaildurcan.dto;

import java.math.BigDecimal;

import com.ismaildurcan.enums.CurrencyType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DtoAccount extends DtoBase {

    private String accountNo;

    private String iban;

    private BigDecimal amount;

    private CurrencyType currencyType;

}
