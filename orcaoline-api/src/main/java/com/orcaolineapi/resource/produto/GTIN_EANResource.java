package com.orcaolineapi.resource.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcaolineapi.modelo.produto.GTIN_EAN;
import com.orcaolineapi.repository.AbstractRepository;
import com.orcaolineapi.repository.produto.GTIN_EANRepository;
import com.orcaolineapi.resource.AbstractResource;
import com.orcaolineapi.service.AbstractService;
import com.orcaolineapi.service.produto.GTIN_EANService;

@RestController
@RequestMapping("/gtins")
public class GTIN_EANResource extends AbstractResource<GTIN_EAN>{

	private @Autowired  GTIN_EANRepository repository;
	
	private @Autowired  GTIN_EANService service;
	
	@Override
	public AbstractRepository<GTIN_EAN, Long> getRepository() {
		return this.repository;
	}

	@Override
	public AbstractService<GTIN_EAN> getService() {
		return this.service;
	}
}