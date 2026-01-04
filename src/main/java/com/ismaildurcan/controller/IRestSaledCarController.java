package com.ismaildurcan.controller;

import com.ismaildurcan.dto.DtoSaledCar;
import com.ismaildurcan.dto.DtoSaledCarIU;

public interface IRestSaledCarController {

    public RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU dtoSaledCarIU);

}
