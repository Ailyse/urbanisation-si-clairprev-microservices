package com.urbanisation_si.microservices_assure.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.urbanisation_si.microservices_assure.model.Assure;

public interface AssureRepository extends CrudRepository<Assure, Integer> {

	Optional<Assure> findByNomAndPrenom(String nom, String prenom);

	List<Assure> findByDateNaissanceBefore(LocalDate date);

	List<Assure> findByNomLike(String chaine);

	List<Assure> findByNomContaining(String chaine);

}
