package com.orcaolineapi.service.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.produto.Segmento;
import com.orcaolineapi.repository.produto.SegmentoRepository;
import com.orcaolineapi.service.AbstractService;

@Service
public class SegmentoService extends AbstractService<Segmento> {

	private @Autowired SegmentoRepository repository;

	@Override
	public SegmentoRepository getRepository() {
		return this.repository;
	}
}