package com.ismaildurcan.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismaildurcan.dto.CurrencyRatesResponse;
import com.ismaildurcan.dto.DtoCar;
import com.ismaildurcan.dto.DtoCustomer;
import com.ismaildurcan.dto.DtoGallerist;
import com.ismaildurcan.dto.DtoSaledCar;
import com.ismaildurcan.dto.DtoSaledCarIU;
import com.ismaildurcan.enums.CarStatusType;
import com.ismaildurcan.exception.BaseException;
import com.ismaildurcan.exception.ErrorMessage;
import com.ismaildurcan.exception.MessageType;
import com.ismaildurcan.model.Car;
import com.ismaildurcan.model.Customer;
import com.ismaildurcan.model.SaledCar;
import com.ismaildurcan.repository.CarRepository;
import com.ismaildurcan.repository.CustomerRepository;
import com.ismaildurcan.repository.GalleristRepository;
import com.ismaildurcan.repository.SaledCarRepository;
import com.ismaildurcan.service.ICurrencyRateService;
import com.ismaildurcan.service.ISaledCarService;

@Service
public class SaledCarServiceImpl implements ISaledCarService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private ICurrencyRateService currencyRateService;

    @Autowired
    private SaledCarRepository saledCarRepository;

    private BigDecimal convertCustomerAmountToUSD(Customer customer) {
        CurrencyRatesResponse currencyRates = currencyRateService.getCurrencyRates("02-01-2026", "02-01-2026");

        BigDecimal usd = new BigDecimal(currencyRates.getItems().get(0).getUsd());

        BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);
        return customerUSDAmount;
    }

    private BigDecimal remaningCustomerAmountAfterBuy(Customer customer, Car car) {
        BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
        BigDecimal remainingUSDAmount = customerUSDAmount.subtract(car.getPrice());

        CurrencyRatesResponse currencyRates = currencyRateService.getCurrencyRates("02-01-2026", "02-01-2026");
        BigDecimal usd = new BigDecimal(currencyRates.getItems().get(0).getUsd());

        return remainingUSDAmount.multiply(usd);
    }

    private boolean checkCarStatus(Long carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new BaseException(
                new ErrorMessage(MessageType.NO_RECORD_FOUND, "Car Id: " + carId)));

        if (car.getCarStatusType() == CarStatusType.SALED) {
            return false;
        }
        return true;
    }

    public boolean checkAmount(DtoSaledCarIU dtoSaledCarIU) {

        Customer customer = customerRepository.findById(dtoSaledCarIU.getCustomerId())
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_FOUND,
                                "Customer Id: " + dtoSaledCarIU.getCustomerId())));

        Car car = carRepository.findById(dtoSaledCarIU.getCarId()).orElseThrow(() -> new BaseException(
                new ErrorMessage(MessageType.NO_RECORD_FOUND, "Car Id: " + dtoSaledCarIU.getCarId())));

        BigDecimal customerAmountToUSD = convertCustomerAmountToUSD(customer);

        if (customerAmountToUSD.compareTo(car.getPrice()) >= 0) {
            return true;
        }
        return false;
    }

    private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU) {
        SaledCar saledCar = new SaledCar();
        saledCar.setCreateTime(new Date());

        saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
        saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
        saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));

        return saledCar;
    }

    @Override
    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {
        if (!checkCarStatus(dtoSaledCarIU.getCarId())) {
            throw new BaseException(
                    new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SALED, null));
        }
        if (!checkAmount(dtoSaledCarIU)) {
            throw new BaseException(
                    new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, null));
        }

        SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));

        // Update Car Status
        Car car = savedSaledCar.getCar();
        car.setCarStatusType(CarStatusType.SALED);
        carRepository.save(car);

        // Update Customer Amount
        Customer customer = savedSaledCar.getCustomer();
        customer.getAccount().setAmount(remaningCustomerAmountAfterBuy(customer, car));
        customerRepository.save(customer);

        return convertToDto(savedSaledCar);
    }

    private DtoSaledCar convertToDto(SaledCar saledCar) {
        DtoCar dtoCar = new DtoCar();
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoGallerist dtoGallerist = new DtoGallerist();

        DtoSaledCar dtoSaledCar = new DtoSaledCar();
        dtoSaledCar.setCreateTime(new Date());

        BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
        BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);
        BeanUtils.copyProperties(saledCar.getCar(), dtoCar);

        BeanUtils.copyProperties(saledCar, dtoSaledCar);
        dtoSaledCar.setCustomer(dtoCustomer);
        dtoSaledCar.setGallerist(dtoGallerist);
        dtoSaledCar.setCar(dtoCar);

        return dtoSaledCar;
    }

}
