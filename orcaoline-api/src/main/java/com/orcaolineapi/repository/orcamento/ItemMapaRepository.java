package com.orcaolineapi.repository.orcamento;

import org.springframework.stereotype.Repository;

import com.orcaolineapi.modelo.orcamento.ItemMapa;
import com.orcaolineapi.repository.AbstractRepository;

@Repository
public interface ItemMapaRepository extends AbstractRepository<ItemMapa, Long> {
}
