package com.orcaolineapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.orcaolineapi.event.RecursoCriadoEvent;
import com.orcaolineapi.modelo.AbstractModel;
import com.orcaolineapi.repository.AbstractRepository;
import com.orcaolineapi.service.AbstractService;

@RestController
public abstract class AbstractResource<T extends AbstractModel> {

	public abstract AbstractRepository<T, Long> getRepository();

	public abstract AbstractService<T> getService();

	private @Autowired ApplicationEventPublisher publisher;

	@CrossOrigin
	@GetMapping
	public List<T> getRecursos() {
		return getRepository().findAll();
	}

	@CrossOrigin
	@PostMapping
	public ResponseEntity<T> save(@Valid @RequestBody T resource, HttpServletResponse response) {
		T resourceSave = getRepository().save(resource);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, resourceSave.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(resourceSave);
	}

	@CrossOrigin
	@GetMapping("/{id}")
	public ResponseEntity<T> getById(@PathVariable Long id) {
		T resource = getRepository().findById(id).get();
		return resource != null ? ResponseEntity.ok(resource) : ResponseEntity.notFound().build();
	}

	@CrossOrigin
	@PutMapping("/{id}")
	public ResponseEntity<T> update(@PathVariable Long id, @RequestBody T resource) {
		return ResponseEntity.ok(getService().update(id, resource));
	}

	@CrossOrigin
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		getRepository().deleteById(id);
	}
}