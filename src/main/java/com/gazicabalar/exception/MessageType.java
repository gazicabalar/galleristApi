package com.gazicabalar.exception;

import lombok.Getter;

@Getter
public enum MessageType {

	NO_RECORDS_EXIST("1004", "Kayıt Bulunamadı"),
	TOKEN_IS_EXPIRED("1005", "Token'ın süresi bitmiştir"),
	USERNAME_NOT_FOUND("1006", "username bulunamadı"),
	USERNAME_OR_PASSWORD_INVALID("1007", "Kullanıcı Adı veya Şifre Hatalı"),
	REFRESH_TOKEN_NOT_FOUND("1008", "Refreshtoken bulunamadı"),
	REFRESH_TOKEN_IS_EXPIRED("1009", "Refreshtoken'ın süresi bitmiştir"),
	CURRENCY_RATES_IS_OCCURED("1010", "döviz kuru alınamadı"),
	CUSTOMER_AMOUNT_IS_NOT_ENOUGH("1011", "müşterinin parası yeterli değil"),
	CAR_STATUS_IS_ALREADY_SALED("1012", "araba satılmış göründüğü için satışa uygun değildir"),
	GENERAL_EXCEPTION("9999", "Genel Hata Oluştu");
	
	private String code;
	private String message;
	
	MessageType(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
}
