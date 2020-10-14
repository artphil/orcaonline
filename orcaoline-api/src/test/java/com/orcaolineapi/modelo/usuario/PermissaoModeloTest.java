package com.orcaolineapi.modelo.usuario;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PermissaoModeloTest {
	
	private Permissao perm;

	@BeforeEach
	void setUp() throws Exception {
		perm = new Permissao("Nome da Permissao","Descricao da Permissao", null);
	}

	
	@Test
	public void savePermissaoUsingPrePersistShouldThrowsNoneException() {
		assertDoesNotThrow(() -> {
			perm.prepersist();
			assertEquals(perm.getNome().toUpperCase(), perm.getNome());
		});
	}
}
