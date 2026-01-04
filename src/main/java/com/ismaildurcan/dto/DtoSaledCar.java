package com.ismaildurcan.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DtoSaledCar extends DtoBase {

    private DtoCustomer customer;

    private DtoGallerist gallerist;

    private DtoCar car;

}
