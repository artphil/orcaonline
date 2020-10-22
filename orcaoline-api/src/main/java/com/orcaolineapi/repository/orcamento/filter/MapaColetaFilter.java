package com.orcaolineapi.repository.orcamento.filter;

import java.time.LocalDate;

public class MapaColetaFilter {
	
	private Long idStatus;
	
	private Long idUsuario;

	private LocalDate dataRegistroInicial;
	
	private LocalDate dataRegistroFinal;
	
	private LocalDate dataEncerramentoInicial;
	
	private LocalDate dataEncerramentoFinal;
	
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

	public LocalDate getDataEncerramentoInicial() {
		return dataEncerramentoInicial;
	}

	public void setDataEncerramentoInicial(LocalDate dataEncerramentoInicial) {
		this.dataEncerramentoInicial = dataEncerramentoInicial;
	}

	public LocalDate getDataEncerramentoFinal() {
		return dataEncerramentoFinal;
	}

	public void setDataEncerramentoFinal(LocalDate dataEncerramentoFinal) {
		this.dataEncerramentoFinal = dataEncerramentoFinal;
	}
}
