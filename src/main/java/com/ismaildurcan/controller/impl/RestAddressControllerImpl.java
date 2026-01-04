package com.ismaildurcan.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ismaildurcan.controller.IRestAddressController;
import com.ismaildurcan.controller.RestBaseController;
import com.ismaildurcan.controller.RootEntity;
import com.ismaildurcan.dto.DtoAddress;
import com.ismaildurcan.dto.DtoAddressIU;
import com.ismaildurcan.service.IAddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController {

    @Autowired
    private IAddressService addressService;

    @Override
    @PostMapping("/save")
    public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
        return ok(addressService.saveAddress(dtoAddressIU));
    }

    @Override
    @PutMapping("/update/{id}")
    public RootEntity<DtoAddress> updateAddress(@PathVariable Long id, @Valid @RequestBody DtoAddressIU dtoAddressIU) {
        return ok(addressService.updateAddress(id, dtoAddressIU));
    }

}
