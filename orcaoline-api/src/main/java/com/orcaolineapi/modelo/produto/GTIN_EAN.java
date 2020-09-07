package com.orcaolineapi.modelo.produto;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.orcaolineapi.modelo.AbstractModel;

@Entity
public class GTIN_EAN extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Digits(integer = 150, fraction = 0)
	@NotNull
	private BigInteger numero;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_brick")
	private Brick brick;
	
	public GTIN_EAN() {
		
	}

	public GTIN_EAN(BigInteger numero, Brick brick) {
		this.numero = numero;
		this.brick = brick;
	}

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