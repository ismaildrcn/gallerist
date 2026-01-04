package com.ismaildurcan.dto;

import java.math.BigDecimal;

import com.ismaildurcan.enums.CarStatusType;
import com.ismaildurcan.enums.CurrencyType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DtoCar extends DtoBase {

    private String plate;

    private String brand;

    private String model;

    private Integer productionYear;

    private BigDecimal price;

    private CurrencyType currencyType;

    private BigDecimal damagePrice;

    private CarStatusType carStatusType;

}
