package com.gazicabalar.controller;

import com.gazicabalar.dto.CurrencyRatesResponse;

public interface IRestCurrencyRatesController {

	public RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate, String endDate);
	
}
