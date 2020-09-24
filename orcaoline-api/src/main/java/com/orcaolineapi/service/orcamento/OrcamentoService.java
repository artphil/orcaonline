package com.orcaolineapi.service.orcamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.orcamento.Orcamento;
import com.orcaolineapi.repository.orcamento.OrcamentoRepository;
import com.orcaolineapi.service.AbstractService;

@Service
public class OrcamentoService extends AbstractService<Orcamento>{

	private @Autowired OrcamentoRepository repository;
	
	@Override
	public OrcamentoRepository getRepository() {
		return repository;
	}

}
