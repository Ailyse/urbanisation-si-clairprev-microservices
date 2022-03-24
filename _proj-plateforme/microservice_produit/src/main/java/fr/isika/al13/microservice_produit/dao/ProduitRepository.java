package fr.isika.al13.microservice_produit.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import fr.isika.al13.microservice_produit.model.Produit;

public interface ProduitRepository extends CrudRepository<Produit, Integer>{

	Optional<Produit> findByNumeroProduit(Long numero);
}
