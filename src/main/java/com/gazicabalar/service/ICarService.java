package com.gazicabalar.service;

import com.gazicabalar.dto.DtoCar;
import com.gazicabalar.dto.DtoCarIU;

public interface ICarService {

	public DtoCar saveCar(DtoCarIU dtoCarIU);
	
}
