package com.gazicabalar.service;

import com.gazicabalar.dto.DtoCustomer;
import com.gazicabalar.dto.DtoCustomerUI;

public interface ICustomerService {

	public DtoCustomer saveCustomer(DtoCustomerUI dtoCustomerUI);
	
}
