package com.urbanisation_si.microservices_contrat_mongodb.http.controleur;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.urbanisation_si.microservices_contrat_mongodb.dao.ContratRepository;
import com.urbanisation_si.microservices_contrat_mongodb.exception.ContratIntrouvableException;
import com.urbanisation_si.microservices_contrat_mongodb.model.Contrat;

@RestController
@RequestMapping(path = "/ref_contrat")
public class ContratControleur {

	@Autowired
	private ContratRepository contratRepository;

//----------------------------------------CREER_CONTRATS----------------------------------------------------------------
	@PostMapping(path = "/ajouterContrat")
	public ResponseEntity<Void> creerContrat(@RequestBody Contrat contrat) {
		Contrat contratAjoute = contratRepository.save(contrat);

		if (contratAjoute == null)
			return ResponseEntity.noContent().build();

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contratAjoute.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

//----------------------------------------LISTER_CONTRATS----------------------------------------------------------------
	@GetMapping(path = "/listerContrats")
	public @ResponseBody Iterable<Contrat> getAllContrats() {
		return contratRepository.findAll();
	}

//----------------------------------------TROUVE_CONTRAT_BY_NUMERO_CONTRAT----------------------------------------------------------------
	@GetMapping(path = "/contratByNumeroContrat/{numeroContrat}")
	public @ResponseBody Contrat getContratByNumeroContrat(@PathVariable Integer numeroContrat) {
		Optional<Contrat> contratRecherche = contratRepository.findByNumeroContrat(numeroContrat.longValue());
		if (!contratRecherche.isPresent()) {
			throw new ContratIntrouvableException(String
					.format("Contrat avec le numero de Contrat " + numeroContrat + " non trouvé.", numeroContrat));
		}
		return contratRecherche.get();
	}

//----------------------------------------TROUVE_CONTRATS_BY_NUMERO_ASSURE----------------------------------------------------------------
	@GetMapping(path = "/contratByNumeroAssure/{numeroAssure}")
	public @ResponseBody List<Contrat> getContratByNumeroAssure(@PathVariable Integer numeroAssure) {
		List<Contrat> contratRecherche = contratRepository.findByNumeroAssure(numeroAssure.longValue());
		if (contratRecherche.equals(null)) {
			throw new ContratIntrouvableException(
					String.format("Contrat avec le numero de Assure " + numeroAssure + " non trouvé.", numeroAssure));
		}
		return contratRecherche;
	}

//----------------------------------------TROUVE_CONTRATS_BY_NUMERO_PRODUIT----------------------------------------------------------------
	@GetMapping(path = "/contratByNumeroProduit/{numeroProduit}")
	public @ResponseBody List<Contrat> getContratByNumeroProduit(@PathVariable Integer numeroProduit) {
		List<Contrat> contratRecherche = contratRepository.findByNumeroProduit(numeroProduit.longValue());
		if (contratRecherche.equals(null)) {
			throw new ContratIntrouvableException(String
					.format("Contrat avec le numero de Produit " + numeroProduit + " non trouvé.", numeroProduit));
		}
		return contratRecherche;
	}

//----------------------------------------TROUVE_CONTRAT_BY_ID----------------------------------------------------------------
	@GetMapping(path = "/Contrat/{id}")
	public @ResponseBody Contrat getContratById(@PathVariable String id) {
		Optional<Contrat> contratRecherche = contratRepository.findById(id);
		if (!contratRecherche.isPresent()) {
			throw new ContratIntrouvableException(String.format("Contrat avec l'id " + id + " non trouvé.", id));
		}
		return contratRecherche.get();
	}

//----------------------------------------SUPPRIMER_CONTRAT_BY_ID----------------------------------------------------------------
	@DeleteMapping(path = "/Contrat/{id}")
	public void supprimerContrat(@PathVariable String id) {
		contratRepository.deleteById(id);
	}

//----------------------------------------UPDATE_CONTRAT_BY_ID----------------------------------------------------------------	
	@PutMapping(path = "/modifierContrat")
	public void modifierContrat(@RequestBody Contrat contrat) {
		contratRepository.save(contrat);
	}

}
