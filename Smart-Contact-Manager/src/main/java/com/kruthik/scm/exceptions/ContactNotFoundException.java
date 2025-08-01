package com.kruthik.scm.exceptions;

public class ContactNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ContactNotFoundException(String msg) {
		super(msg);
	}

}
