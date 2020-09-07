package com.orcaolineapi.resource.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcaolineapi.modelo.produto.Familia;
import com.orcaolineapi.repository.produto.FamiliaRepository;
import com.orcaolineapi.resource.AbstractResource;
import com.orcaolineapi.service.produto.FamiliaService;

@RestController
@RequestMapping("/familias")
public class FamiliaResource extends AbstractResource<Familia> {

	private @Autowired FamiliaRepository repository;

	private @Autowired FamiliaService sevice;

	public FamiliaRepository getRepository() {
		return this.repository;
	}

	public FamiliaService getService() {
		return this.sevice;
	}
}