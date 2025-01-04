package com.gazicabalar.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

	@Override
	public DtoGalleristCar getGalleristCarById(Long id) {
		DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
		Optional<GalleristCar> galleristCar = galleristCarRepository.findById(id);

		if(galleristCar.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, id.toString()));

		DtoAddress dtoAddress = new DtoAddress(
				galleristCar.get().getGallerist().getAddress().getCity(),
				galleristCar.get().getGallerist().getAddress().getDistrict(),
				galleristCar.get().getGallerist().getAddress().getNeighboorhood(),
				galleristCar.get().getGallerist().getAddress().getStreet()
		);

		dtoAddress.setId(galleristCar.get().getGallerist().getAddress().getId());
		dtoAddress.setCreateTime(galleristCar.get().getCreatTime());

		DtoGallerist dtoGallerist = new DtoGallerist(
				galleristCar.get().getGallerist().getFirstName(),
				galleristCar.get().getGallerist().getLastName(),
				dtoAddress
		);

		dtoGallerist.setId(galleristCar.get().getGallerist().getId());
		dtoGallerist.setCreateTime(galleristCar.get().getCreatTime());

		DtoCar dtoCar = new DtoCar(
				galleristCar.get().getCar().getPlaka(),
				galleristCar.get().getCar().getBrand(),
				galleristCar.get().getCar().getModel(),
				galleristCar.get().getCar().getProductionYear(),
				galleristCar.get().getCar().getPrice(),
				galleristCar.get().getCar().getCurrencyType(),
				galleristCar.get().getCar().getDamagePrice(),
				galleristCar.get().getCar().getCarStatusType()
		);

		dtoCar.setId(galleristCar.get().getCar().getId());
		dtoCar.setCreateTime(galleristCar.get().getCreatTime());

		dtoGalleristCar.setDtoGallerist(dtoGallerist);
		dtoGalleristCar.setDtoCar(dtoCar);

		dtoGalleristCar.setId(id);
		dtoGalleristCar.setCreateTime(galleristCar.get().getCreatTime());

		return dtoGalleristCar;
	}

	@Override
	public List<DtoGalleristCar> getAllGalleristCar() {
		List<DtoGalleristCar> dtoGalleristCars = new ArrayList<>();

		List<GalleristCar> galleristCarAll = galleristCarRepository.findAll();

		if(galleristCarAll.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, galleristCarAll.toString()));

		for (GalleristCar galleristCar : galleristCarAll) {
			DtoAddress dtoAddress = new DtoAddress(
					galleristCar.getGallerist().getAddress().getCity(),
					galleristCar.getGallerist().getAddress().getDistrict(),
					galleristCar.getGallerist().getAddress().getNeighboorhood(),
					galleristCar.getGallerist().getAddress().getStreet()
			);

			dtoAddress.setId(galleristCar.getGallerist().getAddress().getId());
			dtoAddress.setCreateTime(galleristCar.getCreatTime());

			DtoGallerist dtoGallerist = new DtoGallerist(
					galleristCar.getGallerist().getFirstName(),
					galleristCar.getGallerist().getLastName(),
					dtoAddress
			);

			dtoGallerist.setId(galleristCar.getGallerist().getId());
			dtoGallerist.setCreateTime(galleristCar.getCreatTime());

			DtoCar dtoCar = new DtoCar(
					galleristCar.getCar().getPlaka(),
					galleristCar.getCar().getBrand(),
					galleristCar.getCar().getModel(),
					galleristCar.getCar().getProductionYear(),
					galleristCar.getCar().getPrice(),
					galleristCar.getCar().getCurrencyType(),
					galleristCar.getCar().getDamagePrice(),
					galleristCar.getCar().getCarStatusType()
			);

			dtoCar.setId(galleristCar.getCar().getId());
			dtoCar.setCreateTime(galleristCar.getCreatTime());

			DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
			dtoGalleristCar.setDtoGallerist(dtoGallerist);
			dtoGalleristCar.setDtoCar(dtoCar);

			dtoGalleristCar.setId(galleristCar.getId());
			dtoGalleristCar.setCreateTime(galleristCar.getCreatTime());

			dtoGalleristCars.add(dtoGalleristCar);
		}

		return dtoGalleristCars;
	}

	@Override
	public DtoGalleristCar updateGalleristCar(Long id, DtoGalleristCarIU dtoGalleristCarIU) {
		DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();

		Optional<GalleristCar> galleristCar = galleristCarRepository.findById(id);
		Optional<Gallerist> gallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
		Optional<Car> car = carRepository.findById(dtoGalleristCarIU.getCarId());

		if(galleristCar.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, id.toString()));

		if (car.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, id.toString()));

		if (gallerist.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, id.toString()));

		galleristCar.get().setGallerist(gallerist.get());
		galleristCar.get().setCar(car.get());

		GalleristCar savedGalleristCar = galleristCarRepository.save(galleristCar.get());

		DtoAddress dtoAddress = new DtoAddress(
				savedGalleristCar.getGallerist().getAddress().getCity(),
				savedGalleristCar.getGallerist().getAddress().getDistrict(),
				savedGalleristCar.getGallerist().getAddress().getNeighboorhood(),
				savedGalleristCar.getGallerist().getAddress().getStreet()
		);

		dtoAddress.setId(savedGalleristCar.getGallerist().getAddress().getId());
		dtoAddress.setCreateTime(savedGalleristCar.getCreatTime());

		DtoGallerist dtoGallerist = new DtoGallerist(
				savedGalleristCar.getGallerist().getFirstName(),
				savedGalleristCar.getGallerist().getLastName(),
				dtoAddress
		);

		dtoGallerist.setId(savedGalleristCar.getGallerist().getId());
		dtoGallerist.setCreateTime(savedGalleristCar.getCreatTime());

		DtoCar dtoCar = new DtoCar(
				savedGalleristCar.getCar().getPlaka(),
				savedGalleristCar.getCar().getBrand(),
				savedGalleristCar.getCar().getModel(),
				savedGalleristCar.getCar().getProductionYear(),
				savedGalleristCar.getCar().getPrice(),
				savedGalleristCar.getCar().getCurrencyType(),
				savedGalleristCar.getCar().getDamagePrice(),
				savedGalleristCar.getCar().getCarStatusType()
		);

		dtoCar.setId(savedGalleristCar.getCar().getId());
		dtoCar.setCreateTime(savedGalleristCar.getCreatTime());

		dtoGalleristCar.setDtoGallerist(dtoGallerist);
		dtoGalleristCar.setDtoCar(dtoCar);

		dtoGalleristCar.setId(savedGalleristCar.getId());
		dtoGalleristCar.setCreateTime(savedGalleristCar.getCreatTime());

		return dtoGalleristCar;
	}

	@Override
	public String deleteGalleristCar(Long id) {
		Optional<GalleristCar> galleristCar = galleristCarRepository.findById(id);

		if(galleristCar.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, id.toString()));

		Long galleristId = galleristCar.get().getId();

		galleristCarRepository.deleteById(id);

		return "Gallerist-Car is deleted : " + galleristId;
	}

}
