package com.orcaolineapi.repository.produto.filter;

public class ProdutoFilter {

	private Integer id;

	private String nome;

	private String descricao;

	private Integer ncm;

	private Integer segmento;

	private Integer familia;

	private Integer classe;

	private Integer brick;

	private Integer gtin;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getNcm() {
		return ncm;
	}

	public void setNcm(Integer ncm) {
		this.ncm = ncm;
	}

	public Integer getSegmento() {
		return segmento;
	}

	public void setSegmento(Integer segmento) {
		this.segmento = segmento;
	}

	public Integer getFamilia() {
		return familia;
	}

	public void setFamilia(Integer familia) {
		this.familia = familia;
	}

	public Integer getClasse() {
		return classe;
	}

	public void setClasse(Integer classe) {
		this.classe = classe;
	}

	public Integer getBrick() {
		return brick;
	}

	public void setBrick(Integer brick) {
		this.brick = brick;
	}

	public Integer getGtin() {
		return gtin;
	}

	public void setGtin(Integer gtin) {
		this.gtin = gtin;
	}

}
