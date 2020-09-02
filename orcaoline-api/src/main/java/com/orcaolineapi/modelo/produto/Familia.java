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
public class Familia extends AbstractModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Pattern(regexp = "[0-9]+", message =  "Não são permitidas letras ou caracteres especiais no nome do familia")
	private Long id;

	@Length(max=150, message="Tamanho excedido para nome da familia")
    @Pattern(regexp = "[a-zA-Z]+ [a-zA-Z]+", message =  "Não são permitidos números ou caracteres especiais no nome da familia")
	@NotBlank(message = "O nome da família é obrigatório!")
	private String nome;
	
	@Length(max=200, message="Tamanho excedido para descricao da familia")
    @Pattern(regexp = "[a-zA-Z]+ [a-zA-Z]+", message =  "Não são permitidos números ou caracteres especiais no nome da familia")
	private String descricao;
	
	@NotNull(message = "Segmento é obrigatório!")
	@ManyToOne
	@JoinColumn(name = "id_segmento")
	private Segmento segmento;

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

	public Segmento getSegmento() {
		return segmento;
	}

	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
	}
}