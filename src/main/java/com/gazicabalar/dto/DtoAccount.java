package com.gazicabalar.dto;

import java.math.BigDecimal;

import com.gazicabalar.enums.CurrencyType;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoAccount extends DtoBase{

	private String accountNo;
	
	private String iban;
	
	private BigDecimal amount;
	
	private CurrencyType currencyType;

}
