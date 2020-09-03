package com.orcaolineapi.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	Optional<T> findById(ID id);

	<S extends T> S save(S entity);	
}
