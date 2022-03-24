package fr.isika.al13.microservice_produit.http.controleur;

import java.net.URI;
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

import fr.isika.al13.microservice_produit.dao.ProduitRepository;
import fr.isika.al13.microservice_produit.exception.ProduitIntrouvableException;
import fr.isika.al13.microservice_produit.model.Produit;

@RestController
@RequestMapping(path = "/ref_produit")
public class ProduitControleur {

	@Autowired
	private ProduitRepository produitRepository;

//----------------------------------------CREER_PRODUIT----------------------------------------------------------------
	@PostMapping(path = "/ajouterProduit")
	public ResponseEntity<Void> creerProduit(@RequestBody Produit produit) {
		Produit produitAjoute = produitRepository.save(produit);

		if (produitAjoute == null)
			return ResponseEntity.noContent().build();

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produitAjoute.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

//----------------------------------------LISTER_PRODUITS----------------------------------------------------------------
	@GetMapping(path = "/listerProduits")
	public @ResponseBody Iterable<Produit> getAllProduits() {
		return produitRepository.findAll();
	}

//----------------------------------------TROUVE_PRODUIT_BY_NUMERO_PRODUIT----------------------------------------------------------------
	@GetMapping(path = "/ProduitByNumero/{numeroProduit}")
	public @ResponseBody Produit getProduitByNumeroProduit(@PathVariable Integer numeroProduit) {
		Optional<Produit> produitRecherche = produitRepository.findByNumeroProduit(numeroProduit.longValue());
		if (!produitRecherche.isPresent()) {
			throw new ProduitIntrouvableException(String
					.format("Produit avec le numero de produit " + numeroProduit + " non trouvé.", numeroProduit));
		}
		return produitRecherche.get();
	}

//----------------------------------------TROUVE_PRODUIT_BY_ID----------------------------------------------------------------
	@GetMapping(path = "/Produit/{id}")
	public @ResponseBody Produit getProduitById(@PathVariable Integer id) {
		Optional<Produit> produitRecherche = produitRepository.findById(id);
		if (!produitRecherche.isPresent()) {
			throw new ProduitIntrouvableException(String.format("Produit avec l'id " + id + " non trouvé.", id));
		}
		return produitRecherche.get();
	}

//----------------------------------------SUPPRIMER_PRODUIT_BY_ID----------------------------------------------------------------
	@DeleteMapping(path = "/Produit/{id}")
	public void supprimerProduit(@PathVariable Integer id) {
		produitRepository.deleteById(id);
	}

//----------------------------------------UPDATE_PRODUIT_BY_ID----------------------------------------------------------------	
	@PutMapping(path = "/modifierContrat")
	public void modifierProduit(@RequestBody Produit produit) {
		produitRepository.save(produit);
	}

}
