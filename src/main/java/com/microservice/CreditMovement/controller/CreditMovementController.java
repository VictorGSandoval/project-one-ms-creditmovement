package com.microservice.CreditMovement.controller;


import com.microservice.CreditMovement.model.CreditMovement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/CreditMovement")
public class CreditMovementController {
	
private final com.microservice.CreditMovement.service.CreditMovementService creditMovementService;
	
	@GetMapping
	public Mono<ResponseEntity<Flux<CreditMovement>>>getAllCreditMovement() {
		Flux<CreditMovement> list=this.creditMovementService.getAllCreditMovement();
		return  Mono.just(ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(list));
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<CreditMovement>> getCreditMovementById(@PathVariable String id){
		var creditMovement=this.creditMovementService.getCreditMovementById(id);
		return creditMovement.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<CreditMovement> create(@RequestBody CreditMovement account){
		return this.creditMovementService.createCreditMovement(account);
	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<CreditMovement>> updateCreditMovementById(@PathVariable String id, @RequestBody CreditMovement creditMovement){

		return this.creditMovementService.updateCreditMovement(id,creditMovement)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.badRequest().build());
	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> deleteCreditMovementById(@PathVariable String id){
		return this.creditMovementService.deleteCreditMovement(id)
				.map(r -> ResponseEntity.ok().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
}
