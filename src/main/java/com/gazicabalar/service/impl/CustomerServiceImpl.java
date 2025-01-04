package com.gazicabalar.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

	@Override
	public DtoCustomer getCustomer(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);

		if(customer.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, id.toString()));

		DtoAddress dtoAddress = new DtoAddress(
				customer.get().getAddress().getCity(),
				customer.get().getAddress().getDistrict(),
				customer.get().getAddress().getNeighboorhood(),
				customer.get().getAddress().getStreet()
		);

		DtoAccount dtoAccount = new DtoAccount(
				customer.get().getAccount().getAccountNo(),
				customer.get().getAccount().getIban(),
				customer.get().getAccount().getAmount(),
				customer.get().getAccount().getCurrencyType()
		);

		DtoCustomer dtoCustomer = new DtoCustomer(
				customer.get().getFirstName(),
				customer.get().getLastName(),
				customer.get().getTckn(),
				customer.get().getBirthOfDate(),
				dtoAddress,
				dtoAccount
		);

		return dtoCustomer;
	}

	@Override
	public List<DtoCustomer> getAllCustomers() {
		List<DtoCustomer> dtoCustomers = new ArrayList<>();
		List<Customer> customers = customerRepository.findAll();

		if(customers.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, ""));

		for(Customer customer : customers){
			DtoAddress dtoAddress = new DtoAddress(
					customer.getAddress().getCity(),
					customer.getAddress().getDistrict(),
					customer.getAddress().getNeighboorhood(),
					customer.getAddress().getStreet()
			);

			DtoAccount dtoAccount = new DtoAccount(
					customer.getAccount().getAccountNo(),
					customer.getAccount().getIban(),
					customer.getAccount().getAmount(),
					customer.getAccount().getCurrencyType()
			);
			DtoCustomer dtoCustomer = new DtoCustomer(
					customer.getFirstName(),
					customer.getLastName(),
					customer.getTckn(),
					customer.getBirthOfDate(),
					dtoAddress,
					dtoAccount
			);
			dtoCustomers.add(dtoCustomer);
		}

		return dtoCustomers;
	}

	@Override
	public DtoCustomer updateCustomer(Long id, DtoCustomerUI dtoCustomerUI) {
		Optional<Customer> customer = customerRepository.findById(id);
		Optional<Address> customerAddressId = addressRepository.findById(dtoCustomerUI.getAddressId());
		Optional<Account> customerAccountId = accountRepository.findById(dtoCustomerUI.getAccountID());

		DtoAddress dtoAddress = new DtoAddress();
		DtoAccount dtoAccount = new DtoAccount();

		if(customer.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, id.toString()));

		if(customerAddressId.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, id.toString()));

		if(customerAccountId.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, id.toString()));


		customer.get().setFirstName(dtoCustomerUI.getFirstName());
		customer.get().setLastName(dtoCustomerUI.getLastName());
		customer.get().setTckn(dtoCustomerUI.getTckn());
		customer.get().setBirthOfDate(dtoCustomerUI.getBirthOfDate());
		customer.get().setAddress(customerAddressId.get());
		customer.get().setAccount(customerAccountId.get());

		Customer savedCustomer = customerRepository.save(customer.get());

		BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);
		BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);

		DtoCustomer dtoCustomer = new DtoCustomer(
				savedCustomer.getFirstName(),
				savedCustomer.getLastName(),
				savedCustomer.getTckn(),
				savedCustomer.getBirthOfDate(),
				dtoAddress,
				dtoAccount
		);

		return dtoCustomer;
	}

	@Override
	public String deleteCustomer(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);

		if(customer.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, id.toString()));

		Long customerId = customer.get().getId();

		customerRepository.delete(customer.get());

		return "Customer is deleted : " + customerId;
	}


}
