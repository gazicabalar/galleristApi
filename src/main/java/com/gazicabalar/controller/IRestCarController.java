package com.gazicabalar.controller;

import com.gazicabalar.dto.DtoCar;
import com.gazicabalar.dto.DtoCarIU;

import java.util.List;

public interface IRestCarController {

	public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);

	public RootEntity<DtoCar> getCar(Long id);

	public RootEntity<List<DtoCar>> getAllCars();

	public RootEntity<DtoCar> updateCar(Long id, DtoCarIU dtoCarIU);

	public RootEntity<String> deleteCar(Long id);
	
}
