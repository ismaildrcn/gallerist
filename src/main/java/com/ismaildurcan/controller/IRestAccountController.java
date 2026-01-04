package com.ismaildurcan.controller;

import com.ismaildurcan.dto.DtoAccount;
import com.ismaildurcan.dto.DtoAccountIU;

public interface IRestAccountController {

    public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);

}
