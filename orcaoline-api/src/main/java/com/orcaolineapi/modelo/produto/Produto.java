package com.orcaolineapi.modelo.produto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.orcaolineapi.modelo.AbstractModel;

@Entity
public class Produto extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 5, max = 150)
	@Pattern(regexp = "[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")
	@NotBlank
	private String nome;

	@Size(min = 0, max = 200)
	@Pattern(regexp = "^$|[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")
	private String descricao;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_ncm")
	private NCM ncm;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_gtin_ean")
	private GTIN_EAN gtin;
	
	public Produto() {
		
	}

	public Produto(String nome, String descricao, NCM ncm, GTIN_EAN gtin_ean) {
		this.nome = nome;
		this.descricao = descricao;
		this.ncm = ncm;
		this.gtin = gtin_ean;
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

	public NCM getNcm() {
		return ncm;
	}

	public void setNcm(NCM ncm) {
		this.ncm = ncm;
	}

	public GTIN_EAN getGtin() {
		return gtin;
	}

	public void setGtin(GTIN_EAN gtin) {
		this.gtin = gtin;
	}

}