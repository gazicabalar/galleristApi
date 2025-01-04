package com.gazicabalar.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity{

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "tckn")
	private String tckn;
	
	@Column(name = "birth_of_date")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date birthOfDate;
	
	@OneToOne
	private Address address;
	
	@OneToOne
	private Account account;
	
	
}
