package com.ismaildurcan.controller;

import com.ismaildurcan.dto.DtoCustomer;
import com.ismaildurcan.dto.DtoCustomerIU;

public interface IRestCustomerController {

    public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);

}
