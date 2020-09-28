package com.orcaolineapi.resource.orcamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcaolineapi.modelo.orcamento.Status;
import com.orcaolineapi.repository.orcamento.StatusRepository;
import com.orcaolineapi.resource.AbstractResource;
import com.orcaolineapi.service.orcamento.StatusService;

@RestController
@RequestMapping("/status")
public class StatusResource extends AbstractResource<Status> {

	private @Autowired StatusRepository repository;

	private @Autowired StatusService service;

	@Override
	public StatusRepository getRepository() {
		return repository;
	}

	@Override
	public StatusService getService() {
		return service;
	}
}
