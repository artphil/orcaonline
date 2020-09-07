package com.orcaolineapi.service.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.produto.NCM;
import com.orcaolineapi.repository.produto.NCMRepository;
import com.orcaolineapi.service.AbstractService;

@Service
public class NCMService extends AbstractService<NCM> {

	private @Autowired NCMRepository repository;

	@Override
	public NCMRepository getRepository() {
		return this.repository;
	}
}