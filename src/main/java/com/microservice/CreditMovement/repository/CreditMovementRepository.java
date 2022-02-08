package com.microservice.CreditMovement.repository;

import com.microservice.CreditMovement.model.CreditMovement;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditMovementRepository extends ReactiveCrudRepository<CreditMovement, String> {

}
