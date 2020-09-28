package com.orcaolineapi.service.orcamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.orcamento.ItemOrcamento;
import com.orcaolineapi.repository.orcamento.ItemOrcamentoRepository;
import com.orcaolineapi.service.AbstractService;

@Service
public class ItemOrcamentoService extends AbstractService<ItemOrcamento> {

	private @Autowired ItemOrcamentoRepository repository;

	@Override
	public ItemOrcamentoRepository getRepository() {
		return repository;
	}

}
