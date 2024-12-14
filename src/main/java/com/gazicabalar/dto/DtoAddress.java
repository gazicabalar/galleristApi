package com.gazicabalar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAddress extends DtoBase{
	
	private String city;
	
	private String district;
		
	private String neighboorhood;
		
	private String street;
	
}
