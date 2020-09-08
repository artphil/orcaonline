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
import com.orcaolineapi.modelo.produto.Brick;
import com.orcaolineapi.repository.produto.BrickRepository;
import com.orcaolineapi.repository.produto.ClasseRepository;
import com.orcaolineapi.repository.produto.FamiliaRepository;
import com.orcaolineapi.repository.produto.SegmentoRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class BrickRepositoryTest {

	private @Autowired BrickRepository repositoryB;
	
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
	
	public Classe validClasse() {
		Familia fam = validFamilia();
		
		Classe cla = new Classe("Nome da Classe", "Descricao da Classe", fam);
		this.repositoryC.save(cla);
		return cla;
	}

	@Test
	public void saveBrickWithNotNullIdShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			Classe cla = validClasse();
			
			Brick bri = new Brick("Nome do Brick", "Descricao do Brick", cla);
			this.repositoryB.save(bri);
			assertThat(cla.getId()).isNotNull();
		});
		
	}
	
	@Test
	public void saveBrickNullShouldThrowsInvalidDataAccessApiUsageException() {
		
		Throwable exception = assertThrows(InvalidDataAccessApiUsageException.class, () -> {			
			Brick bri = null;
			this.repositoryB.save(bri);
		});
		
		assertEquals("Target object must not be null; nested exception is java.lang.IllegalArgumentException: Target object must not be null", exception.getMessage());
	}
	
	@Test
	public void saveBrickWithNullBrickShouldThrowsConstraintViolationException() {
		
		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {	
			Classe cla = null;
			Brick bri = new Brick("Nome do Brick", "Descricao do Brick", cla);
			this.repositoryB.save(bri);
			
		});
		
		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} é obrigatório(a).'"));
	}
	
	@Test
	public void saveBrickWithInvalidIdClasseShouldThrowsDataIntegrityViolationException() {
		
		Throwable exception = assertThrows(DataIntegrityViolationException.class, () -> {	
			Classe cla = validClasse();
			cla.setId(Long.valueOf(999999999));
			//falta codigo aqui para mudar o id para um invalido
			Brick bri = new Brick("Nome do Brick", "Descricao do Brick", cla);
			this.repositoryB.save(bri);
			
		});
		
		assertEquals("could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement", exception.getMessage());
	}
	
	@Test
	public void saveBrickWithBlankNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Classe cla = validClasse();
			
			Brick bri = new Brick("", "Descrição do Brick", cla);
			this.repositoryB.save(bri);
		});
		
		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'") && (exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'") && (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'"));
	}

	@Test
	public void saveBrickWithBlankDescriptionShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			Classe cla = validClasse();
			
			Brick bri = new Brick("Nome do Brick", "", cla);
			this.repositoryB.save(bri);
		});

	}

	@Test
	public void saveBrickWithNumbersInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Classe cla = validClasse();
			
			Brick bri = new Brick("123456789", "Descrição do Brick", cla);
			this.repositoryB.save(bri);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}
	
	@Test
	public void saveBrickWithNumbersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Classe cla = validClasse();

			Brick bri = new Brick("Nome do Brick", "123456789", cla);
			this.repositoryB.save(bri);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");

	}
	
	@Test
	public void saveBrickWithBlanckSpacesInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Classe cla = validClasse();

			Brick bri = new Brick("        ", "Descrição da Classe", cla);
			this.repositoryB.save(bri);
		});
		
		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'");
	}
	
	@Test
	public void saveBrickWithSpecialCharactersInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Classe cla = validClasse();

			Brick bri = new Brick("@@@@@@@", "Descrição do Brick", cla);
			this.repositoryB.save(bri);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}
	
	@Test
	public void saveBrickWithSpecialCharactersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Classe cla = validClasse();
			
			Brick bri = new Brick("Nome do Brick", "@@@@@@@", cla);
			this.repositoryB.save(bri);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}
	
	@Test
	public void saveBrickWithTooLongNameShouldThrowsConstraintViolationException() {
		
		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Classe cla = validClasse();
			
			Brick bri = new Brick("Nome do BrickNome do BrickNome do BrickNome do BrickNome do BrickNome do Brick"+
					"Nome do BrickNome do BrickNome do BrickNome do BrickNome do Brick"+
					"Nome do BrickNome do BrickNome do BrickNome do BrickNome do Brick"+
					"Nome do BrickNome do BrickNome do BrickNome do BrickNome do Brick", "Descrição do Brick", cla);
			this.repositoryB.save(bri);
		});

	    assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'");
	}
	
	@Test
	public void saveBrickWithTooShortNameShouldThrowsConstraintViolationException() {
		
		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Classe cla = validClasse();
			
			Brick bri = new Brick("Nome", "Descrição do Brick", cla);
			this.repositoryB.save(bri);
		});

	    assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'");
	}
	
	@Test
	public void saveBrickWithTooLongDescriptionShouldThrowsConstraintViolationException() {
		
		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Classe cla = validClasse();

			Brick bri = new Brick("Nome da Classe", "Descrição do BrickDescrição do BrickDescrição do BrickDescrição do Brick"+
					"Descrição do BrickDescrição do BrickDescrição do BrickDescrição do BrickDescrição do BrickDescrição do Brick"+
					"Descrição do BrickDescrição do BrickDescrição do BrickDescrição do BrickDescrição do BrickDescrição do Brick"+
					"Descrição do BrickDescrição do BrickDescrição do BrickDescrição do BrickDescrição do BrickDescrição do Brick"+
					"Descrição do BrickDescrição do BrickDescrição do BrickDescrição do BrickDescrição do BrickDescrição do Brick"+
					"Descrição do BrickDescrição do BrickDescrição do BrickDescrição do Brick", cla);
			this.repositoryB.save(bri);
		});

	    assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 0 e 200.'");
	}
}