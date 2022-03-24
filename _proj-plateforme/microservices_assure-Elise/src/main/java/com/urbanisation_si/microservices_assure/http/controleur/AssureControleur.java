package com.urbanisation_si.microservices_assure.http.controleur;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.urbanisation_si.microservices_assure.dao.AssureRepository;
import com.urbanisation_si.microservices_assure.exceptions.AssureIntrouvableException;
import com.urbanisation_si.microservices_assure.model.Assure;
import com.urbanisation_si.microservices_assure.model.Personne;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/previt")
@Api(description = "API pour les opérations CRUD pour les assurés")
public class AssureControleur {
	
	@Autowired
	private AssureRepository assureRepository;

//----------------------------------------CREER_ASSURE----------------------------------------------------------------
	@PostMapping(path = "/ajouterAssure")
	public ResponseEntity<Void> creerAssure(@Valid @RequestBody Assure assure) {
		Personne assureAjoute = assureRepository.save(assure);

		if (assureAjoute == null)
			return ResponseEntity.noContent().build();

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(assureAjoute.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

//----------------------------------------LISTER_ASSURES----------------------------------------------------------------
	@GetMapping(path = "/listerLesAssures")
	public @ResponseBody Iterable<Assure> getAllAssures() {
		return assureRepository.findAll();
	}

//----------------------------------------TROUVE_ASSURE_BY_ID----------------------------------------------------------------
	       
	@ApiOperation(value = "Recherche un assuré grâce à son ID à condition que celui-ci existe.") 
	@GetMapping(path = "/Assure/{id}")
	public @ResponseBody Assure getPAssureById(@PathVariable Integer id) {
		Optional<Assure> assureRecherche = assureRepository.findById(id);
		if (!assureRecherche.isPresent()) {
			throw new AssureIntrouvableException(String.format("Assure avec l'id" +id+ "non trouvé.", id));
		}
		return assureRecherche.get();
	}

//----------------------------------------TROUVE_ASSURE_BY_NOM_PRENOM----------------------------------------------------------------
	@GetMapping(path = "/rechercherAssureNomPrenom/{nom}/{prenom}")
	public @ResponseBody Assure getAssureByNomAndPrenom(@PathVariable String nom, @PathVariable String prenom) {
		Optional<Assure> assureRecherche = assureRepository.findByNomAndPrenom(nom, prenom);
		if (!assureRecherche.isPresent()) {
			throw new AssureIntrouvableException(String.format(nom+ prenom +" non trouvé.", nom, prenom));
		}
		return assureRecherche.get();
	}

//----------------------------------------TROUVE_ASSURE_CONTAIN_NOM----------------------------------------------------------------
	@GetMapping(path = "/rechercherAssureNom/{chaine}")
	public @ResponseBody List <Assure> getAssureByContainNom(@PathVariable String chaine) {
		List<Assure> assureRecherche = assureRepository.findByNomContaining(chaine);
		if (assureRecherche.size()==0){
			throw new AssureIntrouvableException(String.format(chaine+"non trouvé.", chaine));
		}
		return assureRecherche;
	}

//----------------------------------------TROUVE_ASSURE_BEFORE_DATENAISSANCE----------------------------------------------------------------
	@GetMapping(path = "/Assure/avantDateNaissance/{dateNaissance}")
	public @ResponseBody List <Assure> getPersonneByBeforeDate(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dateNaissance) {
		List <Assure> assureRecherche = assureRepository.findByDateNaissanceBefore(dateNaissance);
		if (assureRecherche.size()==0) {
			throw new AssureIntrouvableException(String.format("Assure ayant avant la date de naissance " +dateNaissance +" non trouvé.",dateNaissance));
		}
		return assureRecherche;
	}
	
//----------------------------------------SUPPRIMER_ASSURE_BY_ID----------------------------------------------------------------
	@DeleteMapping(path = "/Assure/{id}")
	public void supprimerAssurer(@PathVariable Integer id) {
		assureRepository.deleteById(id);
	}
	
//----------------------------------------UPDATE_ASSURE_BY_ID----------------------------------------------------------------	
	@PutMapping (path="/modifierAssure")    
    public void modifierAssure(@RequestBody Assure assure) {
      assureRepository.save(assure);
    }
	
}
