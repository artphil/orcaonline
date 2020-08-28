package com.orcaolineapi.service.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.produto.GTIN_EAN;
import com.orcaolineapi.repository.AbstractRepository;
import com.orcaolineapi.repository.produto.GTIN_EANRepository;
import com.orcaolineapi.service.AbstractService;

@Service
public class GTIN_EANService extends AbstractService<GTIN_EAN>{

	private @Autowired GTIN_EANRepository repository;

	@Override
	public AbstractRepository<GTIN_EAN, Long> getRepository() {
		return this.repository;
	}
}