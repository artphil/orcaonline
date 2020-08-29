package com.orcaolineapi.modelo.produto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.orcaolineapi.modelo.AbstractModel;

@Entity
public class Produto extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String descricao;
	
	private String ncm;
	
	@ManyToOne
	@JoinColumn(name = "id_gtin_ean")
	private GTIN_EAN gtin;

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
	
	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	public GTIN_EAN getGtin() {
		return gtin;
	}

	public void setGtin(GTIN_EAN gtin) {
		this.gtin = gtin;
	}
	
}