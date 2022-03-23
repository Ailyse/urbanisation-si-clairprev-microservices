package com.urbanisation_si.microservices_assure.dao;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.urbanisation_si.microservices_assure.model.Personne;


public interface AssureRepository extends CrudRepository <Personne, Integer> {

	Optional<Personne> findByNomAndPrenom(String nom, String prenom);
	Iterable<Personne> findByDateNaissanceBefore(LocalDate date);
	Iterable<Personne> findByNom(String nom);
	
}
