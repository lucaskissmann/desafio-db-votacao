/**
 * Filename:    CpfOrCnpjValidator.java
 *
 * Description: Implementation of the CpfOrCnpjValidator class.
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

package com.db.desafio.votacao.v1.helpers.validation;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfOrCnpjValidator
    implements
        ConstraintValidator<CpfOrCnpj, String> 
{
    /**
     * isValid
     * 
     * @param value String
     * @param context ConstraintValidatorContext
     * @return boolean
     */
	@Override
	public boolean isValid( String value, ConstraintValidatorContext context ) 
    {
        /**
         * if value is null, will pass to @IsNotEmpty
         */
        if( StringUtils.isEmpty( value ))
        {
            return true;
        }

        return isCpf( value ) 
            ? validateCpf( value, context )
            : validateCnpj( value, context );
	}

    /**
     * isCpf
     * 
     * @param value String
     * @return boolean
     */
    public boolean isCpf( String value )
    {
        return value.length() == 11;
    }

    /**
     * validateCpf
     * 
     * @param value String
     * @param context ConstraintValidatorContext
     * @return boolean
     */
    public boolean validateCpf( String value, ConstraintValidatorContext context )
    {
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize( null );
    
        return cpfValidator.isValid( value, context );
    }

    /**
     * validateCnpj
     * 
     * @param value String
     * @param context ConstraintValidatorContext
     * @return boolean
     */
    public boolean validateCnpj( String value, ConstraintValidatorContext context )
    {
        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize( null );

        return cnpjValidator.isValid( value, context );
    }
}