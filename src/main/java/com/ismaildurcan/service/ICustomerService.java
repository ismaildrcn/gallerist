package com.ismaildurcan.service;

import com.ismaildurcan.dto.DtoCustomer;
import com.ismaildurcan.dto.DtoCustomerIU;

public interface ICustomerService {

    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);

}
