package com.ismaildurcan.controller;

import com.ismaildurcan.dto.DtoGallerist;
import com.ismaildurcan.dto.DtoGalleristIU;

public interface IRestGalleristController {

    public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);

}
