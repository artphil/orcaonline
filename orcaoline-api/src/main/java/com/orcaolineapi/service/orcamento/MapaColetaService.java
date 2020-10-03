package com.orcaolineapi.service.orcamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.orcamento.ItemMapa;
import com.orcaolineapi.modelo.orcamento.MapaColeta;
import com.orcaolineapi.modelo.orcamento.Status;
import com.orcaolineapi.repository.orcamento.MapaColetaRepository;
import com.orcaolineapi.service.AbstractService;

@Service
public class MapaColetaService extends AbstractService<MapaColeta> {

	private @Autowired MapaColetaRepository repository;

	@Override
	public MapaColetaRepository getRepository() {
		return repository;
	}
	
	public MapaColeta save(MapaColeta mapa) {
		if(mapa.getId() != null) {
			MapaColeta mapaSalvo = repository.findById(mapa.getId()).get();
			mapaSalvo.setDescricao(mapa.getDescricao());
			return repository.save(mapaSalvo);
		}
		return repository.save(mapa);
	}
	
	public MapaColeta addItem(ItemMapa item) {
		if(item.getMapa().getId() != null) {
			MapaColeta mapaSalvo = repository.findById(item.getMapa().getId()).get();
			mapaSalvo.getItens().add(item);
			return repository.save(mapaSalvo);
		}
		return null;
	}
	
	public MapaColeta deleteItem(ItemMapa item) {
		if(item.getMapa().getId() != null) {
			MapaColeta mapaSalvo = repository.findById(item.getMapa().getId()).get();
			mapaSalvo.getItens().removeIf(i -> i.getId() == item.getId());
			return repository.save(mapaSalvo);
		}
		return null;
	}
	
	public MapaColeta iniciarCotacao(Long idMapa) {
		if(idMapa != null) {
			MapaColeta mapaSalvo = repository.findById(idMapa).get();
			mapaSalvo.setStatus(Status.EM_ANDAMENTO);
			return repository.save(mapaSalvo);
		}
		return null;
	}
	
	public MapaColeta encerrar(Long idMapa) {
		if(idMapa != null) {
			MapaColeta mapaSalvo = repository.findById(idMapa).get();
			mapaSalvo.setStatus(Status.FECHADO);
			return repository.save(mapaSalvo);
		}
		return null;
	}
	
	

}
