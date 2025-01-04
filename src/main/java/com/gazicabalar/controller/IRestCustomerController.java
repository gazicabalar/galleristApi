package com.gazicabalar.controller;

import com.gazicabalar.dto.DtoCustomer;
import com.gazicabalar.dto.DtoCustomerUI;

import java.util.List;

public interface IRestCustomerController {

	public RootEntity<DtoCustomer> saveCustomer(DtoCustomerUI dtoCustomerUI);

	public RootEntity<DtoCustomer> getCustomer(Long id);

	public RootEntity<List<DtoCustomer>> getAllCustomers();

	public RootEntity<DtoCustomer> updateCustomer(Long id,DtoCustomerUI dtoCustomerUI);

	public RootEntity<String> deleteCustomer(Long id);
	
}
