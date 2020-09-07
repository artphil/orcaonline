package com.orcaolineapi.resource.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcaolineapi.modelo.produto.Classe;
import com.orcaolineapi.repository.produto.ClasseRepository;
import com.orcaolineapi.resource.AbstractResource;
import com.orcaolineapi.service.produto.ClasseService;

@RestController
@RequestMapping("/classes")
public class ClasseResource extends AbstractResource<Classe> {

	private @Autowired ClasseRepository repository;

	private @Autowired ClasseService service;

	public ClasseRepository getRepository() {
		return this.repository;
	}

	@Override
	public ClasseService getService() {
		return this.service;
	}
}