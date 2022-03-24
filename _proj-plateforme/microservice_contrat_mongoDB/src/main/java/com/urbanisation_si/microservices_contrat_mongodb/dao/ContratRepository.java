package com.urbanisation_si.microservices_contrat_mongodb.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.urbanisation_si.microservices_contrat_mongodb.model.Contrat;

public interface ContratRepository  extends MongoRepository<Contrat, String> {

	Optional<Contrat> findByNumeroContrat(Long numero);
	List<Contrat> findByNumeroAssure(Long numero);
	List<Contrat> findByNumeroProduit(Long numero);
	
}
