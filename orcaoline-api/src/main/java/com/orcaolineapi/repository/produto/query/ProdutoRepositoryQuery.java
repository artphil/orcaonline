package com.orcaolineapi.repository.produto.query;

import java.util.List;

import com.orcaolineapi.modelo.produto.Produto;
import com.orcaolineapi.repository.produto.filter.ProdutoFilter;

public interface ProdutoRepositoryQuery {
	
	public List<Produto> filtrar(ProdutoFilter filter);

}
