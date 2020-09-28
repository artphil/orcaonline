package com.orcaolineapi.service.orcamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.orcamento.ItemMapa;
import com.orcaolineapi.repository.orcamento.ItemMapaRepository;
import com.orcaolineapi.service.AbstractService;

@Service
public class ItemMapaService extends AbstractService<ItemMapa> {

	private @Autowired ItemMapaRepository repository;

	@Override
	public ItemMapaRepository getRepository() {
		return repository;
	}

}
