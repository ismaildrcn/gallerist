package com.ismaildurcan.service;

import com.ismaildurcan.dto.DtoAddress;
import com.ismaildurcan.dto.DtoAddressIU;

public interface IAddressService {

    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);

}
