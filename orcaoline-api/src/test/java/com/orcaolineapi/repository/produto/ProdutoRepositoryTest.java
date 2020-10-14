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
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;

import com.orcaolineapi.modelo.produto.Brick;
import com.orcaolineapi.modelo.produto.Classe;
import com.orcaolineapi.modelo.produto.Familia;
import com.orcaolineapi.modelo.produto.GTIN_EAN;
import com.orcaolineapi.modelo.produto.NCM;
import com.orcaolineapi.modelo.produto.Produto;
import com.orcaolineapi.modelo.produto.Segmento;

@DataJpaTest
@ActiveProfiles("test")

@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProdutoRepositoryTest {

	private @Autowired BrickRepository repositoryB;

	private @Autowired ClasseRepository repositoryC;

	private @Autowired SegmentoRepository repositoryS;

	private @Autowired FamiliaRepository repositoryF;

	private @Autowired NCMRepository repositoryN;

	private @Autowired GTIN_EANRepository repositoryG;

	private @Autowired ProdutoRepository repositoryP;

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

	public GTIN_EAN validGTIN_EAN() {
		Brick bri = validBrick();

		GTIN_EAN gte = new GTIN_EAN(BigInteger.valueOf(12345678), bri);
		this.repositoryG.save(gte);
		return gte;
	}

	public NCM validNCM() {
		NCM ncm = new NCM("12345678", "Descrição do NCM");
		this.repositoryN.save(ncm);
		return ncm;
	}

	@Test
	public void saveProdutoWithNotNullIdShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			NCM ncm = validNCM();
			GTIN_EAN gte = validGTIN_EAN();

			Produto pro = new Produto("Nome do Produto", "Descricao do Produto", ncm, gte);
			this.repositoryP.save(pro);
			assertThat(pro.getId()).isNotNull();
		});

	}

	@Test
	public void saveProdutoNullShouldThrowsInvalidDataAccessApiUsageException() {

		Throwable exception = assertThrows(InvalidDataAccessApiUsageException.class, () -> {
			Produto pro = null;
			this.repositoryP.save(pro);
		});

		assertEquals(
				"Target object must not be null; nested exception is java.lang.IllegalArgumentException: Target object must not be null",
				exception.getMessage());
	}

	@Test
	public void saveProdutoWithNullNCMShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = null;
			GTIN_EAN gte = validGTIN_EAN();

			Produto pro = new Produto("Nome do Produto", "Descricao do Produto", ncm, gte);
			this.repositoryP.save(pro);

		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} é obrigatório(a).'"));
	}

	@Test
	public void saveProdutoWithNullGTIN_EANShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = validNCM();
			GTIN_EAN gte = null;

			Produto pro = new Produto("Nome do Produto", "Descricao do Produto", ncm, gte);
			this.repositoryP.save(pro);

		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} é obrigatório(a).'"));
	}

	@Test
	public void saveProdutoWithNullGTIN_EANNullNCMShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = null;
			GTIN_EAN gte = null;

			Produto pro = new Produto("Nome do Produto", "Descricao do Produto", ncm, gte);
			this.repositoryP.save(pro);

		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} é obrigatório(a).'"));
	}

	@Test
	public void saveProdutoWithInvalidIdGTIN_EANShouldThrowsDataIntegrityViolationException() {

		Throwable exception = assertThrows(DataIntegrityViolationException.class, () -> {
			NCM ncm = validNCM();
			GTIN_EAN gte = validGTIN_EAN();
			gte.setId(Long.valueOf(999999999));

			// falta codigo aqui para mudar o id para um invalido
			Produto pro = new Produto("Nome do Produto", "Descricao do Produto", ncm, gte);
			this.repositoryP.save(pro);

		});

		assertEquals(
				"could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
				exception.getMessage());
	}

	@Test
	public void saveProdutoWithBlankNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = validNCM();
			GTIN_EAN gte = validGTIN_EAN();

			Produto pro = new Produto("", "Descrição do Produto", ncm, gte);
			this.repositoryP.save(pro);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'"));
	}

	@Test
	public void saveProdutoWithBlankDescriptionShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			NCM ncm = validNCM();
			GTIN_EAN gte = validGTIN_EAN();

			Produto pro = new Produto("Nome do Produto", "", ncm, gte);
			this.repositoryP.save(pro);
		});

	}

	@Test
	public void saveProdutoWithNumbersInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = validNCM();
			GTIN_EAN gte = validGTIN_EAN();

			Produto pro = new Produto("123456789", "Descrição do Produto", ncm, gte);
			this.repositoryP.save(pro);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}

	@Test
	public void saveProdutoWithNumbersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = validNCM();
			GTIN_EAN gte = validGTIN_EAN();

			Produto pro = new Produto("Nome do Produto", "123456789", ncm, gte);
			this.repositoryP.save(pro);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");

	}

	@Test
	public void saveProdutoWithBlanckSpacesInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = validNCM();
			GTIN_EAN gte = validGTIN_EAN();

			Produto pro = new Produto("        ", "Descrição do Produto", ncm, gte);
			this.repositoryP.save(pro);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'");
	}

	@Test
	public void saveProdutoWithSpecialCharactersInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = validNCM();
			GTIN_EAN gte = validGTIN_EAN();

			Produto pro = new Produto("@@@@@@@", "Descrição do Produto", ncm, gte);
			this.repositoryP.save(pro);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}

	@Test
	public void saveProdutoWithSpecialCharactersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = validNCM();
			GTIN_EAN gte = validGTIN_EAN();

			Produto pro = new Produto("Nome do Produto", "@@@@@@@", ncm, gte);
			this.repositoryP.save(pro);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}

	@Test
	public void saveProdutoWithTooLongNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = validNCM();
			GTIN_EAN gte = validGTIN_EAN();

			Produto pro = new Produto(
					"Nome do ProdutoNome do ProdutoNome do ProdutoNome do ProdutoNome do ProdutoNome do Produto"
							+ "Nome do ProdutoNome do ProdutoNome do ProdutoNome do ProdutoNome do Produto"
							+ "Nome do ProdutoNome do ProdutoNome do ProdutoNome do ProdutoNome do Produto"
							+ "Nome do ProdutoNome do ProdutoNome do ProdutoNome do ProdutoNome do Produto",
					"Descrição do Produto", ncm, gte);
			this.repositoryP.save(pro);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'");
	}

	@Test
	public void saveProdutoWithTooShortNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = validNCM();
			GTIN_EAN gte = validGTIN_EAN();

			Produto pro = new Produto("Nome", "Descrição do Produto", ncm, gte);
			this.repositoryP.save(pro);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'");
	}

	@Test
	public void saveProdutoWithTooLongDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			NCM ncm = validNCM();
			GTIN_EAN gte = validGTIN_EAN();

			Produto pro = new Produto("Nome do Produto",
					"Descrição do ProdutoDescrição do ProdutoDescrição do ProdutoDescrição do Produto"
							+ "Descrição do ProdutoDescrição do ProdutoDescrição do ProdutoDescrição do ProdutoDescrição do ProdutoDescrição do Produto"
							+ "Descrição do ProdutoDescrição do ProdutoDescrição do ProdutoDescrição do ProdutoDescrição do ProdutoDescrição do Produto"
							+ "Descrição do ProdutoDescrição do ProdutoDescrição do ProdutoDescrição do ProdutoDescrição do ProdutoDescrição do Produto"
							+ "Descrição do ProdutoDescrição do ProdutoDescrição do ProdutoDescrição do ProdutoDescrição do ProdutoDescrição do Produto"
							+ "Descrição do ProdutoDescrição do ProdutoDescrição do ProdutoDescrição do Produto",
					ncm, gte);
			this.repositoryP.save(pro);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 0 e 200.'");
	}
}