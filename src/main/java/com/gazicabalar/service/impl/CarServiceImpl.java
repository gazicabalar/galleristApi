package com.gazicabalar.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.gazicabalar.exception.BaseException;
import com.gazicabalar.exception.ErrorMessage;
import com.gazicabalar.exception.MessageType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gazicabalar.dto.DtoCar;
import com.gazicabalar.dto.DtoCarIU;
import com.gazicabalar.model.Car;
import com.gazicabalar.repository.CarRepository;
import com.gazicabalar.service.ICarService;

@Service
public class CarServiceImpl implements ICarService{

	@Autowired
	private CarRepository carRepository;
	
	private Car createCar(DtoCarIU dtoCarIU) {
		Car car = new Car();
		car.setCreatTime(new Date());
		
		BeanUtils.copyProperties(dtoCarIU, car);
		
		return car;
	}
	
	@Override
	public DtoCar saveCar(DtoCarIU dtoCarIU) {
		DtoCar dtoCar = new DtoCar(); 
		Car savedCar = carRepository.save(createCar(dtoCarIU));
		 
		BeanUtils.copyProperties(savedCar, dtoCar);
		
		 return dtoCar;
	}

	@Override
	public DtoCar getCar(Long id) {
		Optional<Car> car = carRepository.findById(id);

		if (car.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, id.toString()));

		System.out.println(car.get().getCreatTime());

		DtoCar dtoCar = new DtoCar(
				car.get().getPlaka(),
				car.get().getBrand(),
				car.get().getModel(),
				car.get().getProductionYear(),
				car.get().getPrice(),
				car.get().getCurrencyType(),
				car.get().getDamagePrice(),
				car.get().getCarStatusType()
		);

		dtoCar.setId(id);
		dtoCar.setCreateTime(car.get().getCreatTime());

		return dtoCar;
	}

	@Override
	public List<DtoCar> getAllCars() {
		List<DtoCar> dtoCars = new ArrayList<>();

		List<Car> cars = carRepository.findAll();

		if(cars.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, ""));

		for (Car car : cars){
			DtoCar dtoCar = new DtoCar(
					car.getPlaka(),
					car.getBrand(),
					car.getModel(),
					car.getProductionYear(),
					car.getPrice(),
					car.getCurrencyType(),
					car.getDamagePrice(),
					car.getCarStatusType()
			);
			dtoCars.add(dtoCar);
		}

		return dtoCars;
	}

	@Override
	public DtoCar updateCar(Long id, DtoCarIU dtoCarIU) {
		Optional<Car> car = carRepository.findById(id);

		if (car.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, id.toString()));

		car.get().setPlaka(dtoCarIU.getPlaka());
		car.get().setBrand(dtoCarIU.getBrand());
		car.get().setModel(dtoCarIU.getModel());
		car.get().setProductionYear(dtoCarIU.getProductionYear());
		car.get().setPrice(dtoCarIU.getPrice());
		car.get().setCurrencyType(dtoCarIU.getCurrencyType());
		car.get().setDamagePrice(dtoCarIU.getDamagePrice());
		car.get().setCarStatusType(dtoCarIU.getCarStatusType());

		Car savedCar = carRepository.save(car.get());

		DtoCar dtoCar = new DtoCar(
				savedCar.getPlaka(),
				savedCar.getBrand(),
				savedCar.getModel(),
				savedCar.getProductionYear(),
				savedCar.getPrice(),
				savedCar.getCurrencyType(),
				savedCar.getDamagePrice(),
				savedCar.getCarStatusType()
		);

		return dtoCar;
	}

	@Override
	public String deleteCar(Long id) {
		Optional<Car> car = carRepository.findById(id);

		if (car.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, id.toString()));

		Long car_id = car.get().getId();

		carRepository.deleteById(id);

		return "Car is deleted : " + car_id;
	}

}
