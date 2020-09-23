package com.orcaolineapi.service.orcamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.orcamento.MapaColeta;
import com.orcaolineapi.repository.orcamento.MapaColetaRepository;
import com.orcaolineapi.service.AbstractService;

@Service
public class MapaColetaService extends AbstractService<MapaColeta>{

	private @Autowired MapaColetaRepository repository;
	
	@Override
	public MapaColetaRepository getRepository() {
		return repository;
	}

}
