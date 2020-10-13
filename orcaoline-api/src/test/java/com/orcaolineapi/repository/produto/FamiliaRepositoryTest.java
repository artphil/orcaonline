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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;

import com.orcaolineapi.modelo.produto.Familia;
import com.orcaolineapi.modelo.produto.Segmento;

@DataJpaTest
@ActiveProfiles("test")

@AutoConfigureTestDatabase(replace = Replace.NONE)
public class FamiliaRepositoryTest {

	private @Autowired FamiliaRepository repositoryF;

	private @Autowired SegmentoRepository repositoryS;

	public Segmento validSegmento() {
		Segmento seg = new Segmento("Nome do Segmento", "Descricao do Segmento");
		this.repositoryS.save(seg);
		return seg;
	}

	@Test
	public void saveFamiliaWithNotNullIdShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			Segmento seg = validSegmento();

			Familia fam = new Familia("Nome da Familia", "Descricao da Familia", seg);
			this.repositoryF.save(fam);
			assertThat(fam.getId()).isNotNull();
		});

	}

	@Test
	public void saveFamiliaNullShouldThrowsInvalidDataAccessApiUsageException() {

		Throwable exception = assertThrows(InvalidDataAccessApiUsageException.class, () -> {
			Familia fam = null;
			this.repositoryF.save(fam);
		});

		assertEquals(
				"Target object must not be null; nested exception is java.lang.IllegalArgumentException: Target object must not be null",
				exception.getMessage());
	}

	@Test
	public void saveFamiliaWithNullSegmentoShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Segmento seg = null;
			Familia fam = new Familia("Nome da Familia", "Descricao da Familia", seg);
			this.repositoryF.save(fam);

		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} é obrigatório(a).'"));
	}

	@Test
	public void saveFamiliaWithInvalidIdSegmentoShouldThrowsDataIntegrityViolationException() {

		Throwable exception = assertThrows(DataIntegrityViolationException.class, () -> {
			Segmento seg = validSegmento();
			seg.setId(Long.valueOf(999999999));
			// falta codigo aqui para mudar o id para um invalido
			Familia fam = new Familia("Nome da Familia", "Descricao da Familia", seg);
			this.repositoryF.save(fam);

		});

		assertEquals(
				"could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
				exception.getMessage());
	}

	@Test
	public void saveFamiliaWithBlankNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Segmento seg = validSegmento();

			Familia fam = new Familia("", "Descrição da Familia", seg);
			this.repositoryF.save(fam);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'"));
	}

	@Test
	public void saveFamiliaWithBlankDescriptionShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			Segmento seg = validSegmento();

			Familia fam = new Familia("Nome da Familia", "", seg);
			this.repositoryF.save(fam);
		});

	}

	@Test
	public void saveFamiliaWithNumbersInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Segmento seg = validSegmento();

			Familia fam = new Familia("123456789", "Descrição da Familia", seg);
			this.repositoryF.save(fam);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}

	@Test
	public void saveFamiliaWithNumbersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Segmento seg = validSegmento();

			Familia fam = new Familia("Nome da Descrição", "123456789", seg);
			this.repositoryF.save(fam);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");

	}

	@Test
	public void saveFamiliaWithBlankSpacesInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Segmento seg = validSegmento();

			Familia fam = new Familia("        ", "Descrição da Familia", seg);
			this.repositoryF.save(fam);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'");
	}

	@Test
	public void saveFamiliaWithSpecialCharactersInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Segmento seg = validSegmento();

			Familia fam = new Familia("@@@@@@@", "Descrição da Familia", seg);
			this.repositoryF.save(fam);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}

	@Test
	public void saveFamiliaWithSpecialCharactersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Segmento seg = validSegmento();

			Familia fam = new Familia("Nome da Familia", "@@@@@@@", seg);
			this.repositoryF.save(fam);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}

	@Test
	public void saveFamiliaWithTooLongNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Segmento seg = validSegmento();

			Familia fam = new Familia(
					"Nome da FamiliaNome da FamiliaNome da FamiliaNome da FamiliaNome da FamiliaNome da Familia"
							+ "Nome da FamiliaNome da FamiliaNome da FamiliaNome da FamiliaNome da Familia",
					"Descrição da Familia", seg);
			this.repositoryF.save(fam);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'");
	}

	@Test
	public void saveFamiliaWithTooShortNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Segmento seg = validSegmento();

			Familia fam = new Familia("Nome", "Descrição da Familia", seg);
			this.repositoryF.save(fam);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'");
	}

	@Test
	public void saveFamiliaWithTooLongDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Segmento seg = validSegmento();

			Familia fam = new Familia("Nome da Familia",
					"Descrição da FamiliaDescrição da FamiliaDescrição da FamiliaDescrição da Familia"
							+ "Descrição da FamiliaDescrição da FamiliaDescrição da FamiliaDescrição da FamiliaDescrição da FamiliaDescrição da Familia"
							+ "Descrição da FamiliaDescrição da FamiliaDescrição da FamiliaDescrição da FamiliaDescrição da FamiliaDescrição da Familia"
							+ "Descrição da FamiliaDescrição da FamiliaDescrição da FamiliaDescrição da Familia",
					seg);
			this.repositoryF.save(fam);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 0 e 200.'");
	}
}