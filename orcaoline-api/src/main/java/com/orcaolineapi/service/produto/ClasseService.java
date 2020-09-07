package com.orcaolineapi.service.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.produto.Classe;
import com.orcaolineapi.repository.produto.ClasseRepository;
import com.orcaolineapi.service.AbstractService;

@Service
public class ClasseService extends AbstractService<Classe> {

	private @Autowired ClasseRepository repository;

	@Override
	public ClasseRepository getRepository() {
		return this.repository;
	}
}