package com.gazicabalar.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gazicabalar.dto.DtoAccount;
import com.gazicabalar.dto.DtoAddress;
import com.gazicabalar.dto.DtoCustomer;
import com.gazicabalar.dto.DtoCustomerUI;
import com.gazicabalar.exception.BaseException;
import com.gazicabalar.exception.ErrorMessage;
import com.gazicabalar.exception.MessageType;
import com.gazicabalar.model.Account;
import com.gazicabalar.model.Address;
import com.gazicabalar.model.Customer;
import com.gazicabalar.repository.AccountRepository;
import com.gazicabalar.repository.AddressRepository;
import com.gazicabalar.repository.CustomerRepository;
import com.gazicabalar.service.ICustomerService;	

@Service
public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	public Customer createCustomer(DtoCustomerUI dtoCustomerUI) {
		Customer customer = new Customer();
		customer.setCreatTime(new Date());
		
		Optional<Address> optAddress = addressRepository.findById(dtoCustomerUI.getAddressId());
		
		if(optAddress.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, dtoCustomerUI.getAddressId().toString()));
		
		Optional<Account> optAccount = accountRepository.findById(dtoCustomerUI.getAccountID());
		
		if(optAccount.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, dtoCustomerUI.getAccountID().toString()));
		
		BeanUtils.copyProperties(dtoCustomerUI, customer);
		
		customer.setAddress(optAddress.get());
		customer.setAccount(optAccount.get());
		
		return customer;
	}
	
	@Override
	public DtoCustomer saveCustomer(DtoCustomerUI dtoCustomerUI) {
		DtoCustomer dtoCustomer = new DtoCustomer();
		DtoAddress dtoAddress = new DtoAddress();
		DtoAccount dtoAccount = new DtoAccount();
		Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerUI));
		
		BeanUtils.copyProperties(savedCustomer, dtoCustomer);
		BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);
		BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);
		
		dtoCustomer.setDtoAddress(dtoAddress);
		dtoCustomer.setDtoAccount(dtoAccount);
		
		return dtoCustomer;
	}

	
	
}
