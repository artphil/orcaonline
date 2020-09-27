package com.orcaolineapi.resource.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcaolineapi.modelo.produto.Produto;
import com.orcaolineapi.repository.produto.ProdutoRepository;
import com.orcaolineapi.repository.produto.filter.ProdutoFilter;
import com.orcaolineapi.resource.AbstractResource;
import com.orcaolineapi.service.produto.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource extends AbstractResource<Produto> {

	private @Autowired ProdutoRepository repository;

	private @Autowired ProdutoService service;

	public ProdutoRepository getRepository() {
		return this.repository;
	}

	@Override
	public ProdutoService getService() {
		return this.service;
	}

	@CrossOrigin
	@GetMapping("pesquisar")
	public List<Produto> pesquisar(ProdutoFilter filter) {
		return repository.filtrar(filter);
	}

}