package com.orcaolineapi.modelo.orcamento;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.orcaolineapi.modelo.AbstractModel;
import com.orcaolineapi.modelo.produto.Produto;

@Entity
public class ItemMapa extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//@Size(min = 0, max = 200)
	private Double quantidade;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_unidade_medida")
	private UnidadeMedida unidade;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_mapa_coleta")
	private MapaColeta mapa;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	
	public ItemMapa() {
		
	}
	
	public ItemMapa(Double quantidade, Produto produto, MapaColeta mapa, UnidadeMedida unidade) {
		this.quantidade = quantidade;
		this.produto = produto;
		this.mapa = mapa;
		this.unidade = unidade;
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
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public MapaColeta getMapa() {
		return mapa;
	}
	
	public void setMapa(MapaColeta mapa) {
		this.mapa = mapa;
	}
	
	public UnidadeMedida getUnidade() {
		return unidade;
	}
	
	public void setUnidade(UnidadeMedida unidade) {
		this.unidade = unidade;
	}
}