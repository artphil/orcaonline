package com.orcaolineapi.repository.orcamento.query;

import java.util.List;

import com.orcaolineapi.modelo.orcamento.Orcamento;
import com.orcaolineapi.repository.orcamento.filter.OrcamentoFilter;

public interface OrcamentoRepositoryQuery {
	List<Orcamento> filtrar(OrcamentoFilter filtro);
}
