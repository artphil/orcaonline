package com.orcaolineapi.repository.produto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.orcaolineapi.modelo.produto.Brick;
import com.orcaolineapi.modelo.produto.Classe;
import com.orcaolineapi.modelo.produto.Familia;
import com.orcaolineapi.modelo.produto.GTIN_EAN;
import com.orcaolineapi.modelo.produto.Segmento;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class GTIN_EANRepositoryTest {

	private @Autowired BrickRepository repositoryB;
	
	private @Autowired ClasseRepository repositoryC;
	
	private @Autowired SegmentoRepository repositoryS;
	
	private @Autowired FamiliaRepository repositoryF;
	
	private @Autowired GTIN_EANRepository repositoryG;
		
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
	
	public Brick validBrick() {
		Classe cla = validClasse();
		
		Brick bri = new Brick("Nome do Brick", "Descricao do Brick", cla);
		this.repositoryB.save(bri);
		return bri;
	}

	@Test
	public void saveGTIN_EANWithNotNullIdShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			Brick bri = validBrick();
			
			GTIN_EAN gte = new GTIN_EAN(BigInteger.valueOf(12345678), bri);
			this.repositoryG.save(gte);
			assertThat(gte.getId()).isNotNull();
		});
		
	}
	
	@Test
	public void saveGTIN_EANNullShouldThrowsInvalidDataAccessApiUsageException() {
		
		Throwable exception = assertThrows(InvalidDataAccessApiUsageException.class, () -> {			
			GTIN_EAN gte = null;
			this.repositoryG.save(gte);
		});
		
		assertEquals("Target object must not be null; nested exception is java.lang.IllegalArgumentException: Target object must not be null", exception.getMessage());
	}
	
	@Test
	public void saveGTIN_EANWithNullBrickShouldThrowsConstraintViolationException() {
		
		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {	
			Brick bri = null;
			GTIN_EAN gte = new GTIN_EAN(BigInteger.valueOf(12345678), bri);
			this.repositoryG.save(gte);
			
		});
		
		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} é obrigatório(a).'"));
	}
	
	@Test
	public void saveGTIN_EANWithInvalidIdBrickShouldThrowsDataIntegrityViolationException() {
		
		Throwable exception = assertThrows(DataIntegrityViolationException.class, () -> {	
			Brick bri = validBrick();
			bri.setId(Long.valueOf(999999999));
			//falta codigo aqui para mudar o id para um invalido
			GTIN_EAN gte = new GTIN_EAN(BigInteger.valueOf(12345678), bri);
			this.repositoryG.save(gte);
			
		});
		
		assertEquals("could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement", exception.getMessage());
	}
	
	@Test
	public void saveGTIN_EANNullNNumberShouldThrowsInvalidDataAccessApiUsageException() {
		
		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {			
			Brick bri = validBrick();
			GTIN_EAN gte = new GTIN_EAN(null, bri);
			this.repositoryG.save(gte);
		});
		
		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} é obrigatório(a).'"));
	}
	
}