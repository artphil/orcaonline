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
import com.orcaolineapi.modelo.usuario.Usuario;

@Entity
public class MapaColeta extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat
	private LocalDate dataRegistro;

	private LocalDate dataEncerramento;
	
	private String descricao;
	
	
	@JsonIgnoreProperties("tipoUsuario")
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_comprador")
	private Usuario comprador;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_status")
	private Status status;

	@JsonIgnoreProperties("mapa")
	@OneToMany(mappedBy = "mapa", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemMapa> itens;

	@JsonIgnoreProperties("mapa")
	@OneToMany(mappedBy = "mapa", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Orcamento> orcamentos;

	public MapaColeta() {
		this.dataRegistro = LocalDate.now();
		this.status = Status.ABERTO;
	}

	public MapaColeta(LocalDate dataRegistro, Usuario comprador, Status status) {
		this.dataRegistro = dataRegistro;
		this.comprador = comprador;
		this.status = status;
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

	public LocalDate getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(LocalDate dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
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
	
	public Boolean isRunning() {
		return getStatus().equals(Status.EM_ANDAMENTO);
	}
	
	public static List<Status> usedStatus() {
		List<Status> list = new ArrayList<>();
		list.add(Status.ABERTO);
		list.add(Status.EM_ANDAMENTO);
		list.add(Status.FECHADO);
		return list;
	}
	
	public Orcamento criaNovoOrcamento() {
		if(isRunning()) {
			Orcamento orcamento = new Orcamento();
			orcamento.setMapa(this);
			addItemOrcamento(orcamento);
			return orcamento;
		}
		return null;
	}
	
	private void addItemOrcamento(Orcamento orcamento) {
		for(ItemMapa item : getItens()) {
			orcamento.getItens().add(new ItemOrcamento(orcamento, item));
		}
	}
	
	public void encerrar() {
		setDataEncerramento(LocalDate.now());
		setStatus(Status.FECHADO);
	}
	
	public void aprovarOrcamento(Long idOrcamento) {
		for(Orcamento o : getOrcamentos()) {
			if(o.getId() == idOrcamento) {
				o.setAprovado(true);
				setDataEncerramento(LocalDate.now());
				setStatus(Status.FECHADO);
			}
		}
	}
	
}