package com.ismaildurcan.controller;

import com.ismaildurcan.dto.DtoAddress;
import com.ismaildurcan.dto.DtoAddressIU;

public interface IRestAddressController {

    public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);

    public RootEntity<DtoAddress> updateAddress(Long id, DtoAddressIU dtoAddressIU);

}
