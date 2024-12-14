package com.gazicabalar.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gazicabalar.dto.CurrencyRatesResponse;
import com.gazicabalar.dto.DtoCar;
import com.gazicabalar.dto.DtoCustomer;
import com.gazicabalar.dto.DtoGallerist;
import com.gazicabalar.dto.DtoSaledCar;
import com.gazicabalar.dto.DtoSaledCarIU;
import com.gazicabalar.enums.CarStatusType;
import com.gazicabalar.exception.BaseException;
import com.gazicabalar.exception.ErrorMessage;
import com.gazicabalar.exception.MessageType;
import com.gazicabalar.model.Car;
import com.gazicabalar.model.Customer;
import com.gazicabalar.model.SaledCar;
import com.gazicabalar.repository.CarRepository;
import com.gazicabalar.repository.CustomerRepository;
import com.gazicabalar.repository.GalleristRepository;
import com.gazicabalar.repository.SaledCarRepository;
import com.gazicabalar.service.ICurrencyRatesService;
import com.gazicabalar.service.ISaledCarService;
import com.gazicabalar.utils.DateUtils;

@Service
public class SaledCarServiceImpl implements ISaledCarService{
	
	@Autowired
	private SaledCarRepository saledCarRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private ICurrencyRatesService currencyRatesService;
		
	private BigDecimal convertCustomerAmountToUSD(Customer customer) {
		CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRatesResponse(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
		BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
		
		BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);
		
		return customerUSDAmount;
	}

	private boolean checkCarStatus(Long carId) {
		Optional<Car> optCar = carRepository.findById(carId);
		
		if(optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name()))
			return false;
		
		return true;
	}
	
	private BigDecimal remainingCustomerAmount(Customer customer, Car car) {
		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
		BigDecimal remainingCustomerUSDAmount = customerUSDAmount.subtract(car.getPrice());
		
		CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRatesResponse(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
		BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
		
		return remainingCustomerUSDAmount.multiply(usd);
		
	}
	
	private boolean checkAmount(DtoSaledCarIU dtoSaledCarIU) {
		
		Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
		
		if(optCustomer.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, dtoSaledCarIU.getCustomerId().toString()));
		
		Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarId());
		
		if(optCustomer.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORDS_EXIST, dtoSaledCarIU.getCarId().toString()));
		
		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optCustomer.get());
		
		if(customerUSDAmount.compareTo(optCar.get().getPrice())==0 || customerUSDAmount.compareTo(optCar.get().getPrice())>0) {
			return true;
		}
		
		return false;
		
	}
	
	private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU) {
		SaledCar saledCar = new SaledCar();
		saledCar.setCreatTime(new Date());
		
		saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
		saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
		saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));
		
		return saledCar;
		
	}
	
	@Override
	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {
		if(!checkAmount(dtoSaledCarIU))
			throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, ""));
		
		if(!checkCarStatus(dtoSaledCarIU.getCarId()))
			throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SALED, dtoSaledCarIU.getCarId().toString()));
		
		SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));
		
		Car car = savedSaledCar.getCar();
		car.setCarStatusType(CarStatusType.SALED);
		carRepository.save(car);
		
		Customer customer = savedSaledCar.getCustomer();
		customer.getAccount().setAmount(remainingCustomerAmount(customer, car));
		customerRepository.save(customer);
			
		return toDto(savedSaledCar);
	}
	
	private DtoSaledCar toDto(SaledCar saledCar) {
		DtoSaledCar dtoSaledCar = new DtoSaledCar();
		DtoCustomer dtoCustomer = new DtoCustomer();
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoCar dtoCar = new DtoCar();
		
		BeanUtils.copyProperties(saledCar, dtoSaledCar);
		BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
		BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);
		BeanUtils.copyProperties(saledCar.getCar(), dtoCar);
		
		dtoSaledCar.setDtoCustomer(dtoCustomer);
		dtoSaledCar.setDtoGallerist(dtoGallerist);
		dtoSaledCar.setDtoCar(dtoCar);
		
		return dtoSaledCar;
	}

	
	
}
