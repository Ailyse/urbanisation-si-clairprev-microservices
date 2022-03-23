package com.urbanisation_si.microservices_assure.model;

import java.time.LocalDate;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "personne_type")
public abstract class Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String nom;

	private String prenom;

	private Long numeroPersonne;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate dateNaissance;

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

	public Integer getId() {
		return id;
	}

}
