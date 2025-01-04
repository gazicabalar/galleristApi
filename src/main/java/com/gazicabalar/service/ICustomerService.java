package com.gazicabalar.service;

import com.gazicabalar.dto.DtoCustomer;
import com.gazicabalar.dto.DtoCustomerUI;

import java.util.List;

public interface ICustomerService {

	public DtoCustomer saveCustomer(DtoCustomerUI dtoCustomerUI);

	public DtoCustomer getCustomer(Long id);

	public List<DtoCustomer> getAllCustomers();

	public DtoCustomer updateCustomer(Long id,DtoCustomerUI dtoCustomerUI);

	public String deleteCustomer(Long id);
	
}
