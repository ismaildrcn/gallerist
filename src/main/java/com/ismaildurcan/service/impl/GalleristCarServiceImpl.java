package com.ismaildurcan.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismaildurcan.dto.DtoAddress;
import com.ismaildurcan.dto.DtoCar;
import com.ismaildurcan.dto.DtoGallerist;
import com.ismaildurcan.dto.DtoGalleristCar;
import com.ismaildurcan.dto.DtoGalleristCarIU;
import com.ismaildurcan.exception.BaseException;
import com.ismaildurcan.exception.ErrorMessage;
import com.ismaildurcan.exception.MessageType;
import com.ismaildurcan.model.Car;
import com.ismaildurcan.model.Gallerist;
import com.ismaildurcan.model.GalleristCar;
import com.ismaildurcan.repository.CarRepository;
import com.ismaildurcan.repository.GalleristCarRepository;
import com.ismaildurcan.repository.GalleristRepository;
import com.ismaildurcan.service.IGalleristCarService;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService {

    @Autowired
    private GalleristCarRepository galleristCarRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    private GalleristCar createGalleristCarFromDto(DtoGalleristCarIU dtoGalleristCarIU) {
        GalleristCar galleristCar = new GalleristCar();
        galleristCar.setCreateTime(new Date());

        Gallerist dbGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId())
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_FOUND,
                                "Gallerist Id: " + dtoGalleristCarIU.getGalleristId())));

        Car dbCar = carRepository.findById(dtoGalleristCarIU.getCarId())
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_FOUND,
                                "Car Id: " + dtoGalleristCarIU.getCarId())));

        galleristCar.setGallerist(dbGallerist);
        galleristCar.setCar(dbCar);
        return galleristCar;
    }

    @Override
    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();

        DtoAddress dtoAddress = new DtoAddress();

        GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCarFromDto(dtoGalleristCarIU));

        BeanUtils.copyProperties(savedGalleristCar, dtoGalleristCar);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist(), dtoGallerist);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), dtoAddress);
        BeanUtils.copyProperties(savedGalleristCar.getCar(), dtoCar);

        dtoGallerist.setAddress(dtoAddress);
        dtoGalleristCar.setGallerist(dtoGallerist);
        dtoGalleristCar.setCar(dtoCar);

        return dtoGalleristCar;
    }

}
