package com.gazicabalar.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gazicabalar.controller.IRestCustomerController;
import com.gazicabalar.controller.RestBaseController;
import com.gazicabalar.controller.RootEntity;
import com.gazicabalar.dto.DtoCustomer;
import com.gazicabalar.dto.DtoCustomerUI;
import com.gazicabalar.service.ICustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/customer")
public class RestCustomerController extends RestBaseController implements IRestCustomerController{

	@Autowired
	private ICustomerService customerService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerUI dtoCustomerUI) {
		return ok(customerService.saveCustomer(dtoCustomerUI));
	}

	
	
}
