package com.gazicabalar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoGalleristCar extends DtoBase{

	private DtoGallerist dtoGallerist;
	
	private DtoCar dtoCar;
	
}
