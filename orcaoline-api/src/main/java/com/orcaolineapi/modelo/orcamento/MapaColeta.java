package com.orcaolineapi.modelo.orcamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.orcaolineapi.modelo.AbstractModel;
import com.orcaolineapi.modelo.usuario.Usuario;

@Entity
public class MapaColeta extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat
	private LocalDate dataRegistro;

	private String descricao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_comprador")
	private Usuario comprador;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_status")
	private Status status;

	@OneToMany(mappedBy = "mapa", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemMapa> itens;

	@OneToMany(mappedBy = "mapa", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Orcamento> orcamentos;

	public MapaColeta() {
		this.dataRegistro = LocalDate.now();
		this.status = Status.ABERTO;
	}

	public MapaColeta(LocalDate dataRegistro, Usuario comprador, Status status, List<ItemMapa> itens,
			List<Orcamento> Orcamento) {
		this.dataRegistro = dataRegistro;
		this.comprador = comprador;
		this.status = status;
		this.itens = itens;
		this.orcamentos = Orcamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDate dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public void setComprador(Usuario comprador) {
		this.comprador = comprador;
	}

	public List<ItemMapa> getItens() {
		if(this.itens == null) {
			this.itens = new ArrayList<>();
		}
		return itens;
	}

	public void setItens(List<ItemMapa> itens) {
		this.itens = itens;
	}

	public List<Orcamento> getOrcamentos() {
		if(this.orcamentos == null) {
			this.orcamentos = new ArrayList<>();
		}
		return orcamentos;
	}

	public void setOrcamentos(List<Orcamento> orcamentos) {
		this.orcamentos = orcamentos;
	}

	public static List<Status> usedStatus() {
		List<Status> list = new ArrayList<>();
		list.add(Status.ABERTO);
		list.add(Status.EM_ANDAMENTO);
		list.add(Status.FECHADO);
		return list;
	}
}