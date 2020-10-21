package com.orcaolineapi.repository.orcamento.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.orcaolineapi.modelo.orcamento.Orcamento;
import com.orcaolineapi.repository.orcamento.filter.OrcamentoFilter;
import com.orcaolineapi.repository.orcamento.query.OrcamentoRepositoryQuery;

public class OrcamentoRepositoryImpl implements OrcamentoRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Orcamento> filtrar(OrcamentoFilter filtro) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Orcamento> criteria = builder.createQuery(Orcamento.class);
		Root<Orcamento> root = criteria.from(Orcamento.class);
		
		Predicate[] predicates = criarRestricoes(filtro, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Orcamento> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	
	private Predicate[] criarRestricoes(OrcamentoFilter filter, CriteriaBuilder builder, Root<Orcamento> root) {
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(filter.getIdStatus() != null) {
			predicates.add(builder.equal(root.get("status").get("id"), filter.getIdStatus()));
		}
		
		if(filter.getIdUsuario() != null) {
			predicates.add(builder.equal(root.get("fornecedor").get("id"), filter.getIdUsuario()));
		}
		
		if(filter.getDataRegistroInicial() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataRegistro"), filter.getDataRegistroInicial()));
		}
		
		if(filter.getDataRegistroFinal() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataRegistro"), filter.getDataRegistroFinal()));
		}
		
		if(filter.getDataEnvioInicial() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataEnvio"), filter.getDataEnvioInicial()));
		}
		
		if(filter.getDataEnvioFinal() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataEnvio"), filter.getDataEnvioFinal()));
		}
		
		if(filter.getAprovado()) {
			predicates.add(builder.equal(root.get("aprovado"), true));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	

}
