package com.gazicabalar.controller;

import com.gazicabalar.dto.DtoAccount;
import com.gazicabalar.dto.DtoAccountIU;

import java.util.List;

public interface IRestAccountController {

	public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);

	public RootEntity<DtoAccount> getAccount(Long id);

	public RootEntity<List<DtoAccount>> getAllAccounts();

	public RootEntity<DtoAccount> updateAccount(Long id,DtoAccountIU dtoAccountIU);

	public RootEntity<String> deleteAccount(Long id);
	
}
