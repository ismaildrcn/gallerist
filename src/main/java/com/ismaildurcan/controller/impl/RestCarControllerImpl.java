package com.ismaildurcan.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ismaildurcan.controller.IRestCarController;
import com.ismaildurcan.controller.RestBaseController;
import com.ismaildurcan.controller.RootEntity;
import com.ismaildurcan.dto.DtoCar;
import com.ismaildurcan.dto.DtoCarIU;
import com.ismaildurcan.service.ICarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarControllerImpl extends RestBaseController implements IRestCarController {

    @Autowired
    private ICarService carService;

    @Override
    @PostMapping("/save")
    public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {
        return ok(carService.saveCar(dtoCarIU));
    }

    @Override
    @PutMapping("/update/{id}")
    public RootEntity<DtoCar> updateCar(@PathVariable Long id, @Valid @RequestBody DtoCarIU dtoCarIU) {
        return ok(carService.updateCar(id, dtoCarIU));
    }

}
