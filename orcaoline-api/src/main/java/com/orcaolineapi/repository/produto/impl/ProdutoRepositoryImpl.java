package com.orcaolineapi.repository.produto.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.orcaolineapi.modelo.produto.Produto;
import com.orcaolineapi.repository.produto.filter.ProdutoFilter;
import com.orcaolineapi.repository.produto.query.ProdutoRepositoryQuery;

public class ProdutoRepositoryImpl implements ProdutoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Produto> filtrar(ProdutoFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Produto> criteria = builder.createQuery(Produto.class);
		Root<Produto> root = criteria.from(Produto.class);

		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);

		TypedQuery<Produto> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(ProdutoFilter filter, CriteriaBuilder builder, Root<Produto> root) {

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (filter.getId() != null) {
			predicates.add(builder.equal(root.get("id"), filter.getId()));
		}
		if (!StringUtils.isEmpty(filter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get("nome")), "%" + filter.getNome().toLowerCase() + "%"));
		}
		if (!StringUtils.isEmpty(filter.getDescricao())) {
			predicates.add(builder.like(builder.lower(root.get("descricao")),
					"%" + filter.getDescricao().toLowerCase() + "%"));
		}
		if (filter.getNcm() != null) {
			predicates.add(builder.equal(root.get("ncm").get("id"), filter.getNcm()));
		}
		if (filter.getGtin() != null) {
			predicates.add(builder.equal(root.get("gtin").get("id"), filter.getGtin()));
		}
		if (filter.getBrick() != null) {
			predicates.add(builder.equal(root.get("gtin").get("brick").get("id"), filter.getBrick()));
		}
		if (filter.getClasse() != null) {
			predicates.add(builder.equal(root.get("gtin").get("brick").get("classe").get("id"), filter.getClasse()));
		}
		if (filter.getFamilia() != null) {
			predicates.add(builder.equal(root.get("gtin").get("brick").get("classe").get("familia").get("id"),
					filter.getFamilia()));
		}
		if (filter.getSegmento() != null) {
			predicates.add(
					builder.equal(root.get("gtin").get("brick").get("classe").get("familia").get("segmento").get("id"),
							filter.getSegmento()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
