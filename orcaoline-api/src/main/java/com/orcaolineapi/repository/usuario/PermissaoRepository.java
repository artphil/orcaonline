package com.orcaolineapi.repository.usuario;

import java.util.List;

import com.orcaolineapi.modelo.sistema.Modulo;
import com.orcaolineapi.modelo.usuario.Permissao;
import com.orcaolineapi.repository.AbstractRepository;

public interface PermissaoRepository extends AbstractRepository<Permissao, Long> {
	public List<Permissao> findByModulo(Modulo modulo);
}
