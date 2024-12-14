package com.gazicabalar.service;

import com.gazicabalar.dto.DtoSaledCar;
import com.gazicabalar.dto.DtoSaledCarIU;

public interface ISaledCarService {

	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);
	
}
