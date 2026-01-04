package com.ismaildurcan.controller;

import com.ismaildurcan.dto.DtoGalleristCar;
import com.ismaildurcan.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {

    public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);

}
