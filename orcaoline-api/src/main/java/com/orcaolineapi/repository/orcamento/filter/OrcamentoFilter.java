package com.orcaolineapi.repository.orcamento.filter;

import java.time.LocalDate;

public class OrcamentoFilter {
	
	private Long idStatus;
	
	private Long idUsuario;

	private LocalDate dataRegistroInicial;
	
	private LocalDate dataRegistroFinal;
	
	private LocalDate dataEnvioInicial;
	
	private LocalDate dataEnvioFinal;
	
	private Boolean aprovado;

	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public LocalDate getDataRegistroInicial() {
		return dataRegistroInicial;
	}

	public void setDataRegistroInicial(LocalDate dataRegistroInicial) {
		this.dataRegistroInicial = dataRegistroInicial;
	}

	public LocalDate getDataRegistroFinal() {
		return dataRegistroFinal;
	}

	public void setDataRegistroFinal(LocalDate dataRegistroFinal) {
		this.dataRegistroFinal = dataRegistroFinal;
	}

	public LocalDate getDataEnvioInicial() {
		return dataEnvioInicial;
	}

	public void setDataEnvioInicial(LocalDate dataEnvioInicial) {
		this.dataEnvioInicial = dataEnvioInicial;
	}

	public LocalDate getDataEnvioFinal() {
		return dataEnvioFinal;
	}

	public void setDataEnvioFinal(LocalDate dataEnvioFinal) {
		this.dataEnvioFinal = dataEnvioFinal;
	}

	public Boolean getAprovado() {
		return aprovado;
	}

	public void setAprovado(Boolean aprovado) {
		this.aprovado = aprovado;
	}
}
