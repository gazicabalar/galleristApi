package com.gazicabalar.controller;

import com.gazicabalar.dto.DtoAddress;
import com.gazicabalar.dto.DtoAddressIU;

import java.util.List;

public interface IRestAddressController {

	public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);

	public RootEntity<DtoAddress> getAddress(Long id);

	public RootEntity<List<DtoAddress>> getAllAccounts();

	public RootEntity<DtoAddress> updateAddress(Long id, DtoAddressIU dtoAddressIU);

	public RootEntity<String> deleteAddress(Long id);
	
}
