package com.orcaolineapi.repository.orcamento.query;

import java.util.List;

import com.orcaolineapi.modelo.orcamento.MapaColeta;
import com.orcaolineapi.repository.orcamento.filter.MapaColetaFilter;

public interface MapaColetaRepositoryQuery {
	List<MapaColeta> filtrar(MapaColetaFilter filtro);
}
