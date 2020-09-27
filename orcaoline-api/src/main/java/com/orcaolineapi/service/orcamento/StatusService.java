package com.orcaolineapi.service.orcamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.orcamento.Status;
import com.orcaolineapi.repository.orcamento.StatusRepository;
import com.orcaolineapi.service.AbstractService;

@Service
public class StatusService extends AbstractService<Status> {

	private @Autowired StatusRepository repository;

	@Override
	public StatusRepository getRepository() {
		return repository;
	}

}
