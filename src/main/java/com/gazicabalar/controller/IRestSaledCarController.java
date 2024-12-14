package com.gazicabalar.controller;

import com.gazicabalar.dto.DtoSaledCar;
import com.gazicabalar.dto.DtoSaledCarIU;

public interface IRestSaledCarController {

	public RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU dtoSaledCarIU);
	
}
