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
public class ItemOrcamento extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double valorUnitario;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_orcamento")
	private Orcamento orcamento;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_item_mapa")
	private ItemMapa itemMapa;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	public ItemOrcamento() {

	}

	public ItemOrcamento(Double valorUnitario, Orcamento orcamento, ItemMapa itemMapa, Produto produto) {
		this.valorUnitario = valorUnitario;
		this.orcamento = orcamento;
		this.itemMapa = itemMapa;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	public ItemMapa getItemMapa() {
		return itemMapa;
	}

	public void setItemMapa(ItemMapa itemMapa) {
		this.itemMapa = itemMapa;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}