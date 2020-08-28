package com.orcaolineapi.service.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.produto.Familia;
import com.orcaolineapi.repository.produto.FamiliaRepository;
import com.orcaolineapi.service.AbstractService;

@Service
public class FamiliaService extends AbstractService<Familia>{

	private @Autowired FamiliaRepository repository;
	
	@Override
	public FamiliaRepository getRepository() {
		return this.repository;
	}
}