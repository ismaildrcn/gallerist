package com.ismaildurcan.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismaildurcan.dto.DtoCar;
import com.ismaildurcan.dto.DtoCarIU;
import com.ismaildurcan.exception.BaseException;
import com.ismaildurcan.exception.ErrorMessage;
import com.ismaildurcan.exception.MessageType;
import com.ismaildurcan.model.Car;
import com.ismaildurcan.repository.CarRepository;
import com.ismaildurcan.service.ICarService;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarRepository carRepository;

    private Car createCarFromDto(DtoCarIU dtoCarIU) {
        Car car = new Car();
        car.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoCarIU, car);
        return car;
    }

    @Override
    public DtoCar saveCar(DtoCarIU dtoCarIU) {
        DtoCar dtoCar = new DtoCar();
        Car savedCar = carRepository.save(createCarFromDto(dtoCarIU));

        BeanUtils.copyProperties(savedCar, dtoCar);
        return dtoCar;
    }

    @Override
    public DtoCar updateCar(Long id, DtoCarIU dtoCarIU) {
        DtoCar dtoCar = new DtoCar();
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND, "Card Id: " + id)));

        BeanUtils.copyProperties(dtoCarIU, existingCar);
        Car savedCar = carRepository.save(existingCar);

        BeanUtils.copyProperties(savedCar, dtoCar);
        return dtoCar;
    }

}
