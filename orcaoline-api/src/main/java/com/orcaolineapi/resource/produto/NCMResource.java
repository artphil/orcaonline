package com.orcaolineapi.resource.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcaolineapi.modelo.produto.NCM;
import com.orcaolineapi.repository.produto.NCMRepository;
import com.orcaolineapi.resource.AbstractResource;
import com.orcaolineapi.service.produto.NCMService;

@RestController
@RequestMapping("/ncms")
public class NCMResource extends AbstractResource<NCM>{

	private @Autowired NCMRepository repository;
	
	private @Autowired NCMService service;
	
	@Override
	public NCMRepository  getRepository() {
		return this.repository;
	}

	@Override
	public NCMService getService() {
		return this.service;
	}
}