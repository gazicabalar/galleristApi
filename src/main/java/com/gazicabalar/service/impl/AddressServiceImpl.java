package com.gazicabalar.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gazicabalar.dto.DtoAddress;
import com.gazicabalar.dto.DtoAddressIU;
import com.gazicabalar.model.Address;
import com.gazicabalar.repository.AddressRepository;
import com.gazicabalar.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService{

	@Autowired
	private AddressRepository addressRepository;
	
	private Address createAddress(DtoAddressIU dtoAddressIU) {
		Address address = new Address();
		address.setCreatTime(new Date());
		
		BeanUtils.copyProperties(dtoAddressIU, address);
		return address;
	}
	
	@Override
	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
		DtoAddress dtoAddress = new DtoAddress();
		Address savedAddress = addressRepository.save(createAddress(dtoAddressIU));
		
		BeanUtils.copyProperties(savedAddress, dtoAddress);
		
		return dtoAddress;
	}

	
	
}
