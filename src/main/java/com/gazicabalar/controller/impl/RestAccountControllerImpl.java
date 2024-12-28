package com.gazicabalar.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gazicabalar.controller.IRestAccountController;
import com.gazicabalar.controller.RestBaseController;
import com.gazicabalar.controller.RootEntity;
import com.gazicabalar.dto.DtoAccount;
import com.gazicabalar.dto.DtoAccountIU;
import com.gazicabalar.service.IAccountService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountControllerImpl extends RestBaseController implements IRestAccountController{

	@Autowired
	private IAccountService accountService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
		return ok(accountService.saveAccount(dtoAccountIU));
	}

	@GetMapping("/{id}")
	@Override
	public RootEntity<DtoAccount> getAccount(@Valid @PathVariable Long id) {
		return ok(accountService.getAccount(id));
	}

	@GetMapping("/all")
	@Override
	public RootEntity<List<DtoAccount>> getAllAccounts() {
		return ok(accountService.getAllAccounts());
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoAccount> updateAccount(@PathVariable Long id, @RequestBody DtoAccountIU dtoAccountIU) {
		return ok(accountService.updateAccount(id, dtoAccountIU));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<String> deleteAccount(@PathVariable Long id) {
		return ok(accountService.deleteAccount(id));
	}


}
