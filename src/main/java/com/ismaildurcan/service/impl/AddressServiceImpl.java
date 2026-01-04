package com.ismaildurcan.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismaildurcan.dto.DtoAddress;
import com.ismaildurcan.dto.DtoAddressIU;
import com.ismaildurcan.exception.BaseException;
import com.ismaildurcan.exception.ErrorMessage;
import com.ismaildurcan.exception.MessageType;
import com.ismaildurcan.model.Address;
import com.ismaildurcan.repository.AddressRepository;
import com.ismaildurcan.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    private Address createAddressFromDto(DtoAddressIU dtoAddressIU) {
        Address address = new Address();
        address.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoAddressIU, address);
        return address;
    }

    @Override
    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
        DtoAddress dtoAddress = new DtoAddress();
        Address savedAddress = addressRepository.save(createAddressFromDto(dtoAddressIU));

        BeanUtils.copyProperties(savedAddress, dtoAddress);
        return dtoAddress;
    }

    @Override
    public DtoAddress updateAddress(Long id, DtoAddressIU dtoAddressIU) {
        DtoAddress dtoAddress = new DtoAddress();
        Address existingAddress = addressRepository.findById(id).orElseThrow(
                () -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND, "Address Id:" + id)));

        BeanUtils.copyProperties(dtoAddressIU, existingAddress);
        Address updatedAddress = addressRepository.save(existingAddress);

        BeanUtils.copyProperties(updatedAddress, dtoAddress);
        return dtoAddress;
    }

}
