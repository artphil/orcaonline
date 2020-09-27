package com.orcaolineapi.modelo.usuario;

import com.orcaolineapi.modelo.sistema.Modulo;

public class PermisaoTipoUsuarioEdicao {

	private Long id;

	private String nome;

	private String descricao;

	private Modulo modulo;

	private boolean vinculado;

	public PermisaoTipoUsuarioEdicao(Long long1, String nome, String descricao, Modulo modulo, boolean vinculado) {
		this.id = long1;
		this.nome = nome;
		this.descricao = descricao;
		this.modulo = modulo;
		this.vinculado = vinculado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public boolean isVinculado() {
		return vinculado;
	}

	public void setVinculado(boolean vinculado) {
		this.vinculado = vinculado;
	}

}
