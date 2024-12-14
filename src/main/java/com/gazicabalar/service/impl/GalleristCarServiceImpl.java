package com.gazicabalar.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gazicabalar.dto.DtoAddress;
import com.gazicabalar.dto.DtoCar;
import com.gazicabalar.dto.DtoGallerist;
import com.gazicabalar.dto.DtoGalleristCar;
import com.gazicabalar.dto.DtoGalleristCarIU;
import com.gazicabalar.exception.BaseException;
import com.gazicabalar.exception.ErrorMessage;
import com.gazicabalar.exception.MessageType;
import com.gazicabalar.model.Car;
import com.gazicabalar.model.Gallerist;
import com.gazicabalar.model.GalleristCar;
import com.gazicabalar.repository.CarRepository;
import com.gazicabalar.repository.GalleristCarRepository;
import com.gazicabalar.repository.GalleristRepository;
import com.gazicabalar.service.IGalleristCarService;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService {

	@Autowired
	private GalleristCarRepository galleristCarRepository;
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		GalleristCar galleristCar = new GalleristCar();
		galleristCar.setCreatTime(new Date());
		
		Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
		
		if(optGallerist.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, dtoGalleristCarIU.getGalleristId().toString()));
		
		Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarId());
		
		if(optCar.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, dtoGalleristCarIU.getCarId().toString()));
		
		BeanUtils.copyProperties(dtoGalleristCarIU, galleristCar);
		galleristCar.setGallerist(optGallerist.get());
		galleristCar.setCar(optCar.get());
		
		return galleristCar;
	}
	
	@Override
	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoCar dtoCar = new DtoCar();
		DtoAddress dtoAddress = new DtoAddress();
		
		GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));
		
		BeanUtils.copyProperties(savedGalleristCar, dtoGalleristCar);
		BeanUtils.copyProperties(savedGalleristCar.getGallerist(), dtoGallerist);
		BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), dtoAddress);
		BeanUtils.copyProperties(savedGalleristCar.getCar(), dtoCar);
		
		dtoGallerist.setDtoAddress(dtoAddress);
		dtoGalleristCar.setDtoGallerist(dtoGallerist);
		dtoGalleristCar.setDtoCar(dtoCar);
		
		return dtoGalleristCar;
	}

}
