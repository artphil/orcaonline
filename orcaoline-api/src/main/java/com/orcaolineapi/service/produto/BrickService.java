package com.orcaolineapi.service.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.produto.Brick;
import com.orcaolineapi.repository.AbstractRepository;
import com.orcaolineapi.repository.produto.BrickRepository;
import com.orcaolineapi.service.AbstractService;

@Service
public class BrickService extends AbstractService<Brick>{

	private @Autowired BrickRepository repository;
	
	@Override
	public AbstractRepository<Brick, Long> getRepository() {
		return this.repository;
	}
}