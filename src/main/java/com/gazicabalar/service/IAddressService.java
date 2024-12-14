package com.gazicabalar.service;

import com.gazicabalar.dto.DtoAddress;
import com.gazicabalar.dto.DtoAddressIU;

public interface IAddressService {

	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
	
	
}
