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

import com.orcaolineapi.modelo.produto.NCM;

@DataJpaTest
@ActiveProfiles("test")

@AutoConfigureTestDatabase(replace = Replace.NONE)
public class NCMRepositoryTest {

	private @Autowired NCMRepository repositoryN;

	@Test
	public void saveNCMWithNotNullIdShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			NCM ncm = new NCM("12345678", "Descrição do NCM");
			this.repositoryN.save(ncm);
			assertThat(ncm.getId()).isNotNull();
		});

	}

	@Test
	public void saveNCMNullShouldThrowsInvalidDataAccessApiUsageException() {

		Throwable exception = assertThrows(InvalidDataAccessApiUsageException.class, () -> {
			NCM ncm = null;
			this.repositoryN.save(ncm);
		});

		assertEquals(
				"Target object must not be null; nested exception is java.lang.IllegalArgumentException: Target object must not be null",
				exception.getMessage());
	}

	@Test
	public void saveNCMEmptyNumberShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = new NCM("", "Descrição do NCM");
			this.repositoryN.save(ncm);
		});

		assertThat((exception.getMessage().contains("interpolatedMessage='{0} deve conter apenas dígitos.'"))
				&& exception.getMessage().contains("interpolatedMessage='{0} deve ter o tamanho entre 8 e 8.'"));
	}

	@Test
	public void saveNCMWithBlankSpacesInNumberShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = new NCM("        ", "Descrição do NCM");
			this.repositoryN.save(ncm);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas dígitos.'");
	}

	@Test
	public void saveNCMWithBlankDescriptionShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			NCM ncm = new NCM("12345678", "");
			this.repositoryN.save(ncm);
		});

	}

	@Test
	public void saveNCMWithEmptyNumberShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = new NCM("", "Descrição do NCM");
			this.repositoryN.save(ncm);
		});

		assertThat((exception.getMessage().contains("interpolatedMessage='{0} deve conter apenas dígitos.'"))
				&& exception.getMessage().contains("interpolatedMessage='{0} deve ter o tamanho entre 8 e 8.'"));
	}

	@Test
	public void saveNCMWithStringInNumberShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = new NCM("Numero do NCM", "Descrição do NCM");
			this.repositoryN.save(ncm);
		});

		assertThat((exception.getMessage().contains("interpolatedMessage='{0} deve conter apenas dígitos.'"))
				&& exception.getMessage().contains("interpolatedMessage='{0} deve ter o tamanho entre 8 e 8.'"));
	}

	@Test
	public void saveNCMWithNumbersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = new NCM("12345678", "12345678");
			this.repositoryN.save(ncm);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");

	}

	@Test
	public void saveNCMWithSpecialCharactersInNumberShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = new NCM("@@@@@@@@@@", "Descrição do NCM");
			this.repositoryN.save(ncm);
		});

		assertThat((exception.getMessage().contains("interpolatedMessage='{0} deve conter apenas dígitos.'"))
				&& exception.getMessage().contains("interpolatedMessage='{0} deve ter o tamanho entre 8 e 8.'"));
	}

	@Test
	public void saveNCMWithSpecialCharactersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = new NCM("12345678", "@@@@@@@@@@");
			this.repositoryN.save(ncm);
		});

		assertThat((exception.getMessage().contains("interpolatedMessage='{0} deve conter apenas letras.'"))
				&& exception.getMessage().contains("interpolatedMessage='{0} deve ter o tamanho entre 8 e 8.'"));
	}

	@Test
	public void saveNCMWithTooLongNumberShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = new NCM("123456789", "Descrição do NCM");
			this.repositoryN.save(ncm);
		});

		assertThat((exception.getMessage().contains("interpolatedMessage='{0} deve conter apenas dígitos.'"))
				&& exception.getMessage().contains("interpolatedMessage='{0} deve ter o tamanho entre 8 e 8.'"));
	}

	@Test
	public void saveNCMWithTooShortNumberShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = new NCM("1234567", "Descrição do NCM");
			this.repositoryN.save(ncm);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 8 e 8.'");
	}

	@Test
	public void saveNCMWithTooLongDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = new NCM("12345678", "Descrição do NCMDescrição do NCMDescrição do NCMDescrição do NCM"
					+ "Descrição do NCMDescrição do NCMDescrição do NCMDescrição do NCMDescrição do NCMDescrição do NCM"
					+ "Descrição do NCMDescrição do NCMDescrição do NCMDescrição do NCMDescrição do NCMDescrição do NCM"
					+ "Descrição do NCMDescrição do NCMDescrição do NCMDescrição do NCMDescrição do NCMDescrição do NCM");
			this.repositoryN.save(ncm);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 0 e 200.'");
	}
}