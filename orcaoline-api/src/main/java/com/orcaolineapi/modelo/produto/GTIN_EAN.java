package com.orcaolineapi.modelo.produto;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.orcaolineapi.modelo.AbstractModel;

@Entity
public class GTIN_EAN extends AbstractModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private BigInteger numero;

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