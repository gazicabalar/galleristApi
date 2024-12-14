package com.gazicabalar.controller;

import com.gazicabalar.dto.DtoAddress;
import com.gazicabalar.dto.DtoAddressIU;

public interface IRestAddressController {

	public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
	
}
