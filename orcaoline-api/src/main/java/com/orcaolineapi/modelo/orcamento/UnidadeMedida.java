package com.orcaolineapi.modelo.orcamento;

public enum UnidadeMedida {

	M2("Metro quadrado", "m²"),
	M3("Metro cúbico", "m³"),
	UN("Unidade", "unidade"),
	L("Litro", "l"),
	ML("Mililitro", "ml"),
	T("Tonelada", "t"),
	KILO("Kilograma", "kg"),
	GRAMA("Grama", "g"),
	MILIGRAMA("Miligrama", "mg"),
	PECA("Peça", "peça");
	
	private String descricao;
	private String simbolo;
	
	UnidadeMedida(String descricao, String simbolo){
		this.descricao = descricao;
		this.simbolo = simbolo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
	
}
