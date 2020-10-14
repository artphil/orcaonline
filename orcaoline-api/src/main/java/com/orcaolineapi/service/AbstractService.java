package com.orcaolineapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.AbstractModel;
import com.orcaolineapi.modelo.usuario.Usuario;
import com.orcaolineapi.repository.AbstractRepository;
import com.orcaolineapi.security.UsuarioLogado;

@Service
public abstract class AbstractService<T extends AbstractModel> {

	public abstract AbstractRepository<T, Long> getRepository();

	private @Autowired UsuarioLogado usuarioLogado;
	
	public T update(Long id, T resource) {
		Optional<T> resourceSave = getRepository().findById(id);
		if (!resourceSave.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(resource, resourceSave.get(), "id");
		return getRepository().save(resourceSave.get());
	}
	
	private UsuarioLogado getUsuario() {
		return usuarioLogado;
	}
	
	public Usuario getUsuarioLogado() {
		return getUsuario().usuarioLogado();
	}

}