package com.gazicabalar.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gazicabalar.dto.DtoAccount;
import com.gazicabalar.dto.DtoAccountIU;
import com.gazicabalar.model.Account;
import com.gazicabalar.repository.AccountRepository;
import com.gazicabalar.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService{

	@Autowired
	private AccountRepository accountRepository;
	
	private Account createAccount(DtoAccountIU dtoAccountIU) {
		Account account = new Account();
		account.setCreatTime(new Date());
		
		BeanUtils.copyProperties(dtoAccountIU, account);
		return account;		
	}
	
	@Override
	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {
		DtoAccount dtoAccount = new DtoAccount();
		Account savedAccount = accountRepository.save(createAccount(dtoAccountIU));
		
		BeanUtils.copyProperties(savedAccount, dtoAccount);
		
		return dtoAccount;
	}

}
