
/**
 * Filename:    Controller.java
 *
 * Description: Implementation of the Controller class.
 *
 * Revision:    1.0
 *
 * Changed by:  lk
 *
 * Author:      Lucas Kissmann
 * Email:       lucaskissmann@gmail.com
 *
 * Copyright (c) [2024] Lucas Kissmann
 * This program is part of the voting challenge by DB.
 * It is intellectual property and may only be used and/or copied with permission.
 * Challenge: https://github.com/dbserver/desafio-votacao
 * 
 */

package com.db.desafio.votacao.v1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Controller {

	public static final String VERSION = "v1/";

	/**
	 * created
	 *
	 * @return ResponseEntity<T>
	 */
	protected <T> ResponseEntity<T> created()
	{
		return new ResponseEntity<>( HttpStatus.CREATED );
	}

	/**
	 * created
	 *
	 * @param body T
	 * @return ResponseEntity<T>
	 */
	protected <T> ResponseEntity<T> created( T body )
	{
		return new ResponseEntity<>( body, HttpStatus.CREATED );
	}

	/**
	 * ok
	 *
	 * @return ResponseEntity<T>
	 */
	protected <T> ResponseEntity<T> ok()
	{
		return new ResponseEntity<>( HttpStatus.OK );
	}

	/**
	 * ok
	 *
	 * @param body T
	 * @return ResponseEntity<T>
	 */
	protected <T> ResponseEntity<T> ok( T body )
	{
		return new ResponseEntity<>( body, HttpStatus.OK );
	}


	/**
	 * noContent
	 *
	 * @return ResponseEntity<T>
	 */
	protected <T> ResponseEntity<T> noContent()
	{
		return new ResponseEntity<>( HttpStatus.NO_CONTENT );
	}
}