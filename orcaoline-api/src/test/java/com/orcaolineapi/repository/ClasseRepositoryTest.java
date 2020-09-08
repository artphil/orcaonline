package com.orcaolineapi.repository;

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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.orcaolineapi.modelo.produto.Classe;
import com.orcaolineapi.modelo.produto.Familia;
import com.orcaolineapi.modelo.produto.Segmento;
import com.orcaolineapi.repository.produto.ClasseRepository;
import com.orcaolineapi.repository.produto.FamiliaRepository;
import com.orcaolineapi.repository.produto.SegmentoRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ClasseRepositoryTest {

	private @Autowired ClasseRepository repositoryC;
	
	private @Autowired SegmentoRepository repositoryS;
	
	private @Autowired FamiliaRepository repositoryF;

	
	public Segmento validSegmento() {
		Segmento seg = new Segmento("Nome do Segmento", "Descricao do Segmento");
		this.repositoryS.save(seg);
		return seg;
	}
	
	public Familia validFamilia() {
		Segmento seg = validSegmento();
		Familia fam = new Familia("Nome da Familia", "Descricao da Familia", seg);
		this.repositoryF.save(fam);
		return fam;
	}

	@Test
	public void saveClasseWithNotNullIdShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			Familia fam = validFamilia();
			
			Classe cla = new Classe("Nome da Classe", "Descricao da Classe", fam);
			this.repositoryC.save(cla);
			assertThat(cla.getId()).isNotNull();
		});
		
	}
	
	@Test
	public void saveClasseNullShouldThrowsInvalidDataAccessApiUsageException() {
		
		Throwable exception = assertThrows(InvalidDataAccessApiUsageException.class, () -> {			
			Classe cla = null;
			this.repositoryC.save(cla);
		});
		
		assertEquals("Target object must not be null; nested exception is java.lang.IllegalArgumentException: Target object must not be null", exception.getMessage());
	}
	
	@Test
	public void saveClasseWithNullSegmentoShouldThrowsConstraintViolationException() {
		
		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {	
			Familia fam = null;
			Classe cla = new Classe("Nome da Classe", "Descricao da Classe", fam);
			this.repositoryC.save(cla);
			
		});
		
		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} é obrigatório(a).'"));
	}
	
	@Test
	public void saveClasseWithInvalidIdSegmentoShouldThrowsDataIntegrityViolationException() {
		
		Throwable exception = assertThrows(DataIntegrityViolationException.class, () -> {	
			Familia fam = validFamilia();
			fam.setId(Long.valueOf(999999999));
			//falta codigo aqui para mudar o id para um invalido
			Classe cla = new Classe("Nome da Classe", "Descricao da Classe", fam);
			this.repositoryC.save(cla);
			
		});
		
		assertEquals("could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement", exception.getMessage());
	}
	
	@Test
	public void saveClasseWithBlankNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Familia fam = validFamilia();
			
			Classe cla = new Classe("", "Descrição da Classe", fam);
			this.repositoryC.save(cla);
		});
		
		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'") && (exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'") && (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'"));
	}

	@Test
	public void saveClasseWithBlankDescriptionShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			Familia fam = validFamilia();
			
			Classe cla = new Classe("Nome da Classe", "", fam);
			this.repositoryC.save(cla);
		});

	}

	@Test
	public void saveClasseWithNumbersInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Familia fam = validFamilia();
			
			Classe cla = new Classe("123456789", "Descrição da Classe", fam);
			this.repositoryC.save(cla);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}
	
	@Test
	public void saveClasseWithNumbersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Familia fam = validFamilia();

			Classe cla = new Classe("Nome da Descrição", "123456789", fam);
			this.repositoryC.save(cla);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");

	}
	
	@Test
	public void saveClasseWithBlankSpacesInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Familia fam = validFamilia();

			Classe cla = new Classe("        ", "Descrição da Classe", fam);
			this.repositoryC.save(cla);
		});
		
		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'");
	}
	
	@Test
	public void saveClasseWithSpecialCharactersInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Familia fam = validFamilia();

			Classe cla = new Classe("@@@@@@@", "Descrição da Classe", fam);
			this.repositoryC.save(cla);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}
	
	@Test
	public void saveClasseWithSpecialCharactersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Familia fam = validFamilia();

			Classe cla = new Classe("Nome da Classe", "@@@@@@@", fam);
			this.repositoryC.save(cla);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}
	
	@Test
	public void saveClasseWithTooLongNameShouldThrowsConstraintViolationException() {
		
		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Familia fam = validFamilia();

			Classe cla = new Classe("Nome da ClasseNome da ClasseNome da ClasseNome da ClasseNome da ClasseNome da Classe"+
					"Nome da ClasseNome da ClasseNome da ClasseNome da ClasseNome da Classe", "Descrição da Classe", fam);
			this.repositoryC.save(cla);
		});

	    assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'");
	}
	
	@Test
	public void saveClasseWithTooShortNameShouldThrowsConstraintViolationException() {
		
		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Familia fam = validFamilia();

			Classe cla = new Classe("Nome", "Descrição da Classe", fam);
			this.repositoryC.save(cla);
		});

	    assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'");
	}
	
	@Test
	public void saveClasseWithTooLongDescriptionShouldThrowsConstraintViolationException() {
		
		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Familia fam = validFamilia();

			Classe cla = new Classe("Nome da Classe", "Descrição da ClasseDescrição da ClasseDescrição da ClasseDescrição da Classe"+
					"Descrição da ClasseDescrição da ClasseDescrição da ClasseDescrição da ClasseDescrição da ClasseDescrição da Classe"+
					"Descrição da ClasseDescrição da ClasseDescrição da ClasseDescrição da ClasseDescrição da ClasseDescrição da Classe"+
					"Descrição da ClasseDescrição da ClasseDescrição da ClasseDescrição da Classe", fam);
			this.repositoryC.save(cla);
		});

	    assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 0 e 200.'");
	}
}