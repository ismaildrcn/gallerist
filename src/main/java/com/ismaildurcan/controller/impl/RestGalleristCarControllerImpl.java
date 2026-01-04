package com.ismaildurcan.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ismaildurcan.controller.IRestGalleristCarController;
import com.ismaildurcan.controller.RestBaseController;
import com.ismaildurcan.controller.RootEntity;
import com.ismaildurcan.dto.DtoGalleristCar;
import com.ismaildurcan.dto.DtoGalleristCarIU;
import com.ismaildurcan.service.IGalleristCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist-car")
public class RestGalleristCarControllerImpl extends RestBaseController implements IRestGalleristCarController {

    @Autowired
    private IGalleristCarService galleristCarService;

    @Override
    @PostMapping("/save")
    public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
        return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
    }

}
