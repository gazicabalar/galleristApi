package com.gazicabalar.service.impl;

import java.util.Date;

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

}
