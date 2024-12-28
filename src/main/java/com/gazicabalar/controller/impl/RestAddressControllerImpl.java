package com.gazicabalar.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gazicabalar.controller.IRestAddressController;
import com.gazicabalar.controller.RestBaseController;
import com.gazicabalar.controller.RootEntity;
import com.gazicabalar.dto.DtoAddress;
import com.gazicabalar.dto.DtoAddressIU;
import com.gazicabalar.service.IAddressService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController{

	@Autowired
	private IAddressService addressService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
		return ok(addressService.saveAddress(dtoAddressIU));
	}

	@GetMapping("/{id}")
	@Override
	public RootEntity<DtoAddress> getAddress(@Valid @PathVariable Long id) {
		return ok(addressService.getAddress(id));
	}

	@GetMapping("/all")
	@Override
	public RootEntity<List<DtoAddress>> getAllAccounts() {
		return ok(addressService.getAllAddress());
	}

	@PostMapping("/update/{id}")
	@Override
	public RootEntity<DtoAddress> updateAddress(@Valid @PathVariable Long id, @Valid @RequestBody DtoAddressIU dtoAddressIU) {
		return ok(addressService.updateAddress(id, dtoAddressIU));
	}

	@DeleteMapping("delete/{id}")
	@Override
	public RootEntity<String> deleteAddress(@Valid @PathVariable Long id) {
		return ok(addressService.deleteAddess(id));
	}


}
