package com.orcaolineapi.resource.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcaolineapi.modelo.produto.Produto;
import com.orcaolineapi.repository.produto.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	private @Autowired ProdutoRepository repository;

	@GetMapping
	public List<Produto> getProdutos() {
		return repository.findAll();
	}

}
