package com.orcaolineapi.repository.produto;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;

import com.orcaolineapi.modelo.produto.Segmento;

@DataJpaTest
@ActiveProfiles("test")

@AutoConfigureTestDatabase(replace = Replace.NONE)
public class SegmentoRepositoryTest {

	private @Autowired SegmentoRepository repository;

	@Test
	public void saveSegmentoWithNotNullIdShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			Segmento seg = new Segmento("Nome do Segmento", "Descricao do Segmento");
			this.repository.save(seg);
			assertThat(seg.getId()).isNotNull();
		});
	}

	@Test
	public void saveSegmentoNullShouldThrowsInvalidDataAccessApiUsageException() {

		Throwable exception = assertThrows(InvalidDataAccessApiUsageException.class, () -> {
			Segmento seg = null;
			this.repository.save(seg);
		});

		assertEquals(
				"Target object must not be null; nested exception is java.lang.IllegalArgumentException: Target object must not be null",
				exception.getMessage());
	}

	@Test
	public void saveSegmentoWithBlankNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Segmento seg = new Segmento("", "Descrição do Segmento");
			this.repository.save(seg);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'"));
	}

	@Test
	public void saveSegmentoWithBlankDescriptionShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			Segmento seg = new Segmento("Nome do Segmento", "");
			this.repository.save(seg);
		});

	}

	@Test
	public void saveSegmentoWithNumbersInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Segmento seg = new Segmento("123456789", "Descrição do Segmento");
			this.repository.save(seg);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}

	@Test
	public void saveSegmentoWithNumbersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Segmento seg = new Segmento("Nome da Descrição", "123456789");
			this.repository.save(seg);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");

	}

	@Test
	public void saveSegmentoWithBlankSpacesInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Segmento seg = new Segmento("        ", "Descrição do Segmento");
			this.repository.save(seg);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'");
	}

	@Test
	public void saveSegmentoWithSpecialCharactersInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Segmento seg = new Segmento("@@@@@@@", "Descrição do Segmento");
			this.repository.save(seg);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}

	@Test
	public void saveSegmentoWithSpecialCharactersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Segmento seg = new Segmento("Nome do Segmento", "@@@@@@@");
			this.repository.save(seg);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}

	@Test
	public void saveSegmentoWithTooLongNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Segmento seg = new Segmento(
					"Nome do SegmentoNome do SegmentoNome do SegmentoNome do SegmentoNome do SegmentoNome do Segmento"
							+ "Nome do SegmentoNome do SegmentoNome do SegmentoNome do Segmento",
					"Descrição do Segmento");
			this.repository.save(seg);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'");
	}

	@Test
	public void saveSegmentoWithTooShortNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Segmento seg = new Segmento("Nome", "Descrição do Segmento");
			this.repository.save(seg);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'");
	}

	@Test
	public void saveSegmentoWithTooLongDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Segmento seg = new Segmento("Nome do Segmento",
					"Descrição do SegmentoDescrição do SegmentoDescrição do SegmentoDescrição do Segmento"
							+ "Descrição do SegmentoDescrição do SegmentoDescrição do SegmentoDescrição do SegmentoDescrição do SegmentoDescrição do Segmento");
			this.repository.save(seg);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 0 e 200.'");
	}
}