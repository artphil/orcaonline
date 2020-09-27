package com.orcaolineapi.modelo.sistema;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public enum Modulo {

	ORCAMENTO("Orçamento"), PRODUTO("Produto"), USUARIO("Usuário");

	@Size(min = 0, max = 200)
	@Pattern(regexp = "[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")
	private String descricao;

	Modulo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
