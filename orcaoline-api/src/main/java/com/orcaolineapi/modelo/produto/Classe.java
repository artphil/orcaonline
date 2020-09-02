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
public class Classe extends AbstractModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Pattern(regexp = "[0-9]+", message =  "Não são permitidas letras ou caracteres especiais no nome da classe")
	private Long id;
	
	@Length(max=150, message="Tamanho excedido para nome da classe")
    @Pattern(regexp = "[a-zA-Z]+ [a-zA-Z]+", message =  "Não são permitidos números ou caracteres especiais no nome da classe")
	@NotBlank(message = "O nome da classe é obrigatório!")
	private String nome;
	
	@Length(max=200, message="Tamanho excedido para descricao da classe")
    @Pattern(regexp = "[a-zA-Z]+ [a-zA-Z]+", message =  "Não são permitidos números ou caracteres especiais no nome da classe")
	private String descricao;
	
	@NotNull(message = "Família é obrigatório!")
	@ManyToOne
	@JoinColumn(name = "id_familia")
	private Familia familia;

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

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}
}