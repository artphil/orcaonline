package com.orcaolineapi.modelo.produto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.orcaolineapi.modelo.AbstractModel;

@Entity
public class NCM extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Digits(integer = 8, fraction = 0)
	@Size(min = 8, max = 8)
	private String numero;
	
	@Size(min = 0, max = 200)
	@Pattern(regexp = "^$|[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")
	private String descricao;
	
	public NCM() {
		
	}
	
	public NCM(String numero, String descricao) {
		this.numero = numero;
		this.descricao = descricao;
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
