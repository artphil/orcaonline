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

import com.orcaolineapi.modelo.orcamento.MapaColeta;
import com.orcaolineapi.repository.orcamento.filter.MapaColetaFilter;
import com.orcaolineapi.repository.orcamento.query.MapaColetaRepositoryQuery;

public class MapaColetaRepositoryImpl implements MapaColetaRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<MapaColeta> filtrar(MapaColetaFilter filtro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<MapaColeta> criteria = builder.createQuery(MapaColeta.class);
		Root<MapaColeta> root = criteria.from(MapaColeta.class);
		
		Predicate[] predicates = criarRestricoes(filtro, builder, root);
		criteria.where(predicates);
		
		TypedQuery<MapaColeta> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	
	private Predicate[] criarRestricoes(MapaColetaFilter filter, CriteriaBuilder builder, Root<MapaColeta> root) {
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(filter.getIdStatus() != null) {
			predicates.add(builder.equal(root.get("status").get("id"), filter.getIdStatus()));
		}
		
		if(filter.getIdUsuario() != null) {
			predicates.add(builder.equal(root.get("comprador").get("id"), filter.getIdUsuario()));
		}
		
		if(filter.getDataRegistroInicial() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataRegistro"), filter.getDataRegistroInicial()));
		}
		
		if(filter.getDataRegistroFinal() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataRegistro"), filter.getDataRegistroFinal()));
		}
		
		if(filter.getDataEncerramentoInicial() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataEncerramento"), filter.getDataEncerramentoInicial()));
		}
		
		if(filter.getDataEncerramentoFinal() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataEncerramento"), filter.getDataEncerramentoFinal()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	

}
