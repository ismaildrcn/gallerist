package com.ismaildurcan.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ismaildurcan.dto.DtoAccount;
import com.ismaildurcan.dto.DtoAddress;
import com.ismaildurcan.dto.DtoCustomer;
import com.ismaildurcan.dto.DtoCustomerIU;
import com.ismaildurcan.exception.BaseException;
import com.ismaildurcan.exception.ErrorMessage;
import com.ismaildurcan.exception.MessageType;
import com.ismaildurcan.model.Account;
import com.ismaildurcan.model.Address;
import com.ismaildurcan.model.Customer;
import com.ismaildurcan.repository.AccountRepository;
import com.ismaildurcan.repository.AddressRepository;
import com.ismaildurcan.repository.CustomerRepository;
import com.ismaildurcan.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Customer createCustomerFromDto(DtoCustomerIU dtoCustomerIU) {
        Customer customer = new Customer();

        Address dbAddress = addressRepository.findById(dtoCustomerIU.getAddressId())
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_FOUND,
                                "Address ID: " + dtoCustomerIU.getAddressId())));

        Account dbAccount = accountRepository.findById(dtoCustomerIU.getAccountId())
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_FOUND,
                                "Account ID: " + dtoCustomerIU.getAccountId())));

        customer.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoCustomerIU, customer);
        customer.setAddress(dbAddress);
        customer.setAccount(dbAccount);

        return customer;
    }

    @Override
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAddress dtoAddress = new DtoAddress();
        DtoAccount dtoAccount = new DtoAccount();

        Customer savedCustomer = customerRepository.save(createCustomerFromDto(dtoCustomerIU));

        BeanUtils.copyProperties(savedCustomer, dtoCustomer);
        BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);
        BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);

        dtoCustomer.setAddress(dtoAddress);
        dtoCustomer.setAccount(dtoAccount);

        return dtoCustomer;
    }

}
