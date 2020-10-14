package com.orcaolineapi.modelo.usuario;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.orcaolineapi.modelo.orcamento.Status;

class UsuarioModeloTest {
	
	private Usuario usu;

	@BeforeEach
	void setUp() throws Exception {
		usu = new Usuario("usuario.usuario@gmail.com", "123Usuario@", "12345678910111",
				"Razao Social do Usuario", "Nome fantasia do Usuario", null);
	}
	
	@Test
	public void saveUsuarioWithUsedStatusShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			assertThat(Usuario.usedStatus()).contains(Status.ATIVO);
			assertThat(Usuario.usedStatus()).contains(Status.INATIVO);
		});
	}
	
	@Test
	public void saveUsuarioWithNotUsedStatusShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			assertThat(Usuario.usedStatus()).doesNotContain(Status.CANCELADO);
			assertThat(Usuario.usedStatus()).doesNotContain(Status.EM_ANDAMENTO);
			assertThat(Usuario.usedStatus()).doesNotContain(Status.FECHADO);
			assertThat(Usuario.usedStatus()).doesNotContain(Status.ABERTO);
		});
	}	
	
	@Test
	public void saveUsuarioWithEncodaSenhaShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {			
			String senha = usu.getSenha();
			usu.encodaSenha();
			String senhaEncryp = usu.getSenha();
			assertNotEquals(senhaEncryp, senha);
		});
	}


}
