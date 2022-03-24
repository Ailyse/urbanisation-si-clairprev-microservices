package fr.isika.al13.microservice_contrat.dao.copy;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import fr.isika.al13.microservice_contrat.model.Contrat;

public interface ContratRepository  extends CrudRepository<Contrat, Integer> {

	Optional<Contrat> findByNumeroContrat(Long numero);
	List<Contrat> findByNumeroAssure(Long numero);
	List<Contrat> findByNumeroProduit(Long numero);
	
}
