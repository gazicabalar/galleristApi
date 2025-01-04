package com.gazicabalar.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoCustomer extends DtoBase{

	private String firstName;
	
	private String lastName;
	
	private String tckn;
	
	private Date birthOfDate;
		
	private DtoAddress dtoAddress;
		
	private DtoAccount dtoAccount;
	
}
