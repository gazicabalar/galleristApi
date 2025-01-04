package com.gazicabalar.controller;

import com.gazicabalar.dto.DtoGallerist;
import com.gazicabalar.dto.DtoGalleristIU;

import java.util.List;

public interface IRestGalleristController {

	public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);
	public RootEntity<DtoGallerist> getGallerist(Long id);
	public RootEntity<List<DtoGallerist>> getAllGallerist();
	public RootEntity<DtoGallerist> updateGallerist(Long id, DtoGalleristIU dtoGalleristIU);
	public RootEntity<String> deleteGallerist(Long id);
}
