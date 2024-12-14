package com.gazicabalar.controller;

import com.gazicabalar.dto.DtoAccount;
import com.gazicabalar.dto.DtoAccountIU;

public interface IRestAccountController {

	public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
	
}
