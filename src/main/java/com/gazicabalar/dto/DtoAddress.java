package com.gazicabalar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoAddress extends DtoBase{
	
	private String city;
	
	private String district;
		
	private String neighboorhood;
		
	private String street;
	
}
