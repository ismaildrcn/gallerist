package com.ismaildurcan.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DtoCustomer extends DtoBase {

    private String firstName;

    private String lastName;

    private String tckn;

    private String birthOfDate;

    private DtoAddress address;

    private DtoAccount account;

}
