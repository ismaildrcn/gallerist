package com.ismaildurcan.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DtoGallerist extends DtoBase {

    private String firstName;

    private String lastName;

    private DtoAddress address;

}
