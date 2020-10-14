package com.orcaolineapi.repository.orcamento;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")

@AutoConfigureTestDatabase(replace = Replace.NONE)
public class StatusRepositoryTest {

	//private @Autowired Status repository;

	/*@Test
	public void saveSegmentoWithNotNullIdShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			Status sta = new Status(1L);
			this.repository.save(sta);
			assertThat(sta.getId()).isNotNull();
		});
	}

	@Test
	public void saveSegmentoNullShouldThrowsInvalidDataAccessApiUsageException() {

		Throwable exception = assertThrows(InvalidDataAccessApiUsageException.class, () -> {
			Segmento seg = null;
			this.repository.save(sta);
		});

		assertEquals(
				"Target object must not be null; nested exception is java.lang.IllegalArgumentException: Target object must not be null",
				exception.getMessage());
	}

	@Test
	public void saveSegmentoWithBlankNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Status sta = new Status("", "Descrição do Status");
			this.repository.save(sta);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'"));
	}

	@Test
	public void saveSegmentoWithBlankDescriptionShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			Status sta = new Status("Nome do Status", "");
			this.repository.save(sta);
		});

	}

	@Test
	public void saveSegmentoWithNumbersInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Status sta = new Status("123456789", "Descrição do Status");
			this.repository.save(sta);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}

	@Test
	public void saveSegmentoWithNumbersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Status sta = new Status("Nome da Descrição", "123456789");
			this.repository.save(sta);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");

	}

	@Test
	public void saveSegmentoWithBlankSpacesInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Status sta = new Status("        ", "Descrição do Status");
			this.repository.save(sta);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'");
	}

	@Test
	public void saveSegmentoWithSpecialCharactersInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Status sta = new Status("@@@@@@@", "Descrição do Status");
			this.repository.save(sta);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}

	@Test
	public void saveSegmentoWithSpecialCharactersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Status sta = new Status("Nome do Status", "@@@@@@@");
			this.repository.save(sta);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}

	@Test
	public void saveSegmentoWithTooLongNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Status sta = new Status(
					"Nome do StatusNome do StatusNome do StatusNome do StatusNome do StatusNome do Status"
							+ "Nome do StatusNome do StatusNome do StatusNome do Status",
					"Descrição do Status");
			this.repository.save(sta);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'");
	}

	@Test
	public void saveSegmentoWithTooShortNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Status sta = new Status("Nome", "Descrição do Status");
			this.repository.save(sta);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'");
	}

	@Test
	public void saveSegmentoWithTooLongDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Status sta = new Status("Nome do Status",
					"Descrição do StatusDescrição do StatusDescrição do StatusDescrição do Status"
							+ "Descrição do StatusDescrição do StatusDescrição do StatusDescrição do StatusDescrição do StatusDescrição do Status");
			this.repository.save(sta);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 0 e 200.'");
	}*/
}