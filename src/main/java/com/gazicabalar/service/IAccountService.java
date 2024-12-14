package com.gazicabalar.service;

import com.gazicabalar.dto.DtoAccount;
import com.gazicabalar.dto.DtoAccountIU;

public interface IAccountService {

	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
	
}
