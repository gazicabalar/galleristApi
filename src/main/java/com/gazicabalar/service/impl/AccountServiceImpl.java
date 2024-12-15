package com.gazicabalar.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.gazicabalar.exception.BaseException;
import com.gazicabalar.exception.ErrorMessage;
import com.gazicabalar.exception.MessageType;
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

	@Override
	public DtoAccount getAccount(Long id) {
		DtoAccount dtoAccount = new DtoAccount();

		Optional<Account> getAccount = accountRepository.findById(id);

		if(getAccount.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, id.toString()));
		}

		BeanUtils.copyProperties(getAccount.get(), dtoAccount);

		return dtoAccount;
	}

	@Override
	public List<DtoAccount> getAllAccounts() {
		List<DtoAccount> dtoAccounts = new ArrayList<>();

		List<Account> accounts = accountRepository.findAll();

		if(accounts.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, accounts.toString()));
		}

		for(Account account : accounts) {
			DtoAccount dtoAccount = new DtoAccount(
					account.getAccountNo(),
					account.getIban(),
					account.getAmount(),
					account.getCurrencyType()
			);
			dtoAccounts.add(dtoAccount);
		}

		return dtoAccounts;
	}

	@Override
	public DtoAccount updateAccount(Long id, DtoAccountIU dtoAccountIU) {
		DtoAccount dtoAccount = new DtoAccount();

		Optional<Account> account = accountRepository.findById(id);

		if(account.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, account.toString()));
		}

		account.get().setAccountNo(dtoAccountIU.getAccountNo());
		account.get().setIban(dtoAccountIU.getIban());
		account.get().setAmount(dtoAccountIU.getAmount());
		account.get().setCurrencyType(dtoAccountIU.getCurrencyType());

		Account savedAccount = accountRepository.save(account.get());

		BeanUtils.copyProperties(savedAccount, dtoAccount);

		return dtoAccount;
	}

	@Override
	public String deleteAccount(Long id) {

		Optional<Account> account = accountRepository.findById(Long.valueOf(id));

		if(account.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, account.toString()));
		}

		Integer account_id = Math.toIntExact(account.get().getId());

		accountRepository.delete(account.get());

		return "Account deleted = " + account_id.toString();
	}

}
