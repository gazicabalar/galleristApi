package com.gazicabalar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSaledCar extends DtoBase {

	private DtoCustomer dtoCustomer;
	
	private DtoGallerist dtoGallerist;
	
	private DtoCar dtoCar;
	
}
