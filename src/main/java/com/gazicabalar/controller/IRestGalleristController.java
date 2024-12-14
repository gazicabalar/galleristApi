package com.gazicabalar.controller;

import com.gazicabalar.dto.DtoGallerist;
import com.gazicabalar.dto.DtoGalleristIU;

public interface IRestGalleristController {

	public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);
	
}
