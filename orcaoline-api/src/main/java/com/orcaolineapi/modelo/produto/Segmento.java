package com.orcaolineapi.modelo.produto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.orcaolineapi.modelo.AbstractModel;

@Entity
public class Segmento extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Pattern(regexp = "[0-9]+", message =  "Não são permitidas letras ou caracteres especiais no nome do segmento")
	private Long id;

    @Length(max=150, message="Tamanho excedido para nome do segmento")
    @Pattern(regexp = "[a-zA-Z]+ [a-zA-Z]+", message =  "Não são permitidos números ou caracteres especiais no nome do segmento")
	@NotBlank(message = "Nome do segmento é obrigatório")
	private String nome;
	
    @Length(max=200, message="Tamanho excedido para descricao do segmento")
    @Pattern(regexp = "[a-zA-Z]+ [a-zA-Z]+", message =  "Não são permitidos números ou caracteres especiais no nome do segmento")
	private String descricao;
	
	@OneToMany(mappedBy = "segmento", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Familia> familias;
	

	public Segmento(Long id, String nome, String descricao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;	
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
}