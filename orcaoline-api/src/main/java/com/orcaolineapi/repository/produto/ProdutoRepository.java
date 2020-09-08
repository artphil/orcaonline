package com.orcaolineapi.repository.produto;

import com.orcaolineapi.modelo.produto.Produto;
import com.orcaolineapi.repository.AbstractRepository;
import com.orcaolineapi.repository.produto.query.ProdutoRepositoryQuery;

public interface ProdutoRepository extends AbstractRepository<Produto, Long>, ProdutoRepositoryQuery {

}
