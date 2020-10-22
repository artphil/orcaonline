package com.orcaolineapi.repository.orcamento;

import com.orcaolineapi.modelo.orcamento.MapaColeta;
import com.orcaolineapi.repository.AbstractRepository;
import com.orcaolineapi.repository.orcamento.query.MapaColetaRepositoryQuery;

public interface MapaColetaRepository extends AbstractRepository<MapaColeta, Long>, MapaColetaRepositoryQuery {

}
