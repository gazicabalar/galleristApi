package com.gazicabalar.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gazicabalar.controller.IRestCustomerController;
import com.gazicabalar.controller.RestBaseController;
import com.gazicabalar.controller.RootEntity;
import com.gazicabalar.dto.DtoCustomer;
import com.gazicabalar.dto.DtoCustomerUI;
import com.gazicabalar.service.ICustomerService;

import jakarta.validation.Valid;

import java.util.List;

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

	@GetMapping("/get/{id}")
	@Override
	public RootEntity<DtoCustomer> getCustomer(@Valid @PathVariable Long id) {
		return ok(customerService.getCustomer(id));
	}

	@GetMapping("/all")
	@Override
	public RootEntity<List<DtoCustomer>> getAllCustomers() {
		return ok(customerService.getAllCustomers());
	}

	@PostMapping("/update/{id}")
	@Override
	public RootEntity<DtoCustomer> updateCustomer(@Valid @PathVariable Long id, @Valid @RequestBody DtoCustomerUI dtoCustomerUI) {
		return ok(customerService.updateCustomer(id, dtoCustomerUI));
	}

	@DeleteMapping("delete/{id}")
	@Override
	public RootEntity<String> deleteCustomer(@Valid @PathVariable Long id) {
		return ok(customerService.deleteCustomer(id));
	}


}
