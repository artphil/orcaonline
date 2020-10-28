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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.orcaolineapi.modelo.AbstractModel;
import com.orcaolineapi.modelo.LogicException;
import com.orcaolineapi.modelo.usuario.Usuario;

@Entity
public class Orcamento extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat
	private LocalDate dataRegistro;

	private LocalDate dataEnvio;
	
	private Boolean aprovado;

	@JsonIgnoreProperties("tipoUsuario")
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_fornecedor")
	private Usuario fornecedor;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_status")
	private Status status;

	@JsonIgnoreProperties({"comprador", "orcamentos"})
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_mapa")
	private MapaColeta mapa;

	@JsonIgnoreProperties("orcamento")
	@OneToMany(mappedBy = "orcamento", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemOrcamento> itens;

	public Orcamento() {
		this.dataRegistro = LocalDate.now();
		this.status = Status.ABERTO;
	}
	
	public Orcamento(Usuario fornecedor) {
		this();
		this.fornecedor = fornecedor;
	}

	public Orcamento(LocalDate dataRegistro, LocalDate dataEnvio, Usuario fornecedor, Status status, MapaColeta mapa, Boolean aprovado) {
		this.dataRegistro = dataRegistro;
		this.dataEnvio = dataEnvio;
		this.fornecedor = fornecedor;
		this.status = status;
		this.mapa = mapa;
		this.aprovado = aprovado;
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
	
	public LocalDate getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(LocalDate dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Boolean getAprovado() {
		return aprovado;
	}

	public void setAprovado(Boolean aprovado) {
		this.aprovado = aprovado;
	}

	public Usuario getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Usuario fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<ItemOrcamento> getItens() {
		if(this.itens == null) {
			this.itens = new ArrayList<>();
		}
		return itens;
	}

	public void setItens(List<ItemOrcamento> itens) {
		this.itens = itens;
	}

	public MapaColeta getMapa() {
		return mapa;
	}

	public void setMapa(MapaColeta mapa) {
		this.mapa = mapa;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isOpen() {
		return getStatus().equals(Status.ABERTO);
	}
	
	public boolean isRunning() {
		return getStatus().equals(Status.EM_ANDAMENTO);
	}
	
	public boolean isClosed() {
		return getStatus().equals(Status.FECHADO);
	}
	
	public static List<Status> usedStatus() {
		List<Status> list = new ArrayList<>();
		list.add(Status.ABERTO);
		list.add(Status.EM_ANDAMENTO);
		list.add(Status.FECHADO);
		return list;
	}
	
	public void enviar() {
		if(isOpen()) {
			checkItems();
			setStatus(Status.EM_ANDAMENTO);
			setDataEnvio(LocalDate.now());
		}
		else {
			throw new LogicException("O orçamento não está aberto!");
		}
	}

	private void checkItems() {
		for(ItemOrcamento i : getItens()) {
			i.checkValores();
		}
	}
}