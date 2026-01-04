package com.ismaildurcan.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ismaildurcan.controller.IRestSaledCarController;
import com.ismaildurcan.controller.RestBaseController;
import com.ismaildurcan.controller.RootEntity;
import com.ismaildurcan.dto.DtoSaledCar;
import com.ismaildurcan.dto.DtoSaledCarIU;
import com.ismaildurcan.service.ISaledCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/saled-car")
public class RestSaledCarController extends RestBaseController implements IRestSaledCarController {

    @Autowired
    private ISaledCarService saledCarService;

    @Override
    @PostMapping("/buy")
    public RootEntity<DtoSaledCar> buyCar(@Valid @RequestBody DtoSaledCarIU dtoSaledCarIU) {
        return ok(saledCarService.buyCar(dtoSaledCarIU));
    }

}
