package com.urbanisation_si.microservices_assure.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AssureIntrouvableException extends RuntimeException {

	private static final long serialVersionUID = 145482848L;

	public AssureIntrouvableException(String message) {
		super(message);
	}

}
