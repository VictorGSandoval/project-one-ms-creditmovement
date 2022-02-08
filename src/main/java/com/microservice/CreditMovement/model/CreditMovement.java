package com.microservice.CreditMovement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class CreditMovement {
	
	
	@Id
	private String id;
	private double amount;
	private String dateStart;
	private String dateLimit;
	private String commission;
	private String description;
	private int idAccountCustomer;
	private int idAccountDestination;


}
