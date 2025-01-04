package com.gazicabalar.controller;

import com.gazicabalar.dto.DtoGalleristCar;
import com.gazicabalar.dto.DtoGalleristCarIU;

import java.util.List;

public interface IRestGalleristCarController {

	public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);

	public RootEntity<DtoGalleristCar> getGalleristCarById(Long id);

	public RootEntity<List<DtoGalleristCar>> getAllGalleristCar();

	public RootEntity<DtoGalleristCar> updateGalleristCar(Long id, DtoGalleristCarIU dtoGalleristCarIU);

	public RootEntity<String> deleteGalleristCar(Long id);
	
}
