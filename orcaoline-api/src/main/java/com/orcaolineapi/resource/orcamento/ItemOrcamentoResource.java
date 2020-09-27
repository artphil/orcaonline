package com.orcaolineapi.resource.orcamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcaolineapi.modelo.orcamento.ItemOrcamento;
import com.orcaolineapi.repository.orcamento.ItemOrcamentoRepository;
import com.orcaolineapi.resource.AbstractResource;
import com.orcaolineapi.service.orcamento.ItemOrcamentoService;

@RestController
@RequestMapping("/itemorcamentos")
public class ItemOrcamentoResource extends AbstractResource<ItemOrcamento> {

	private @Autowired ItemOrcamentoRepository repository;

	private @Autowired ItemOrcamentoService service;

	@Override
	public ItemOrcamentoRepository getRepository() {
		return repository;
	}

	@Override
	public ItemOrcamentoService getService() {
		return service;
	}
}
