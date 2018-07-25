package com.nelioalves.cursomc.services.exceptions;

import org.h2.index.RangeIndex;

public class ObjectNotFoundException extends RuntimeException {

	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
