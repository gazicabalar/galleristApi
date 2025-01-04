package com.gazicabalar.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.gazicabalar.dto.DtoAccount;
import com.gazicabalar.exception.BaseException;
import com.gazicabalar.exception.ErrorMessage;
import com.gazicabalar.exception.MessageType;
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

	@Override
	public DtoAddress getAddress(Long id) {
		Optional<Address> getAddress = addressRepository.findById(id);

		if(getAddress.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, id.toString()));

		System.out.println(getAddress.get().getCreatTime());

		DtoAddress dtoAddress = new DtoAddress(
				getAddress.get().getCity(),
				getAddress.get().getDistrict(),
				getAddress.get().getNeighboorhood(),
				getAddress.get().getStreet()
		);

		dtoAddress.setId(id);
		dtoAddress.setCreateTime(getAddress.get().getCreatTime());

		return dtoAddress;
	}

	@Override
	public List<DtoAddress> getAllAddress() {
		List<DtoAddress> dtoAddressList = new ArrayList<DtoAddress>();

		List<Address> addresses = addressRepository.findAll();

		if(addresses.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, addresses.toString()));

		for(Address address : addresses){
			DtoAddress dtoAddress = new DtoAddress(
					address.getCity(),
					address.getDistrict(),
					address.getNeighboorhood(),
					address.getStreet()
			);
			dtoAddressList.add(dtoAddress);
		}

		return dtoAddressList;
	}

	@Override
	public DtoAddress updateAddress(Long id, DtoAddressIU dtoAddressIU) {
		DtoAddress dtoAddress = new DtoAddress();

		Optional<Address> address = addressRepository.findById(id);

		if (address.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, id.toString()));

		address.get().setCity(dtoAddressIU.getCity());
		address.get().setDistrict(dtoAddressIU.getDistrict());
		address.get().setNeighboorhood(dtoAddressIU.getNeighboorhood());
		address.get().setStreet(dtoAddressIU.getStreet());

		Address savedAddress = addressRepository.save(address.get());

		BeanUtils.copyProperties(savedAddress,dtoAddress);

		return  dtoAddress;
	}

	@Override
	public String deleteAddress(Long id) {

		Optional<Address> address = addressRepository.findById(id);

		if (address.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, id.toString()));

		Long address_id = address.get().getId();

		addressRepository.delete(address.get());

		return "Address is deleted : " + address_id.toString();
	}


}
