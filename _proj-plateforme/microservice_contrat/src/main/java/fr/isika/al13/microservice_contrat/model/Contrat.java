package fr.isika.al13.microservice_contrat.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Contrat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateDebut;

	private Long numeroContrat;

	private Long numeroAssure;

	private Long numeroProduit;

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Long getNumeroContrat() {
		return numeroContrat;
	}

	public void setNumeroContrat(Long numeroContrat) {
		this.numeroContrat = numeroContrat;
	}

	public Long getNumeroAssure() {
		return numeroAssure;
	}

	public void setNumeroAssure(Long numeroAssure) {
		this.numeroAssure = numeroAssure;
	}

	public Long getNumeroProduit() {
		return numeroProduit;
	}

	public void setNumeroProduit(Long numeroProduit) {
		this.numeroProduit = numeroProduit;
	}

	public Integer getId() {
		return id;
	}

}
