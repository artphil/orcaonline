package com.orcaolineapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.AbstractModel;
import com.orcaolineapi.repository.AbstractRepository;
	
@Service
public abstract class AbstractService<T extends AbstractModel>  {

	public abstract AbstractRepository<T, Long> getRepository();
	
	public T update(Long id, T resource) {
		Optional<T> resourceSave = getRepository().findById(id);
		if(!resourceSave.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(resource, resourceSave.get(), "id");
		return getRepository().save(resourceSave.get());
	}
	
	
}