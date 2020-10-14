package com.orcaolineapi.service.orcamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.orcamento.MapaColeta;
import com.orcaolineapi.modelo.orcamento.Orcamento;
import com.orcaolineapi.repository.orcamento.MapaColetaRepository;
import com.orcaolineapi.repository.orcamento.OrcamentoRepository;
import com.orcaolineapi.service.AbstractService;

@Service
public class OrcamentoService extends AbstractService<Orcamento> {

	private @Autowired OrcamentoRepository repository;
	
	private @Autowired MapaColetaRepository mapaRepository;

	@Override
	public OrcamentoRepository getRepository() {
		return repository;
	}
	
	public Orcamento create(Long idMapa) {
		MapaColeta mapa = mapaRepository.findById(idMapa).get();
		return repository.save(mapa.criaNovoOrcamento(getUsuarioLogado()));
	}
	
	public Orcamento enviar(Orcamento orcamento) {
		orcamento.enviar();
		return repository.save(orcamento);
	}
	
	public void deletar(Long idOrcamento) {
		Orcamento orcamento = repository.findById(idOrcamento).get();
		if(orcamento.isOpen()) {
			repository.delete(orcamento);
		}
	}
	
}
