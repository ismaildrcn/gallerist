package com.ismaildurcan.service;

import com.ismaildurcan.dto.DtoAccount;
import com.ismaildurcan.dto.DtoAccountIU;

public interface IAccountService {

    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);

}
