package com.orcaolineapi.resource.usuario;

import com.orcaolineapi.modelo.sistema.Modulo;

public class FilterTipoUsuarioPermissao {

	private Long idTipoUsuario;
	
	private Long idPermissao;
	
	private Modulo modulo;

	public Long getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(Long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public Long getIdPermissao() {
		return idPermissao;
	}

	public void setIdPermissao(Long idPermissao) {
		this.idPermissao = idPermissao;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
}
