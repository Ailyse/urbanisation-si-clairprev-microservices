package com.urbanisation_si.microservices_assure.dao;

import org.springframework.data.repository.CrudRepository;

import com.urbanisation_si.microservices_assure.model.Personne;


public interface AssureRepository extends CrudRepository <Personne, Integer> {

	
}
