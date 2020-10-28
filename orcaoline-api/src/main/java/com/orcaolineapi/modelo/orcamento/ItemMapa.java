package com.orcaolineapi.modelo.orcamento;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.orcaolineapi.modelo.AbstractModel;
import com.orcaolineapi.modelo.LogicException;
import com.orcaolineapi.modelo.produto.Brick;
import com.orcaolineapi.modelo.produto.Produto;

@Entity
public class ItemMapa extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @Size(min = 0, max = 200)
	private Double quantidade;

	@NotNull
	@Enumerated(EnumType.STRING)
	private UnidadeMedida unidade;

	@JsonIgnoreProperties({"itens", "orcamentos"})
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_mapa")
	private MapaColeta mapa;

	@ManyToOne
	@JoinColumn(name = "id_brick")
	private Brick brick;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	public ItemMapa() {

	}

	public ItemMapa(Double quantidade, @NotNull UnidadeMedida unidade, @NotNull MapaColeta mapa,
			Brick brick, Produto produto) {
		this.quantidade = quantidade;
		this.unidade = unidade;
		this.mapa = mapa;
		this.brick = brick;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public UnidadeMedida getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeMedida unidade) {
		this.unidade = unidade;
	}

	public MapaColeta getMapa() {
		return mapa;
	}

	public void setMapa(MapaColeta mapa) {
		this.mapa = mapa;
	}

	public Brick getBrick() {
		return brick;
	}

	public void setBrick(Brick brick) {
		this.brick = brick;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void check() {
		if(getProduto() != null && getProduto().getId() == null) {
			setProduto(null);
		}
		if(getBrick() == null || getBrick().getId() == null) {
			throw new LogicException("É preciso informar o BRICK");
		}
	}
	
	
}