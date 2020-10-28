package com.orcaolineapi.service.orcamento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.orcamento.ItemMapa;
import com.orcaolineapi.modelo.orcamento.MapaColeta;
import com.orcaolineapi.modelo.orcamento.Status;
import com.orcaolineapi.repository.orcamento.ItemMapaRepository;
import com.orcaolineapi.repository.orcamento.MapaColetaRepository;
import com.orcaolineapi.repository.orcamento.filter.MapaColetaFilter;
import com.orcaolineapi.service.AbstractService;

@Service
public class MapaColetaService extends AbstractService<MapaColeta> {

	private @Autowired MapaColetaRepository repository;

	private @Autowired ItemMapaRepository itemMapaRepository;
	
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
		MapaColeta map = new MapaColeta(getUsuarioLogado());
		map.setDescricao(mapa.getDescricao());
		return repository.save(map);
	}
	
	public MapaColeta addItem(ItemMapa item) {
		item.check();
		if(item.getId() != null) {
			itemMapaRepository.save(item);
			return repository.findById(item.getMapa().getId()).get();
		}
		if(item.getMapa().getId() != null) {
			MapaColeta mapaSalvo = repository.findById(item.getMapa().getId()).get();
			item.setMapa(mapaSalvo);
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
			MapaColeta mapa = repository.findById(idMapa).get();
			mapa.encerrar();
			return repository.save(mapa);
		}
		return null;
	}
	
	public MapaColeta aprovarOrcamento(Long idMapa, Long idOrcamento) {
		if(idMapa != null) {
			MapaColeta mapa = repository.findById(idMapa).get();
			mapa.aprovarOrcamento(idOrcamento);
			return repository.save(mapa);
		}
		return null;
	}

	public List<MapaColeta> filtrar(MapaColetaFilter filtro){
		filtro.setIdUsuario(getUsuarioLogado().getId());
		return repository.filtrar(filtro);
		
	}
	
	public List<MapaColeta> filtrarEmAndamento(MapaColetaFilter filtro){
		filtro.setIdStatus(6L);
		filtro.setIdUsuario(null);
		return repository.filtrar(filtro);
	}
	
}
