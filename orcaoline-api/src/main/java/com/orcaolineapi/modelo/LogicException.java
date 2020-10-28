package com.orcaolineapi.modelo;

public class LogicException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LogicException() {
		super();
	}
	
	public LogicException(String mensagem) {
		super(mensagem);
	}
	
}
