package com.orcaolineapi.resource.orcamento;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.orcaolineapi.event.RecursoCriadoEvent;
import com.orcaolineapi.modelo.orcamento.ItemOrcamento;
import com.orcaolineapi.modelo.orcamento.Orcamento;
import com.orcaolineapi.repository.orcamento.OrcamentoRepository;
import com.orcaolineapi.repository.orcamento.filter.OrcamentoFilter;
import com.orcaolineapi.resource.AbstractResource;
import com.orcaolineapi.service.orcamento.OrcamentoService;

@RestController
@RequestMapping("/orcamentos")
public class OrcamentoResource extends AbstractResource<Orcamento> {

	private @Autowired OrcamentoRepository repository;

	private @Autowired OrcamentoService service;

	@Override
	public OrcamentoRepository getRepository() {
		return repository;
	}

	@Override
	public OrcamentoService getService() {
		return service;
	}
	
	@CrossOrigin
	@PostMapping("create/{idMapa}")
	public ResponseEntity<Orcamento> create(@PathVariable Long idMapa, HttpServletResponse response) {
		Orcamento orcamento = getService().create(idMapa);
		getPublisher().publishEvent(new RecursoCriadoEvent(this, response, orcamento.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(orcamento);
	}
	
	@CrossOrigin
	@PutMapping("enviar/{id}")
	public ResponseEntity<Orcamento> enviar(@PathVariable Long id) {
		return ResponseEntity.ok(service.enviar(id));
	}

	@CrossOrigin
	@PostMapping("updateItem")
	public ResponseEntity<Orcamento> udateItem(@RequestBody ItemOrcamento item) {
		return ResponseEntity.ok(service.updateItem(item));
	}
	
	@CrossOrigin
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.deletar(id);
	}
	
	@CrossOrigin
	@GetMapping("filtrar")
	public List<Orcamento> filtrar(OrcamentoFilter filtro){
		return service.filtrar(filtro);
	}
}
