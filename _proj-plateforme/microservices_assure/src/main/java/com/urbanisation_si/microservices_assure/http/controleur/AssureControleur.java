package com.urbanisation_si.microservices_assure.http.controleur;

import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.urbanisation_si.microservices_assure.dao.AssureRepository;
import com.urbanisation_si.microservices_assure.exceptions.AssureIntrouvableException;
import com.urbanisation_si.microservices_assure.model.Assure;
import com.urbanisation_si.microservices_assure.model.Personne;

@RestController
@RequestMapping(path = "/previt")
public class AssureControleur {
	@Autowired
	private AssureRepository assureRepository;

	@PostMapping(path = "/ajouterAssure")
	public ResponseEntity<Void> creerAssure(@RequestBody Assure assure) {
		Personne assureAjoute = assureRepository.save(assure);

		if (assureAjoute == null)
			return ResponseEntity.noContent().build();

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(assureAjoute.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@GetMapping(path = "/listerLesAssures")
	public @ResponseBody Iterable<Personne> getAllAssures() {
		return assureRepository.findAll();
	}
	
	@GetMapping(path = "/Assure/{id}")
	public @ResponseBody Personne getPersonneById(@PathVariable Integer id) {
		Optional<Personne> assureRecherche = assureRepository.findById(id);
		if (!assureRecherche.isPresent()) {
			throw new AssureIntrouvableException(String.format("non trouvé", id));
		}
			
		return assureRecherche.get();
	}
	
	@GetMapping(path = "/rechercherAssureNomPrenom/{nom}/{prenom}")
	public @ResponseBody Personne getPersonneByNomAndPrenom(@PathVariable String nom, @PathVariable String prenom) {
		Optional<Personne> assureRecherche = assureRepository.findByNomAndPrenom(nom, prenom);
		if (!assureRecherche.isPresent()) {
			throw new AssureIntrouvableException(String.format(nom+ prenom +"non trouvé", nom, prenom));
		}
			
		return assureRecherche.get();
	}
	
	@GetMapping(path = "/rechercherAssureNom/{nom}")
	public @ResponseBody Personne getPersonneByContainNom(@PathVariable String nom) {
		Iterable<Personne> assureRecherche = assureRepository.findByNom(nom);
		if (!assureRecherche.equals(null)) {
			throw new AssureIntrouvableException(String.format(nom+"non trouvé", nom));
		}
		return (Personne) assureRecherche.iterator();
	}
	
	@GetMapping(path = "/Assure/avantDateNaissance/{dateNaissance}")
	public @ResponseBody Personne getPersonneByBeforeDate(@PathVariable LocalDate dateNaissance) {
		Iterable<Personne> assureRecherche = assureRepository.findByDateNaissanceBefore(dateNaissance);
		if (!assureRecherche.equals(null)) {
			throw new AssureIntrouvableException(String.format(dateNaissance +"non trouvé",dateNaissance));
		}
			
		return (Personne) assureRecherche.iterator();
	}
	
	
	
	

}
