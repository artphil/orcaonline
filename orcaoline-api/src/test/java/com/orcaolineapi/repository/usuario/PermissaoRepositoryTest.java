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

import com.orcaolineapi.modelo.sistema.Modulo;
import com.orcaolineapi.modelo.usuario.Permissao;

@DataJpaTest
@ActiveProfiles("test")

@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PermissaoRepositoryTest {

	private @Autowired PermissaoRepository repositoryP;
	
	private Permissao per;

	@BeforeEach
	void setUp() throws Exception {
		per = new Permissao("Nome da Permissao","Descricao da Permissao", Modulo.ORCAMENTO);
	}
	
	@Test
	public void savePermissaoWithNotNullIdShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			this.repositoryP.save(per);
			assertThat(per.getId()).isNotNull();
		});
	}
	
	@Test
	public void savePermissaoNullShouldThrowsInvalidDataAccessApiUsageException() {

		Throwable exception = assertThrows(InvalidDataAccessApiUsageException.class, () -> {
			per = null;
			this.repositoryP.save(per);
		});

		assertEquals(
				"Target object must not be null; nested exception is java.lang.IllegalArgumentException: Target object must not be null",
				exception.getMessage());
	}

	@Test
	public void savePermissaoWithBlankNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			per.setNome("");
			this.repositoryP.save(per);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'"));
	}

	@Test
	public void savePermissaoWithBlankDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			per.setDescricao("");
			this.repositoryP.save(per);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'"));
	}

	@Test
	public void savePermissaoWithNumbersInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			per.setNome("123456789");
			this.repositoryP.save(per);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}

	@Test
	public void savePermissaoWithNumbersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			per.setDescricao("123456789");
			this.repositoryP.save(per);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");

	}

	@Test
	public void savePermissaoWithBlankSpacesInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			per.setNome("        ");
			this.repositoryP.save(per);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'");
	}

	@Test
	public void savePermissaoWithSpecialCharactersInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			per.setNome("@@@@@@@");
			this.repositoryP.save(per);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}

	@Test
	public void savePermissaoWithSpecialCharactersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			per.setDescricao("@@@@@@@");
			this.repositoryP.save(per);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}

	@Test
	public void savePermissaoWithTooLongNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			per.setNome("Nome da PermissaoNome da PermissaoNome da PermissaoNome da PermissaoNome da PermissaoNome da Permissao"
					+ "Nome da PermissaoNome da PermissaoNome da PermissaoNome da PermissaoNome da PermissaoNome da PermissaoNome da PermissaoNome da Permissao"
					+ "Nome da PermissaoNome da Permissao");
			this.repositoryP.save(per);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 100.'");
	}

	@Test
	public void savePermissaoWithTooShortNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			per.setNome("Nome");
			this.repositoryP.save(per);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 100.'");
	}

	@Test
	public void savePermissaoWithTooLongDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			per.setDescricao("Descrição da PermissaoDescrição da PermissaoDescrição da PermissaoDescrição da Permissao"
					+ "Descrição da PermissaoDescrição da PermissaoDescrição da PermissaoDescrição da PermissaoDescrição da PermissaoDescrição da Permissao");			
			this.repositoryP.save(per);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 200.'");
	}
}