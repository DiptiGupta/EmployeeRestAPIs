package com.wp.emp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="User Not Found") //404
public class DisabledException extends UsernameNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DisabledException(String msg) {
		super(msg);
	}
}
	