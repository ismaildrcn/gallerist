package com.ismaildurcan.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismaildurcan.dto.DtoAddress;
import com.ismaildurcan.dto.DtoGallerist;
import com.ismaildurcan.dto.DtoGalleristIU;
import com.ismaildurcan.exception.BaseException;
import com.ismaildurcan.exception.ErrorMessage;
import com.ismaildurcan.exception.MessageType;
import com.ismaildurcan.model.Address;
import com.ismaildurcan.model.Gallerist;
import com.ismaildurcan.repository.AddressRepository;
import com.ismaildurcan.repository.GalleristRepository;
import com.ismaildurcan.service.IGalleristService;

@Service
public class GalleristServiceImpl implements IGalleristService {

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private AddressRepository addressRepository;

    private Gallerist createGalleristFromDto(DtoGalleristIU dtoGalleristIU) {
        Address dbAddress = addressRepository.findById(dtoGalleristIU.getAddressId())
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_FOUND, "Address ID: " + dtoGalleristIU.getAddressId())));

        Gallerist gallerist = new Gallerist();
        gallerist.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoGalleristIU, gallerist);
        gallerist.setAddress(dbAddress);

        return gallerist;
    }

    @Override
    public DtoGallerist saveGallerist(DtoGalleristIU DtoGalleristIU) {
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();

        Gallerist savedGallerist = galleristRepository.save(createGalleristFromDto(DtoGalleristIU));

        BeanUtils.copyProperties(savedGallerist, dtoGallerist);
        BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress);

        dtoGallerist.setAddress(dtoAddress);
        return dtoGallerist;
    }

}
