package com.gazicabalar.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAddressIU {

	@NotEmpty
	private String city;
	
	@NotEmpty
	private String district;
		
	@NotEmpty
	private String neighboorhood;
		
	@NotEmpty
	private String street;
}
