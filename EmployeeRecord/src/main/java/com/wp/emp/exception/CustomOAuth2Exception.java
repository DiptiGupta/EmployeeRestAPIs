package com.wp.emp.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOAuth2Exception extends OAuth2Exception {
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public CustomOAuth2Exception(String msg) {
	        super(msg);
	    }
	}
