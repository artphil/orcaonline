package com.orcaolineapi.resource.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcaolineapi.modelo.produto.Brick;
import com.orcaolineapi.repository.AbstractRepository;
import com.orcaolineapi.repository.produto.BrickRepository;
import com.orcaolineapi.resource.AbstractResource;
import com.orcaolineapi.service.AbstractService;
import com.orcaolineapi.service.produto.BrickService;

@RestController
@RequestMapping("/bricks")
public class BrickResource extends AbstractResource<Brick>{

	private @Autowired BrickRepository repository;
	
	private @Autowired BrickService service;
	
	@Override
	public AbstractRepository<Brick, Long> getRepository() {
		return this.repository;
	}

	@Override
	public AbstractService<Brick> getService() {
		return this.service;
	}
}