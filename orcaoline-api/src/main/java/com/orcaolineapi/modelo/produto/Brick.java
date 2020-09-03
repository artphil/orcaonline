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
public class Brick extends AbstractModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Length(max=150, message="Tamanho excedido para nome do brick")
    @Pattern(regexp = "[a-zA-Z]+[a-zA-Z]+", message =  "Não são permitidos números ou caracteres especiais no nome do brick")
	@NotBlank
	private String nome;
	
	@Length(max=200, message="Tamanho excedido para descricao do brick")
    @Pattern(regexp = "[a-zA-Z]+[a-zA-Z]+", message =  "Não são permitidos números ou caracteres especiais no nome do brick")
	private String descricao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_classe")
	private Classe classe;

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

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}
}