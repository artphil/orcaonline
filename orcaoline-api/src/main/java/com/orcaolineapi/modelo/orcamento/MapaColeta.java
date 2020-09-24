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
	private List<Orcamento> orcamento;
	
	public MapaColeta() {
		
	}
	
	public MapaColeta(LocalDate dataRegistro, Usuario comprador, Status status, List<ItemMapa> itens, List<Orcamento> Orcamento) {
		this.dataRegistro = dataRegistro;
		this.comprador = comprador;
		this.status = status;
		this.itens = itens;
		this.orcamento = Orcamento;
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
		return comprador;
	}
	
	public void setComprador(Usuario comprador) {
		this.comprador = comprador;
	}
	
	public List<ItemMapa> getItens() {
		return itens;
	}
	
	public void setItens(List<ItemMapa> itens) {
		this.itens = itens;
	}
	
	public List<Orcamento> getOrcamento() {
		return orcamento;
	}
	
	public void setOrcamento(List<Orcamento> orcamento) {
		this.orcamento = orcamento;
	}
	
	public static List<Status> usedStatus(){
		List<Status> list = new ArrayList<>();
		list.add(Status.ABERTO);
		list.add(Status.EM_ANDAMENTO);
		list.add(Status.FECHADO);
		return list;
	}
}