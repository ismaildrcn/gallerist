package com.ismaildurcan.service;

import com.ismaildurcan.dto.DtoCar;
import com.ismaildurcan.dto.DtoCarIU;

public interface ICarService {

    public DtoCar saveCar(DtoCarIU dtoCarIU);

    public DtoCar updateCar(Long id, DtoCarIU dtoCarIU);

}
