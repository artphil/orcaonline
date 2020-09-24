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
public class Orcamento extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat
	private LocalDate dataRegistro;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario fornecedor;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_status")
	private Status status;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_mapa_coleta")
	private MapaColeta mapa;
	
	@OneToMany(mappedBy = "orcamento", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemOrcamento> itens;
	
	public Orcamento() {
		
	}
	
	public Orcamento(LocalDate dataRegistro, Usuario fornecedor, Status status, List<ItemOrcamento> itens, MapaColeta mapa) {
		this.dataRegistro = dataRegistro;
		this.fornecedor = fornecedor;
		this.status = status;
		this.itens = itens;
		this.mapa = mapa;
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
	
	public Usuario getComprador() {
		return fornecedor;
	}
	
	public void setComprador(Usuario fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public List<ItemOrcamento> getItens() {
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
	
	public static List<Status> usedStatus(){
		List<Status> list = new ArrayList<>();
		list.add(Status.ABERTO);
		list.add(Status.EM_ANDAMENTO);
		list.add(Status.FECHADO);
		return list;
	}
	
}