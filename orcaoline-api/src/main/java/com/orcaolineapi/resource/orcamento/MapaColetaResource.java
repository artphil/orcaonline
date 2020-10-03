package com.orcaolineapi.resource.orcamento;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcaolineapi.event.RecursoCriadoEvent;
import com.orcaolineapi.modelo.orcamento.ItemMapa;
import com.orcaolineapi.modelo.orcamento.MapaColeta;
import com.orcaolineapi.modelo.orcamento.UnidadeMedida;
import com.orcaolineapi.repository.orcamento.MapaColetaRepository;
import com.orcaolineapi.resource.AbstractResource;
import com.orcaolineapi.service.orcamento.MapaColetaService;

@RestController
@RequestMapping("/mapas")
public class MapaColetaResource extends AbstractResource<MapaColeta> {

	private @Autowired MapaColetaRepository repository;

	private @Autowired MapaColetaService service;

	@Override
	public MapaColetaRepository getRepository() {
		return repository;
	}

	@Override
	public MapaColetaService getService() {
		return service;
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseEntity<MapaColeta> save(@Valid @RequestBody MapaColeta mapa, HttpServletResponse response) {
		MapaColeta mapaSave = service.save(mapa);
		getPublisher().publishEvent(new RecursoCriadoEvent(this, response, mapaSave.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(mapaSave);
	}

	@CrossOrigin
	@PutMapping("addItem")
	public ResponseEntity<MapaColeta> addItem(@Valid ItemMapa item) {
		return ResponseEntity.ok(getService().deleteItem(item));
	}
	
	@CrossOrigin
	@PutMapping("deleteItem")
	public ResponseEntity<MapaColeta> deleteItem(@Valid ItemMapa item) {
		return ResponseEntity.ok(getService().deleteItem(item));
	}
	
	@CrossOrigin
	@PutMapping("iniciarCotacao/{id}")
	public ResponseEntity<MapaColeta> iniciarCotacao(@PathVariable Long id) {
		return ResponseEntity.ok(getService().iniciarCotacao(id));
	}
	
	@CrossOrigin
	@PutMapping("encerrar/{id}")
	public ResponseEntity<MapaColeta> encerrar(@PathVariable Long id) {
		return ResponseEntity.ok(getService().encerrar(id));
	}
	
	@CrossOrigin
	@GetMapping("unidades")
	public UnidadeMedida[] getUnidades() {
		return UnidadeMedida.values();
	}
}
