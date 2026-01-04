package com.ismaildurcan.service;

import com.ismaildurcan.dto.DtoSaledCar;
import com.ismaildurcan.dto.DtoSaledCarIU;

public interface ISaledCarService {

    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);

}
