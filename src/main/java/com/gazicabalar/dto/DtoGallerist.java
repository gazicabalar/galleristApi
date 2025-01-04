package com.gazicabalar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoGallerist extends DtoBase{

	private String firstName;
	
	private String lastName;
	
	private DtoAddress dtoAddress;
	
}
