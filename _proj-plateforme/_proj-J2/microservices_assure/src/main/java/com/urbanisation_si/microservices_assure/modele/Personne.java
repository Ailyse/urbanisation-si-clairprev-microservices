/**
 * 
 */
package com.urbanisation_si.microservices_assure.modele;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Patrice
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Personne {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Integer id;

	protected String nom;
	
	protected String prenom;
	
	protected Long numeroPersonne;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateNaissance;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Long getNumeroPersonne() {
		return numeroPersonne;
	}

	public void setNumeroPersonne(Long numeroPersonne) {
		this.numeroPersonne = numeroPersonne;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Personne(Integer id, String nom, String prenom, Long numeroPersonne, LocalDate dateNaissance) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.numeroPersonne = numeroPersonne;
		this.dateNaissance = dateNaissance;
	}

	public Personne() {
		super();
		// TODO Auto-generated constructor stub
	}

	


	
	
}
