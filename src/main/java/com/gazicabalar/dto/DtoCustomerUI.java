package com.gazicabalar.dto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class DtoCustomerUI {

	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private String tckn;
	
	@NotNull
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date birthOfDate;
	
	@NotNull
	private Long addressId;
	
	@NotNull
	private Long accountID;
	
}
