package com.gazicabalar.service;

import com.gazicabalar.dto.DtoAddress;
import com.gazicabalar.dto.DtoAddressIU;

import java.util.List;

public interface IAddressService {

	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);

	public DtoAddress getAddress(Long id);

	public List<DtoAddress> getAllAddress();

	public DtoAddress updateAddress(Long id, DtoAddressIU dtoAddressIU);

	public String deleteAddess(Long id);
	
}
