package com.gazicabalar.service;

import com.gazicabalar.dto.CurrencyRatesResponse;

public interface ICurrencyRatesService {

	public CurrencyRatesResponse getCurrencyRatesResponse(String startDate, String endDate);
	
}
