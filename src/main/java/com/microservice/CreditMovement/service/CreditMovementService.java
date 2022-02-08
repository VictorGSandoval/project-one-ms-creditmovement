package com.microservice.CreditMovement.service;


import org.springframework.stereotype.Service;
import com.microservice.CreditMovement.model.CreditMovement;
import com.microservice.CreditMovement.repository.CreditMovementRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreditMovementService {
	
	 private  final CreditMovementRepository creditMovementRepository;

	  public Flux<CreditMovement> getAllCreditMovement(){
	    return creditMovementRepository.findAll();
	  }
	  public Mono<CreditMovement> getCreditMovementById(String id){
	    return  creditMovementRepository.findById(id);
	  }
	  public Mono<CreditMovement> createCreditMovement(CreditMovement creditMovement){
	    return creditMovementRepository.save(creditMovement);
	  }
	  public Mono<CreditMovement> updateCreditMovement(String id, CreditMovement creditMovement){
	    return creditMovementRepository.findById(id)
	            .flatMap(bean -> {
	              bean.setAmount(creditMovement.getAmount());
	              bean.setDateStart(creditMovement.getDateStart());
	              bean.setDateLimit(creditMovement.getDateLimit());
	              bean.setCommission(creditMovement.getCommission());
				  bean.setDescription(creditMovement.getDescription());
	              bean.setIdAccountCustomer(creditMovement.getIdAccountCustomer());
				  bean.setIdAccountDestination(creditMovement.getIdAccountDestination());
	              return creditMovementRepository.save(bean);
	            });
	  }
	  public Mono<CreditMovement> deleteCreditMovement(String id){
	    return creditMovementRepository.findById(id)
	            .flatMap(existsAccount -> creditMovementRepository.delete(existsAccount)
	                    .then(Mono.just(existsAccount)));
	  }


}
