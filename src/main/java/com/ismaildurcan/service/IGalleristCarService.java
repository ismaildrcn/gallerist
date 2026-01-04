package com.ismaildurcan.service;

import com.ismaildurcan.dto.DtoGalleristCar;
import com.ismaildurcan.dto.DtoGalleristCarIU;

public interface IGalleristCarService {

    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);

}
