package com.orcaolineapi.resource.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcaolineapi.modelo.produto.Segmento;
import com.orcaolineapi.repository.produto.SegmentoRepository;
import com.orcaolineapi.resource.AbstractResource;
import com.orcaolineapi.service.produto.SegmentoService;

@RestController
@RequestMapping("/segmentos")
public class SegmentoResource extends AbstractResource<Segmento> {

	private @Autowired SegmentoRepository repository;

	private @Autowired SegmentoService service;

	public SegmentoRepository getRepository() {
		return this.repository;
	}

	@Override
	public SegmentoService getService() {
		return this.service;
	}

}