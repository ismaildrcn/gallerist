package com.ismaildurcan.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ismaildurcan.controller.IRestGalleristController;
import com.ismaildurcan.controller.RestBaseController;
import com.ismaildurcan.controller.RootEntity;
import com.ismaildurcan.dto.DtoGallerist;
import com.ismaildurcan.dto.DtoGalleristIU;
import com.ismaildurcan.service.IGalleristService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist")
public class RestGalleristControllerImpl extends RestBaseController implements IRestGalleristController {

    @Autowired
    private IGalleristService galleristService;

    @Override
    @PostMapping("/save")
    public RootEntity<DtoGallerist> saveGallerist(@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {
        return ok(galleristService.saveGallerist(dtoGalleristIU));
    }

}
