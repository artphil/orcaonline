package com.orcaolineapi.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;

import com.orcaolineapi.modelo.produto.Classe;
import com.orcaolineapi.modelo.produto.Familia;
import com.orcaolineapi.modelo.produto.NCM;
import com.orcaolineapi.modelo.produto.Segmento;
import com.orcaolineapi.modelo.produto.Brick;
import com.orcaolineapi.repository.produto.BrickRepository;
import com.orcaolineapi.repository.produto.ClasseRepository;
import com.orcaolineapi.repository.produto.FamiliaRepository;
import com.orcaolineapi.repository.produto.NCMRepository;
import com.orcaolineapi.repository.produto.SegmentoRepository;
import com.orcaolineapi.repository.produto.BrickRepository;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class NCMRepositoryTest {
	
	private @Autowired NCMRepository repositoryN;
		
	@Test
	public void saveNCMWithNotNullIdShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			NCM ncm = new NCM("12345678");
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
		
		assertEquals("Target object must not be null; nested exception is java.lang.IllegalArgumentException: Target object must not be null", exception.getMessage());
	}
	
	@Test
	public void saveNCMNullNumberShouldThrowsDataIntegrityViolationException() {
		
		Throwable exception = assertThrows(DataIntegrityViolationException.class, () -> {			
			NCM ncm = new NCM(null);
			this.repositoryN.save(ncm);
		});
		
		assertEquals("Target object must not be null; nested exception is java.lang.IllegalArgumentException: Target object must not be null", exception.getMessage());
	}
	
	@Test
	public void saveNCMWithBlankSpacesInNumberShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = new NCM("        ");
			this.repositoryN.save(ncm);
		});
		
		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas dígitos.'");
	}
	
	@Test
	public void saveNCMWithEmptyNumberShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = new NCM("");
			this.repositoryN.save(ncm);
		});
		
		assertThat((exception.getMessage().contains("interpolatedMessage='{0} deve conter apenas letras.'")) && exception.getMessage().contains("interpolatedMessage='{0} deve ter o tamanho entre 8 e 8.'"));
	}
	
	@Test
	public void saveNCMWithStringInNumberShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = new NCM("Numero do NCM");
			this.repositoryN.save(ncm);
		});
		
		assertThat((exception.getMessage().contains("interpolatedMessage='{0} deve conter apenas letras.'")) && exception.getMessage().contains("interpolatedMessage='{0} deve ter o tamanho entre 8 e 8.'"));
	}
	
	@Test
	public void saveNCMWithSpecialCharactersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {			
			NCM ncm = new NCM("@@@@@@@@@@");
			this.repositoryN.save(ncm);
		});

		assertThat((exception.getMessage().contains("interpolatedMessage='{0} deve conter apenas letras.'")) && exception.getMessage().contains("interpolatedMessage='{0} deve ter o tamanho entre 8 e 8.'"));
	}
	
	@Test
	public void saveNCMWithTooLongNumberShouldThrowsConstraintViolationException() {
		
		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = new NCM("123456789");
			this.repositoryN.save(ncm);
		});

		assertThat((exception.getMessage().contains("interpolatedMessage='{0} deve conter apenas letras.'")) && exception.getMessage().contains("interpolatedMessage='{0} deve ter o tamanho entre 8 e 8.'"));
	}
	
	@Test
	public void saveNCMWithTooShortNumberShouldThrowsConstraintViolationException() {
		
		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = new NCM("1234567");
			this.repositoryN.save(ncm);
		});

	    assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 8 e 8.'");
	}
}