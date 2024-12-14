package com.gazicabalar.service;

import com.gazicabalar.dto.DtoGalleristCar;
import com.gazicabalar.dto.DtoGalleristCarIU;

public interface IGalleristCarService {

	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
	
}
