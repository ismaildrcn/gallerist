package com.ismaildurcan.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DtoCustomerIU {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String tckn;

    @NotNull
    private String birthOfDate;

    @NotNull
    private Long addressId;

    @NotNull
    private Long accountId;

}
