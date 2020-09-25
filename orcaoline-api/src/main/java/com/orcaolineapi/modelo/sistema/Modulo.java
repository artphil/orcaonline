package com.orcaolineapi.modelo.sistema;

public enum Modulo {

	ORCAMENTO("Orçamento"),
	PRODUTO("Produto"),
	USUARIO("Usuário");
	
	private String descricao;
	
	Modulo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
