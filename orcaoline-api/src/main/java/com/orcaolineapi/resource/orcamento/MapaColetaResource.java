package com.orcaolineapi.resource.orcamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcaolineapi.modelo.orcamento.MapaColeta;
import com.orcaolineapi.repository.orcamento.MapaColetaRepository;
import com.orcaolineapi.resource.AbstractResource;
import com.orcaolineapi.service.orcamento.MapaColetaService;

@RestController
@RequestMapping("/mapas")
public class MapaColetaResource extends AbstractResource<MapaColeta> {

	private @Autowired MapaColetaRepository repository;

	private @Autowired MapaColetaService service;

	@Override
	public MapaColetaRepository getRepository() {
		return repository;
	}

	@Override
	public MapaColetaService getService() {
		return service;
	}

}
