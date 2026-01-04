package com.ismaildurcan.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DtoAddress extends DtoBase {
    
    private String city;

    private String district;

    private String neighborhood;

    private String street;

}
