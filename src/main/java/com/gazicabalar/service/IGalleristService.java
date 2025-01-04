package com.gazicabalar.service;

import com.gazicabalar.dto.DtoGallerist;
import com.gazicabalar.dto.DtoGalleristIU;

import java.util.List;

public interface IGalleristService {

	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);

	public DtoGallerist getGallerist(Long id);

	public List<DtoGallerist> getAllGallerist();

	public DtoGallerist updateGallerist(Long id,DtoGalleristIU dtoGalleristIU);

	public String deleteGallerist(Long id);
}
