package com.gazicabalar.controller;

import com.gazicabalar.dto.DtoGalleristCar;
import com.gazicabalar.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {

	public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
	
}
