package com.gazicabalar.service;

import com.gazicabalar.dto.DtoCar;
import com.gazicabalar.dto.DtoCarIU;

import java.util.List;

public interface ICarService {

	public DtoCar saveCar(DtoCarIU dtoCarIU);

	public DtoCar getCar(Long id);

	public List<DtoCar> getAllCars();

	public DtoCar updateCar(Long id, DtoCarIU dtoCarIU);

	public String deleteCar(Long id);
	
}
