package fr.isika.al13.microservice_produit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProduitIntrouvableException extends RuntimeException {

	private static final long serialVersionUID = 1454828454438L;

	public ProduitIntrouvableException(String message) {
		super(message);
	}

}
