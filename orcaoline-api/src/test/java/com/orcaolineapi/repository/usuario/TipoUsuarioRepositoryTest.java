package com.orcaolineapi.repository.usuario;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;

import com.orcaolineapi.modelo.usuario.ModalidadeTipoUsuario;
import com.orcaolineapi.modelo.usuario.TipoUsuario;

@DataJpaTest
@ActiveProfiles("test")

@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TipoUsuarioRepositoryTest {

	private @Autowired TipoUsuarioRepository repositoryT;

	@Test
	public void saveTipoUsuarioWithNotNullIdShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			TipoUsuario tip = new TipoUsuario("Nome do TipoUsuario", "Descricao do TipoUsuario", ModalidadeTipoUsuario.INTERNO);
			this.repositoryT.save(tip);
			assertThat(tip.getId()).isNotNull();
		});

	}

	@Test
	public void saveTipoUsuarioNullShouldThrowsInvalidDataAccessApiUsageException() {

		Throwable exception = assertThrows(InvalidDataAccessApiUsageException.class, () -> {
			TipoUsuario tip = null;
			this.repositoryT.save(tip);
		});

		assertEquals(
				"Target object must not be null; nested exception is java.lang.IllegalArgumentException: Target object must not be null",
				exception.getMessage());
	}
	
	@Test
	public void saveTipoUsuarioWithBlankNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {

			TipoUsuario tip = new TipoUsuario("", "Descrição do TipoUsuario" , ModalidadeTipoUsuario.INTERNO);
			this.repositoryT.save(tip);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'"));
	}

	@Test
	public void saveTipoUsuarioWithBlankDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {

			TipoUsuario tip = new TipoUsuario("Nome do TipoUsuario", "", ModalidadeTipoUsuario.INTERNO);
			this.repositoryT.save(tip);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 200.'"));
	}

	@Test
	public void saveTipoUsuarioWithNumbersInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {

			TipoUsuario tip = new TipoUsuario("123456789", "Descrição do TipoUsuario", ModalidadeTipoUsuario.INTERNO);
			this.repositoryT.save(tip);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}

	@Test
	public void saveTipoUsuarioWithNumbersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {

			TipoUsuario tip = new TipoUsuario("Nome da Descrição", "123456789", ModalidadeTipoUsuario.INTERNO);
			this.repositoryT.save(tip);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");

	}

	@Test
	public void saveTipoUsuarioWithBlankSpacesInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {

			TipoUsuario tip = new TipoUsuario("        ", "Descrição do TipoUsuario", ModalidadeTipoUsuario.INTERNO);
			this.repositoryT.save(tip);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'");
	}

	@Test
	public void saveTipoUsuarioWithSpecialCharactersInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			

			TipoUsuario tip = new TipoUsuario("@@@@@@@", "Descrição do TipoUsuario" , ModalidadeTipoUsuario.INTERNO);
			this.repositoryT.save(tip);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}

	@Test
	public void saveTipoUsuarioWithSpecialCharactersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {

			TipoUsuario tip = new TipoUsuario("Nome do TipoUsuario", "@@@@@@@", ModalidadeTipoUsuario.INTERNO);
			this.repositoryT.save(tip);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}

	@Test
	public void saveTipoUsuarioWithTooLongNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {

			TipoUsuario tip = new TipoUsuario(
					"Nome do TipoUsuarioNome do TipoUsuarioNome do TipoUsuarioNome do TipoUsuarioNome do TipoUsuarioNome do TipoUsuario"
							+ "Nome do TipoUsuarioNome do TipoUsuarioNome do TipoUsuarioNome do TipoUsuarioNome do TipoUsuario",
					"Descrição do TipoUsuario", ModalidadeTipoUsuario.INTERNO);
			this.repositoryT.save(tip);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 200.'");
	}

	@Test
	public void saveTipoUsuarioWithTooShortNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
		
			TipoUsuario tip = new TipoUsuario("Nome", "Descrição do TipoUsuario", ModalidadeTipoUsuario.INTERNO);
			this.repositoryT.save(tip);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 200.'");
	}

	@Test
	public void saveTipoUsuarioWithTooLongDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {

			TipoUsuario tip = new TipoUsuario("Nome do TipoUsuario",
					"Descrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuario"
							+ "Descrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuario"
							+ "Descrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuario"
							+ "Descrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuario", ModalidadeTipoUsuario.INTERNO);
			this.repositoryT.save(tip);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 200.'");
	}
}