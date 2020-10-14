package com.orcaolineapi.repository.usuario;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;

import com.orcaolineapi.modelo.usuario.ModalidadeTipoUsuario;
import com.orcaolineapi.modelo.usuario.TipoUsuario;
import com.orcaolineapi.modelo.usuario.Usuario;

@DataJpaTest
@ActiveProfiles("test")

@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {

	private @Autowired UsuarioRepository repositoryU;

	private @Autowired TipoUsuarioRepository repositoryT;
	
	private Usuario usu;
	private TipoUsuario tip;

	@BeforeEach
	void setUp() throws Exception {
		tip = new TipoUsuario("Nome do TipoUsuario", "Descricao do TipoUsuario", ModalidadeTipoUsuario.INTERNO);
		this.repositoryT.save(tip);

		usu = new Usuario("usuario.usuario@gmail.com", "123Usuario@", "12345678910111",
				"Razao Social do Usuario", "Nome fantasia do Usuario", tip);
	}

	@Test
	public void saveUsuarioWithNotNullIdShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			this.repositoryU.save(usu);		
			assertThat(usu.getId()).isNotNull();
		});

	}

	@Test
	public void saveUsuarioNullShouldThrowsInvalidDataAccessApiUsageException() {

		Throwable exception = assertThrows(InvalidDataAccessApiUsageException.class, () -> {
			usu = null;
			this.repositoryU.save(usu);
		});

		assertEquals(
				"Target object must not be null; nested exception is java.lang.IllegalArgumentException: Target object must not be null",
				exception.getMessage());
	}

	@Test
	public void saveUsuarioWithNullTipoUsuarioShouldThrowsConstraintViolationException() {
		
		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			tip = null;
			usu.setTipoUsuario(tip);
			this.repositoryU.save(usu);
		});
		
		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} é obrigatório(a).'");
	}

	@Test
	public void saveUsuarioWithBlankEmailShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			usu.setEmail("");
			this.repositoryU.save(usu);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'"));
	}

	@Test
	public void saveUsuarioWithBlankSenhaShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			usu.setSenha("");
			Usuario usu = new Usuario("usuario.usuario@gmail.com", "", "12345678910111", "Razao Social do Usuario",
					"Nome fantasia do Usuario", tip);
			this.repositoryU.save(usu);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 300.'"));
	}

	@Test
	public void saveUsuarioWithNumbersInEmailShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			usu.setEmail("usuario123456@.com");
			this.repositoryU.save(usu);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='deve ser um endereço de e-mail bem formado'");
	}

	@Test
	public void saveUsuarioWithNumbersInRazaoSocialShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			usu.setNome("Razao Social do Usuario123");
			this.repositoryU.save(usu);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}

	@Test
	public void saveUsuarioWithNumbersInNomeFantasiaShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			usu.setNomeFantasia("Nome fantasia do Usuario123");
			this.repositoryU.save(usu);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");

	}

	@Test
	public void saveUsuarioWithBlankSpacesInEmailShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			usu.setEmail("              ");
			this.repositoryU.save(usu);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'");
	}

	@Test
	public void saveUsuarioWithSpecialCharactersInEmailShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			usu.setEmail("@@@@@@@@@");
			this.repositoryU.save(usu);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='deve ser um endereço de e-mail bem formado'");
	}

	@Test
	public void saveUsuarioWithTooLongEmailShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			usu.setEmail("usuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuario"
					+ "usuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuario"
					+ "@usuario.com.br");
			this.repositoryU.save(usu);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 0 e 300.'"));
	}
	
	@Test
	public void saveUsuarioWithTooLongRazaoSocialShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			usu.setNome("Razao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do Usuario"
					+ "Razao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do Usuario"
					+ "Razao Social do UsuarioRazao Social do Usuario");
			this.repositoryU.save(usu);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 0 e 300.'");
	}

	@Test
	public void saveUsuarioWithTooLongNomeFantasiaShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			usu.setNome("Nome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do Usuario"
					+ "Nome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do Usuario"
					+ "Nome fantasia do Usuario");
			this.repositoryU.save(usu);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 0 e 300.'");
	}

	@Test
	public void saveUsuarioWithTooLongCnpjShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			usu.setCnpj("12345678910111121314");
			this.repositoryU.save(usu);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas dígitos'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 14 e 14.'"));
	}

	@Test
	public void saveUsuarioWithTooShortEmailShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {			
			usu.setEmail("usuario");
			this.repositoryU.save(usu);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='deve ser um endereço de e-mail bem formado'");
	}

	@Test
	public void saveUsuarioWithTooShortSenhaShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			usu.setSenha("123Usu@");
			this.repositoryU.save(usu);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 8 e 300.'"));
	
	}
}