package com.orcaolineapi.repository.usuario;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.orcaolineapi.modelo.usuario.Permissao;
import com.orcaolineapi.modelo.usuario.TipoUsuario;
import com.orcaolineapi.modelo.usuario.Usuario;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {
	
	private @Autowired UsuarioRepository repositoryU;

	private @Autowired TipoUsuarioRepository repositoryT;
	
	private @Autowired PermissaoRepository repositoryP;
	
	public TipoUsuario validTipoUsuario() {
		List<Permissao> permissoes = validPermissao();
		TipoUsuario tip = new TipoUsuario("Nome do TipoUsuario", "Descricao do TipoUsuario", permissoes);
		this.repositoryT.save(tip);
		return tip;
	}
	
	public List<Permissao> validPermissao() {
		List<Permissao> permissoes = new ArrayList<Permissao>();
		Permissao per = new Permissao("Nome da Permissao", "Descricao da Permissao");
		permissoes.add(per);
		this.repositoryP.save(per);
		return permissoes;
	}

	/* 	public Usuario(String email, String senha, String cnpj, String razaoSocial, String nomeFantasia, TipoUsuario tipoUsuario) {
  */
	
	@Test
	public void saveUsuarioWithNotNullIdShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			TipoUsuario tip = validTipoUsuario();
			
			Usuario usu = new Usuario("usuario.usuario@gmail.com ", "12345678Usuario@", "12345678910111", "Razao Social do Usuario", "Nome fantasia do Usuario", tip);
			this.repositoryU.save(usu);
			assertThat(tip.getId()).isNotNull();
		});
		
	}
	
	@Test
	public void saveUsuarioNullShouldThrowsInvalidDataAccessApiUsageException() {
		
		Throwable exception = assertThrows(InvalidDataAccessApiUsageException.class, () -> {			
			Usuario usu = null;
			this.repositoryU.save(usu);
		});
		
		assertEquals("Target object must not be null; nested exception is java.lang.IllegalArgumentException: Target object must not be null", exception.getMessage());
	}
	
	@Test
	public void saveUsuarioWithNullTipoUsuarioShouldThrowsNoneException() {
		
		assertDoesNotThrow(() -> {
			TipoUsuario tip = null;
			
			Usuario usu = new Usuario("usuariousuario.com.br", "123Usuario@", "12345678910111", "Razao Social do Usuario", "Nome fantasia do Usuario", tip);
			this.repositoryU.save(usu);
			assertThat(usu.getId()).isNotNull();
		});
	}
	
	
	@Test
	public void saveUsuarioWithInvalidIdTipoUsuarioShouldThrowsDataIntegrityViolationException() {
		
		Throwable exception = assertThrows(DataIntegrityViolationException.class, () -> {	
			
			TipoUsuario tip = validTipoUsuario();
			tip.setId(Long.valueOf(999999999));
			
			Usuario usu = new Usuario("usuario.usuario@gmail.com ", "123Usuario@", "12345678910111", "Razao Social do Usuario", "Nome fantasia do Usuario", tip);
			this.repositoryU.save(usu);
		});
		
		assertEquals("could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement", exception.getMessage());
	}
	
	@Test
	public void saveUsuarioWithBlankEmailShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			TipoUsuario tip = validTipoUsuario();
			
			Usuario usu = new Usuario("", "123Usuario@", "12345678910111", "Razao Social do Usuario", "Nome fantasia do Usuario", tip);
			this.repositoryU.save(usu);
		});
		
		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'") && (exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'") && (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'"));
	}

	@Test
	public void saveUsuarioWithBlankSenhaShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			TipoUsuario tip = validTipoUsuario();
			
			Usuario usu = new Usuario("usuario.usuario@gmail.com ", "", "12345678910111", "Razao Social do Usuario", "Nome fantasia do Usuario", tip);
			this.repositoryU.save(usu);
		});
		
		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'") && (exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'") && (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 300.'"));
	}


	@Test
	public void saveUsuarioWithNumbersInEmailShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			TipoUsuario tip = validTipoUsuario();
			
			Usuario usu = new Usuario("usuario@usuario123456.com.br", "123Usuario@", "12345678910111", "Razao Social do Usuario", "Nome fantasia do Usuario", tip);
			this.repositoryU.save(usu);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}
	
	@Test
	public void saveUsuarioWithNumbersInRazaoSocialShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			TipoUsuario tip = validTipoUsuario();

			Usuario usu = new Usuario("usuario.usuario@gmail.com ", "123Usuario@", "12345678910111", "Razao Social do Usuario123", "Nome fantasia do Usuario", tip);
			this.repositoryU.save(usu);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");

	}
	
	@Test
	public void saveUsuarioWithNumbersInNomeFantasiaShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			TipoUsuario tip = validTipoUsuario();

			Usuario usu = new Usuario("usuario.usuario@gmail.com ", "123Usuario@", "12345678910111", "Razao Social do Usuario", "Nome fantasia do Usuario123", tip);
			this.repositoryU.save(usu);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");

	}
	
	@Test
	public void saveUsuarioWithBlankSpacesInEmailShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			TipoUsuario tip = validTipoUsuario();

			Usuario usu = new Usuario("              ", "123Usuario@", "12345678910111", "Razao Social do Usuario", "Nome fantasia do Usuario", tip);
			this.repositoryU.save(usu);
		});
		
		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'");
	}
	
	@Test
	public void saveUsuarioWithSpecialCharactersInEmailShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			TipoUsuario tip = validTipoUsuario();

			Usuario usu = new Usuario("@@@@@@@@@", "123Usuario@", "12345678910111", "Razao Social do Usuario", "Nome fantasia do Usuario", tip);
			this.repositoryU.save(usu);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}
	
	@Test
	public void saveUsuarioWithSpecialCharactersInSenhaShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			TipoUsuario tip = validTipoUsuario();

			Usuario usu = new Usuario("usuario.usuario@gmail.com ", "@@@@@@@@@", "12345678910111", "Razao Social do Usuario", "Nome fantasia do Usuario", tip);
			this.repositoryU.save(usu);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}
	
	@Test
	public void saveUsuarioWithTooLongEmailShouldThrowsConstraintViolationException() {
		
		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			TipoUsuario tip = validTipoUsuario();

			Usuario usu = new Usuario("usuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuario"+
			"usuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuariousuario"+
					"@usuario.com.br", "123Usuario@", "12345678910111", "Razao Social do Usuario", "Nome fantasia do Usuario123", tip);
			this.repositoryU.save(usu);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'") && (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 0 e 300.'"));
	}
	
	/*
	 
	 			Usuario usu = new Usuario("usuario.usuario@gmail.com ", "123Usuario@", "12345678910111", "Razao Social do Usuario", "Nome fantasia do Usuario", tip);

	 
	 * */
	
	@Test
	public void saveUsuarioWithTooLongRazaoSocialShouldThrowsConstraintViolationException() {
		
		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			TipoUsuario tip = validTipoUsuario();

 			Usuario usu = new Usuario("usuario.usuario@gmail.com ", "123Usuario@", "12345678910111", "Razao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do Usuario"+
 			"Razao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do UsuarioRazao Social do Usuario"+
 			"Razao Social do UsuarioRazao Social do Usuario", "Nome fantasia do Usuario", tip);
 			this.repositoryU.save(usu);
		});

	    assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 300.'");
	}
	
	@Test
	public void saveUsuarioWithTooLongNomeFantasiaShouldThrowsConstraintViolationException() {
		
		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			TipoUsuario tip = validTipoUsuario();

 			Usuario usu = new Usuario("usuario.usuario@gmail.com ", "123Usuario@", "12345678910111", "Razao Social do Usuario", "Nome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do Usuario"+ 
 			"Nome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do UsuarioNome fantasia do Usuario"+
 			"Nome fantasia do Usuario", tip);
 			this.repositoryU.save(usu);
		});

	    assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 0 e 300.'");
	}
	
	@Test
	public void saveUsuarioWithTooLongCnpjShouldThrowsConstraintViolationException() {
		
		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			TipoUsuario tip = validTipoUsuario();

 			Usuario usu = new Usuario("usuario.usuario@gmail.com ", "123Usuario@", "12345678910111121314", "Razao Social do Usuario", "Nome fantasia do Usuario", tip);
 			this.repositoryU.save(usu);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas dígitos'") && (exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'") && (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 14 e 14.'"));
	}
	
	@Test
	public void saveUsuarioWithTooShortEmailShouldThrowsConstraintViolationException() {
		
		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			TipoUsuario tip = validTipoUsuario();

 			Usuario usu = new Usuario("usuario", "123Usuario@", "12345678910111", "Razao Social do Usuario", "Nome fantasia do Usuario", tip);
			this.repositoryU.save(usu);
		});

	    assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 300.'");
	}
	
	@Test
	public void saveUsuarioWithTooShortSenhaShouldThrowsConstraintViolationException() {
		
		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			TipoUsuario tip = validTipoUsuario();

 			Usuario usu = new Usuario("usuario.usuario@gmail.com ", "123Usu@", "12345678910111", "Razao Social do Usuario", "Nome fantasia do Usuario", tip);
			this.repositoryU.save(usu);
		});

	    assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 300.'");
	}
}