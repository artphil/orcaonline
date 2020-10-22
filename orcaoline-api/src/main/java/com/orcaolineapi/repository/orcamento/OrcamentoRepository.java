package com.orcaolineapi.repository.orcamento;

import com.orcaolineapi.modelo.orcamento.Orcamento;
import com.orcaolineapi.repository.AbstractRepository;
import com.orcaolineapi.repository.orcamento.query.OrcamentoRepositoryQuery;

public interface OrcamentoRepository extends AbstractRepository<Orcamento, Long>, OrcamentoRepositoryQuery {

}
