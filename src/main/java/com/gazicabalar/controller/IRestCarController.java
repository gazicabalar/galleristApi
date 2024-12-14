package com.gazicabalar.controller;

import com.gazicabalar.dto.DtoCar;
import com.gazicabalar.dto.DtoCarIU;

public interface IRestCarController {

	public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
	
}
