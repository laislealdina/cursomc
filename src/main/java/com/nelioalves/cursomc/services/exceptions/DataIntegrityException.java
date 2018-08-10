package com.nelioalves.cursomc.services.exceptions;

import org.h2.index.RangeIndex;

public class DataIntegrityException extends RuntimeException {

	public DataIntegrityException(String msg) {
		super(msg);
	}
	
	
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
