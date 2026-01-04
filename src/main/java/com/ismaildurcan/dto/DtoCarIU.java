package com.ismaildurcan.dto;

import java.math.BigDecimal;

import com.ismaildurcan.enums.CarStatusType;
import com.ismaildurcan.enums.CurrencyType;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DtoCarIU {

    @NotNull
    private String plate;

    @NotNull
    private String brand;

    @NotNull
    private String model;

    @NotNull
    private Integer productionYear;

    @NotNull
    private BigDecimal price;

    @NotNull
    private CurrencyType currencyType;

    @NotNull
    private BigDecimal damagePrice;

    @NotNull
    private CarStatusType carStatusType;

}
