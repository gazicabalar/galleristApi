package com.gazicabalar.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCustomer extends DtoBase{

	private String firstName;
	
	private String lastName;
	
	private String tckn;
	
	private Date birthOfDate;
		
	private DtoAddress dtoAddress;
		
	private DtoAccount dtoAccount;
	
}
