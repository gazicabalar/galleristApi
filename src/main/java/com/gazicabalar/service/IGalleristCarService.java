package com.gazicabalar.service;

import com.gazicabalar.dto.DtoGalleristCar;
import com.gazicabalar.dto.DtoGalleristCarIU;

import java.util.List;

public interface IGalleristCarService {

	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);

	public DtoGalleristCar getGalleristCarById(Long id);

	public List<DtoGalleristCar> getAllGalleristCar();

	public DtoGalleristCar updateGalleristCar(Long id, DtoGalleristCarIU dtoGalleristCarIU);

	public String deleteGalleristCar(Long id);
}
