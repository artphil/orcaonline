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

import org.hibernate.validator.constraints.Length;

import com.orcaolineapi.modelo.AbstractModel;

@Entity
public class Produto extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Pattern(regexp = "[0-9]+", message =  "Não são permitidas letras ou caracteres especiais no nome do produto")
	private Long id;

	@Length(max=150, message="Tamanho excedido para descricao do produto")
    @Pattern(regexp = "[a-zA-Z]+ [a-zA-Z]+", message =  "Não são permitidos números ou caracteres especiais no nome do produto")
	@NotBlank(message = "O nome do Produto é obrigatório!")
	private String nome;

	@Length(max=200, message="Tamanho excedido para descricao do produto")
    @Pattern(regexp = "[a-zA-Z]+ [a-zA-Z]+", message =  "Não são permitidos números ou caracteres especiais no nome do produto")
	private String descricao;
	
	@Length(max=8, message="Tamanho excedido para ncm do produto")
    @Pattern(regexp = "[a-zA-Z]+ [a-zA-Z]+", message =  "Não são permitidos números ou caracteres especiais no nome do produto")
	private String ncm;
	
	@NotNull(message = "Gtin_ean é obrigatório!")
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