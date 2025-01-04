package com.gazicabalar.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

	@Override
	public DtoGallerist getGallerist(Long id) {
		Optional<Gallerist> gallerist = galleristRepository.findById(id);

		if (gallerist.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, id.toString()));

		DtoAddress dtoAddress = new DtoAddress(
				gallerist.get().getAddress().getCity(),
				gallerist.get().getAddress().getDistrict(),
				gallerist.get().getAddress().getNeighboorhood(),
				gallerist.get().getAddress().getStreet()
		);

		DtoGallerist dtoGallerist = new DtoGallerist(
				gallerist.get().getFirstName(),
				gallerist.get().getLastName(),
				dtoAddress
		);

		dtoGallerist.setId(id);
		dtoGallerist.setCreateTime(gallerist.get().getCreatTime());

		return dtoGallerist;
	}

	@Override
	public List<DtoGallerist> getAllGallerist() {
		List<DtoGallerist> dtoGallerists = new ArrayList<>();

		List<Gallerist> gallerists = galleristRepository.findAll();

		if(gallerists.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, gallerists.toString()));

		for(Gallerist gallerist : gallerists){
			DtoAddress dtoAddress = new DtoAddress(
					gallerist.getAddress().getCity(),
					gallerist.getAddress().getDistrict(),
					gallerist.getAddress().getNeighboorhood(),
					gallerist.getAddress().getStreet()
			);
			DtoGallerist dtoGallerist = new DtoGallerist(
					gallerist.getFirstName(),
					gallerist.getLastName(),
					dtoAddress
			);
			dtoGallerist.setId(gallerist.getId());
			dtoGallerist.setCreateTime(gallerist.getCreatTime());
			dtoGallerists.add(dtoGallerist);
		}

		return dtoGallerists;
	}

	@Override
	public DtoGallerist updateGallerist(Long id, DtoGalleristIU dtoGalleristIU) {
		Optional<Gallerist> gallerist = galleristRepository.findById(id);
		Optional<Address> galleristAddressId = addressRepository.findById(dtoGalleristIU.getAddressId());

		if (gallerist.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, id.toString()));

		if (galleristAddressId.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, id.toString()));

		gallerist.get().setFirstName(dtoGalleristIU.getFirstName());
		gallerist.get().setLastName(dtoGalleristIU.getLastName());
		gallerist.get().setAddress(galleristAddressId.get());

		Gallerist savedGallerist = galleristRepository.save(gallerist.get());

		DtoAddress dtoAddress = new DtoAddress(
				savedGallerist.getAddress().getCity(),
				savedGallerist.getAddress().getDistrict(),
				savedGallerist.getAddress().getNeighboorhood(),
				savedGallerist.getAddress().getStreet()
		);


		DtoGallerist dtoGallerist = new DtoGallerist(
				savedGallerist.getFirstName(),
				savedGallerist.getLastName(),
				dtoAddress
		);

		dtoGallerist.setId(id);
		dtoGallerist.setCreateTime(gallerist.get().getCreatTime());

		return dtoGallerist;
	}

	@Override
	public String deleteGallerist(Long id) {
		Optional<Gallerist> gallerist = galleristRepository.findById(id);

		if (gallerist.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, id.toString()));

		Long galleristId = gallerist.get().getId();

		galleristRepository.deleteById(id);

		return "Gallerist is deleted : " + galleristId;
	}


}
