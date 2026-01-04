package com.ismaildurcan.controller;

import com.ismaildurcan.dto.DtoCar;
import com.ismaildurcan.dto.DtoCarIU;

public interface IRestCarController {

    public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);

    public RootEntity<DtoCar> updateCar(Long id, DtoCarIU dtoCarIU);

}
