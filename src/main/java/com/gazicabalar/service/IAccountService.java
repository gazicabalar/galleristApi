package com.gazicabalar.service;

import com.gazicabalar.dto.DtoAccount;
import com.gazicabalar.dto.DtoAccountIU;

import java.util.List;

public interface IAccountService {

	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);

	public DtoAccount getAccount(Long id);

	public List<DtoAccount> getAllAccounts();

	public DtoAccount updateAccount(Long id,DtoAccountIU dtoAccountIU);

	public String deleteAccount(Long id);
	
}
