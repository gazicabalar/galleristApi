package com.gazicabalar.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gazicabalar.dto.DtoAddress;
import com.gazicabalar.dto.DtoGallerist;
import com.gazicabalar.dto.DtoGalleristIU;
import com.gazicabalar.exception.BaseException;
import com.gazicabalar.exception.ErrorMessage;
import com.gazicabalar.exception.MessageType;
import com.gazicabalar.model.Address;
import com.gazicabalar.model.Gallerist;
import com.gazicabalar.repository.AddressRepository;
import com.gazicabalar.repository.GalleristRepository;
import com.gazicabalar.service.IGalleristService;

@Service
public class GalleristServiceImpl implements IGalleristService{

	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU) {
		Gallerist gallerist = new Gallerist();
		gallerist.setCreatTime(new Date());
		
		Optional<Address> optAddress = addressRepository.findById(dtoGalleristIU.getAddressId());
		
		if(optAddress.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, dtoGalleristIU.getAddressId().toString()));
		
		BeanUtils.copyProperties(dtoGalleristIU, gallerist);
		
		gallerist.setAddress(optAddress.get());
		
		return gallerist;
	}
	
	@Override
	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoAddress dtoAddress = new DtoAddress();
		Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristIU));
		
		BeanUtils.copyProperties(savedGallerist, dtoGallerist);
		BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress);
		
		dtoGallerist.setDtoAddress(dtoAddress);
		
		return dtoGallerist;
	}

	
	
}
