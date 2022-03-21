package com.urbanisation_si.microservices_assure.http.controleur;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.urbanisation_si.microservices_assure.dao.AssureRepository;
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
}
