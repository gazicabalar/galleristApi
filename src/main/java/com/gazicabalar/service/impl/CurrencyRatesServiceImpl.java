package com.gazicabalar.service.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gazicabalar.dto.CurrencyRatesResponse;
import com.gazicabalar.exception.BaseException;
import com.gazicabalar.exception.ErrorMessage;
import com.gazicabalar.exception.MessageType;
import com.gazicabalar.service.ICurrencyRatesService;

@Service
public class CurrencyRatesServiceImpl implements ICurrencyRatesService{

	
	@Override
	public CurrencyRatesResponse getCurrencyRatesResponse(String startDate, String endDate) {
		String rootURL = "https://evds2.tcmb.gov.tr/service/evds/";
		String series = "TP.DK.USD.A";
		String type = "json";
		
		String endpoint = rootURL + "series=" + series + "&startDate=" + startDate + "&endDate=" + endDate + "&type=" + type;

		org.springframework.http.HttpHeaders httpHeaders = new org.springframework.http.HttpHeaders();
		httpHeaders.set("key", "uDxq0y8fgt");			
		
		HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
		
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			
			ResponseEntity<CurrencyRatesResponse> response = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<CurrencyRatesResponse>() {
			}); 
			
			if(response.getStatusCode().is2xxSuccessful()) {
				return response.getBody();
			}	
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED, e.getMessage()));
		}
			
		return null;
		
	}

	
	
}
