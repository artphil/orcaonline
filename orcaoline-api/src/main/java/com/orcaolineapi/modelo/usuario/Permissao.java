package com.orcaolineapi.modelo.usuario;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import com.orcaolineapi.modelo.AbstractModel;
import com.orcaolineapi.modelo.sistema.Modulo;

@Entity
public class Permissao extends AbstractModel{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 5, max = 100)
	@NotBlank
	private String nome;
	
	@Size(min = 5, max = 200)
	@NotBlank
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	@NonNull
	private Modulo modulo;
	
	public Permissao() {

	}

	public Permissao(String nome, String descricao) {
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
	
	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	@PrePersist @PreUpdate
	public void prepersist() {
		setNome(getNome().toUpperCase());
	}
	
}
