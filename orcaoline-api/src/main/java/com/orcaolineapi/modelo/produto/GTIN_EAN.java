package com.orcaolineapi.modelo.produto;

import java.math.BigInteger;

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
public class GTIN_EAN extends AbstractModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Pattern(regexp = "[0-9]+", message =  "Não são permitidas letras ou caracteres especiais no id do gtin_ean")
	private Long id;
	
    @Pattern(regexp = "[0-9]+", message =  "Não são permitidas letras ou caracteres especiais no nome do gtin_ean")
	@NotBlank(message = "O gtin_ean é obrigatório!")
	private BigInteger numero;

	@NotNull(message = "Brick é obrigatório!")
	@ManyToOne
	@JoinColumn(name = "id_brick")
	private Brick brick;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigInteger getNumero() {
		return numero;
	}

	public void setNumero(BigInteger numero) {
		this.numero = numero;
	}

	public Brick getBrick() {
		return brick;
	}

	public void setBrick(Brick brick) {
		this.brick = brick;
	}
}