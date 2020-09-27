package com.orcaolineapi.resource.orcamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcaolineapi.modelo.orcamento.ItemMapa;
import com.orcaolineapi.repository.orcamento.ItemMapaRepository;
import com.orcaolineapi.resource.AbstractResource;
import com.orcaolineapi.service.orcamento.ItemMapaService;

@RestController
@RequestMapping("/itemmapas")
public class ItemMapaResouce extends AbstractResource<ItemMapa> {

	private @Autowired ItemMapaRepository repository;

	private @Autowired ItemMapaService service;

	@Override
	public ItemMapaRepository getRepository() {
		return repository;
	}

	@Override
	public ItemMapaService getService() {
		return service;
	}

}
