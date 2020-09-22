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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.orcaolineapi.modelo.usuario.Permissao;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PermissaoRepositoryTest {

	private @Autowired PermissaoRepository repository;

	@Test
	public void savePermissaoWithNotNullIdShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			Permissao per = new Permissao("Nome da Permissao", "Descricao da Permissao");
			this.repository.save(per);
			assertThat(per.getId()).isNotNull();
		});
	}
	
	@Test
	public void savePermissaoNullShouldThrowsInvalidDataAccessApiUsageException() {
		
		Throwable exception = assertThrows(InvalidDataAccessApiUsageException.class, () -> {
			Permissao per = null;
			this.repository.save(per);		
		});
		
		assertEquals("Target object must not be null; nested exception is java.lang.IllegalArgumentException: Target object must not be null", exception.getMessage());
	}

	@Test
	public void savePermissaoWithBlankNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Permissao per = new Permissao("", "Descrição da Permissao");
			this.repository.save(per);
		});
		
		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'") && (exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'") && (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'"));
	}

	@Test
	public void savePermissaoWithBlankDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Permissao per = new Permissao("Nome da Permissao", "");
			this.repository.save(per);
		});
		
		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'") && (exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'") && (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'"));
	}

	@Test
	public void savePermissaoWithNumbersInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Permissao per = new Permissao("123456789", "Descrição da Permissao");
			this.repository.save(per);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}
	
	@Test
	public void savePermissaoWithNumbersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Permissao per = new Permissao("Nome da Descrição", "123456789");
			this.repository.save(per);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");

	}
	
	@Test
	public void savePermissaoWithBlankSpacesInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Permissao per = new Permissao("        ", "Descrição da Permissao");
			this.repository.save(per);
		});
		
		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'");
	}
	
	@Test
	public void savePermissaoWithSpecialCharactersInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Permissao per = new Permissao("@@@@@@@", "Descrição da Permissao");
			this.repository.save(per);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}
	
	@Test
	public void savePermissaoWithSpecialCharactersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Permissao per = new Permissao("Nome da Permissao", "@@@@@@@");
			this.repository.save(per);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}
	
	@Test
	public void savePermissaoWithTooLongNameShouldThrowsConstraintViolationException() {
		
		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Permissao per = new Permissao("Nome da PermissaoNome da PermissaoNome da PermissaoNome da PermissaoNome da PermissaoNome da Permissao"+
					"Nome da PermissaoNome da PermissaoNome da PermissaoNome da PermissaoNome da PermissaoNome da PermissaoNome da PermissaoNome da Permissao"+ 
					"Nome da PermissaoNome da Permissao", "Descrição da Permissao");
			this.repository.save(per);
		});

	    assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 100.'");
	}
	
	@Test
	public void savePermissaoWithTooShortNameShouldThrowsConstraintViolationException() {
		
		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Permissao per = new Permissao("Nome", "Descrição da Permissao");
			this.repository.save(per);
		});

	    assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 100.'");
	}
	
	@Test
	public void savePermissaoWithTooLongDescriptionShouldThrowsConstraintViolationException() {
		
		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			com.orcaolineapi.modelo.usuario.Permissao per = new Permissao("Nome da Permissao", "Descrição da PermissaoDescrição da PermissaoDescrição da PermissaoDescrição da Permissao"+
					"Descrição da PermissaoDescrição da PermissaoDescrição da PermissaoDescrição da PermissaoDescrição da PermissaoDescrição da Permissao");
			this.repository.save(per);
		});

	    assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 200.'");
	}
}