package com.orcaolineapi.repository.orcamento;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.orcaolineapi.modelo.orcamento.ItemMapa;
import com.orcaolineapi.modelo.orcamento.MapaColeta;
import com.orcaolineapi.modelo.orcamento.Orcamento;
import com.orcaolineapi.modelo.orcamento.Status;
import com.orcaolineapi.modelo.orcamento.UnidadeMedida;
import com.orcaolineapi.modelo.produto.Brick;
import com.orcaolineapi.modelo.produto.Classe;
import com.orcaolineapi.modelo.produto.Familia;
import com.orcaolineapi.modelo.produto.GTIN_EAN;
import com.orcaolineapi.modelo.produto.NCM;
import com.orcaolineapi.modelo.produto.Produto;
import com.orcaolineapi.modelo.produto.Segmento;
import com.orcaolineapi.modelo.usuario.Permissao;
import com.orcaolineapi.modelo.usuario.TipoUsuario;
import com.orcaolineapi.modelo.usuario.Usuario;
import com.orcaolineapi.repository.produto.BrickRepository;
import com.orcaolineapi.repository.produto.ClasseRepository;
import com.orcaolineapi.repository.produto.FamiliaRepository;
import com.orcaolineapi.repository.produto.GTIN_EANRepository;
import com.orcaolineapi.repository.produto.NCMRepository;
import com.orcaolineapi.repository.produto.ProdutoRepository;
import com.orcaolineapi.repository.produto.SegmentoRepository;
import com.orcaolineapi.repository.usuario.PermissaoRepository;
import com.orcaolineapi.repository.usuario.TipoUsuarioRepository;
import com.orcaolineapi.repository.usuario.UsuarioRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ItemMapaRepositoryTest {

	private @Autowired ItemMapaRepository repositoryI;
	
	/* MAPACOLETA REPOSITORY */
	
	private @Autowired MapaColetaRepository repositoryM;
	
	private @Autowired UsuarioRepository repositoryU;

	private @Autowired TipoUsuarioRepository repositoryT;

	private @Autowired PermissaoRepository repositoryPe;
		
	/* PRODUTO + BRICK REPOSITORY */
	
	private @Autowired BrickRepository repositoryBri;

	private @Autowired ClasseRepository repositoryCla;

	private @Autowired SegmentoRepository repositorySeg;

	private @Autowired FamiliaRepository repositoryFam;
		
	private @Autowired NCMRepository repositoryNcm;

	private @Autowired GTIN_EANRepository repositoryGti;

	private @Autowired ProdutoRepository repositoryPro;
	
	/* @NOTNULL UNIDADE MEDIDA */
	
	public UnidadeMedida validUnidadeMedida() {
		UnidadeMedida unid = UnidadeMedida.valueOf("KILO");
		return unid;
	}
	
	/* FIM DO @NOTNULL UNIDADE MEDIDA */

	/* @NOTNULL MAPA COLETA */
		
	public List<Permissao> validPermissao() {
		List<Permissao> permissoes = null;
		return permissoes;
	}
	
	public TipoUsuario validTipoUsuario() {
		List<Permissao> permissoes = validPermissao();
		TipoUsuario tip = new TipoUsuario("Nome do TipoUsuario", "Descricao do TipoUsuario", permissoes);
		this.repositoryT.save(tip);
		return tip;
	}
	
	public Usuario validUsuario() {
		TipoUsuario tip = validTipoUsuario();
		Usuario usu = new Usuario("usuario.usuario@gmail.com", "12345678Usuario@", "12345678910111",
				"Razao Social do Usuario", "Nome fantasia do Usuario", tip);
		this.repositoryU.save(usu);
		return usu;
	}
	
	public Status validStatus() {
		Status sta = null;		
		// falta recuperar o status do bd aqui
		return sta;
	}
	
	public MapaColeta validMapaColeta() {		
		LocalDate dataRegistro = LocalDate.now();
		Usuario comp = validUsuario();
		Status sta = validStatus();
		List<ItemMapa> itens = null;
		List<Orcamento> orcamento = null;
		
		MapaColeta map = new MapaColeta(dataRegistro, comp, sta, itens, orcamento);
		this.repositoryM.save(map);
		return map;
	}
	
	/* FIM DO @NOTNULL MAPA COLETA */
	
	/* PRODUTO + BRICK */
	
	public Segmento validSegmento() {
		Segmento seg = new Segmento("Nome do Segmento", "Descricao do Segmento");
		this.repositorySeg.save(seg);
		return seg;
	}

	public Familia validFamilia() {
		Segmento seg = validSegmento();
		Familia fam = new Familia("Nome da Familia", "Descricao da Familia", seg);
		this.repositoryFam.save(fam);
		return fam;
	}

	public Classe validClasse() {
		Familia fam = validFamilia();

		Classe cla = new Classe("Nome da Classe", "Descricao da Classe", fam);
		this.repositoryCla.save(cla);
		return cla;
	}
	
	public Brick validBrick() {
		Classe cla = validClasse();

		Brick bri = new Brick("Nome do Brick", "Descricao do Brick", cla);
		this.repositoryBri.save(bri);
		return bri;
	}
	
	public GTIN_EAN validGTIN_EAN() {
		Brick bri = validBrick();

		GTIN_EAN gte = new GTIN_EAN(BigInteger.valueOf(12345678), bri);
		this.repositoryGti.save(gte);
		return gte;
	}

	public NCM validNCM() {
		NCM ncm = new NCM("12345678", "Descrição do NCM");
		this.repositoryNcm.save(ncm);
		return ncm;
	}
	
	public Produto validProduto() {
		NCM ncm = validNCM();
		GTIN_EAN gte = validGTIN_EAN();

		Produto pro = new Produto("Nome do Produto", "Descricao do Produto", ncm, gte);
		this.repositoryPro.save(pro);
		return pro;
	}
	
	/* FIM DO PRODUTO + BRICK */

	@Test
	public void saveItemMapaWithNotNullIdShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			MapaColeta map = validMapaColeta();
			Brick bri = validBrick();
			Produto prod = validProduto();
			UnidadeMedida unid = validUnidadeMedida();
			
			ItemMapa itemM = new ItemMapa(0.9, unid, map, bri, prod);
			this.repositoryI.save(itemM);
			assertThat(itemM.getId()).isNotNull();
		});

	}
	
	@Test
	public void saveItemMapaWithBlankMarcaShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			MapaColeta map = validMapaColeta();
			Brick bri = validBrick();
			Produto prod = validProduto();
			UnidadeMedida unid = validUnidadeMedida();
			
			ItemMapa itemM = new ItemMapa(0.9, unid, map, bri, prod);
			this.repositoryI.save(itemM);
			assertThat(itemM.getId()).isNotNull();
		});
	}

	@Test
	public void saveItemMapaNullShouldThrowsInvalidDataAccessApiUsageException() {

		Throwable exception = assertThrows(InvalidDataAccessApiUsageException.class, () -> {
			
			ItemMapa itemM = null;
			this.repositoryI.save(itemM);
		});

		assertEquals(
				"Target object must not be null; nested exception is java.lang.IllegalArgumentException: Target object must not be null",
				exception.getMessage());
	}

	@Test
	public void saveItemMapaWithNullMapaColetaShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			
			MapaColeta map = null;
			Brick bri = validBrick();
			Produto prod = validProduto();
			UnidadeMedida unid = validUnidadeMedida();
			
			ItemMapa itemM = new ItemMapa(0.9, unid, map, bri, prod);
			
			this.repositoryI.save(itemM);

		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} é obrigatório(a).'"));
	}
	
	@Test
	public void saveItemMapaWithNullBrickShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			
			MapaColeta map = validMapaColeta();
			Brick bri = null;
			Produto prod = validProduto();
			UnidadeMedida unid = validUnidadeMedida();
			
			ItemMapa itemM = new ItemMapa(0.9, unid, map, bri, prod);
			
			this.repositoryI.save(itemM);

		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} é obrigatório(a).'"));
	}
	
	@Test
	public void saveItemMapaWithNullProdutoShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			
			MapaColeta map = validMapaColeta();
			Brick bri = validBrick();
			Produto prod = null;
			UnidadeMedida unid = validUnidadeMedida();
			
			ItemMapa itemM = new ItemMapa(0.9, unid, map, bri, prod);
			
			this.repositoryI.save(itemM);

		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} é obrigatório(a).'"));
	}

	@Test
	public void saveItemMapaWithNullUnidadeMedidaShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			
			MapaColeta map = validMapaColeta();
			Brick bri = validBrick();
			Produto prod = validProduto();
			UnidadeMedida unid = null;
			
			ItemMapa itemM = new ItemMapa(0.9, unid, map, bri, prod);
			
			this.repositoryI.save(itemM);

		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} é obrigatório(a).'"));
	}
	
	@Test
	public void saveItemMapaWithInvalidIdMapaColetaShouldThrowsDataIntegrityViolationException() {

		Throwable exception = assertThrows(DataIntegrityViolationException.class, () -> {
			
			MapaColeta map = validMapaColeta();
			map.setId(Long.valueOf(999999999));
			Brick bri = validBrick();
			Produto prod = validProduto();
			UnidadeMedida unid = null;
			
			ItemMapa itemM = new ItemMapa(0.9, unid, map, bri, prod);
			
			this.repositoryI.save(itemM);

		});

		assertEquals(
				"could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
				exception.getMessage());
	}

	
	@Test
	public void saveItemMapaWithInvalidIdBrickShouldThrowsDataIntegrityViolationException() {

		Throwable exception = assertThrows(DataIntegrityViolationException.class, () -> {
			
			MapaColeta map = validMapaColeta();
			Brick bri = validBrick();
			bri.setId(Long.valueOf(999999999));
			Produto prod = validProduto();
			UnidadeMedida unid = null;
			
			ItemMapa itemM = new ItemMapa(0.9, unid, map, bri, prod);
			
			this.repositoryI.save(itemM);

		});

		assertEquals(
				"could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
				exception.getMessage());
	}
	
	@Test
	public void saveItemMapaWithInvalidIdProdutoShouldThrowsDataIntegrityViolationException() {

		Throwable exception = assertThrows(DataIntegrityViolationException.class, () -> {
			
			MapaColeta map = validMapaColeta();
			Brick bri = validBrick();
			Produto prod = validProduto();
			prod.setId(Long.valueOf(999999999));
			UnidadeMedida unid = null;
			
			ItemMapa itemM = new ItemMapa(0.9, unid, map, bri, prod);
			
			this.repositoryI.save(itemM);

		});

		assertEquals(
				"could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
				exception.getMessage());
	}
}
