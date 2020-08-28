package com.orcaolineapi.service.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.produto.Produto;
import com.orcaolineapi.repository.produto.ProdutoRepository;
import com.orcaolineapi.service.AbstractService;

@Service
public class ProdutoService extends AbstractService<Produto>{

	private @Autowired ProdutoRepository repository;
	
	@Override
	public ProdutoRepository  getRepository() {
		return this.repository;
	}
}