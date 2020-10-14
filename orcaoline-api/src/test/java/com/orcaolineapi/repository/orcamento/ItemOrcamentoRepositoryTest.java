package com.orcaolineapi.repository.orcamento;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;
import java.time.LocalDate;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;

import com.orcaolineapi.modelo.orcamento.ItemMapa;
import com.orcaolineapi.modelo.orcamento.ItemOrcamento;
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
import com.orcaolineapi.modelo.usuario.ModalidadeTipoUsuario;
import com.orcaolineapi.modelo.usuario.TipoUsuario;
import com.orcaolineapi.modelo.usuario.Usuario;
import com.orcaolineapi.repository.produto.BrickRepository;
import com.orcaolineapi.repository.produto.ClasseRepository;
import com.orcaolineapi.repository.produto.FamiliaRepository;
import com.orcaolineapi.repository.produto.GTIN_EANRepository;
import com.orcaolineapi.repository.produto.NCMRepository;
import com.orcaolineapi.repository.produto.ProdutoRepository;
import com.orcaolineapi.repository.produto.SegmentoRepository;
import com.orcaolineapi.repository.usuario.TipoUsuarioRepository;
import com.orcaolineapi.repository.usuario.UsuarioRepository;

@DataJpaTest
@ActiveProfiles("test")

@AutoConfigureTestDatabase(replace = Replace.NONE)
class ItemOrcamentoRepositoryTest {
	
	private @Autowired ItemOrcamentoRepository repositoryIO;

	/* ORCAMENTO REPOSITORY */

	private @Autowired OrcamentoRepository repositoryO;

	/* ITEMMAPA REPOSITORY */
	
	private @Autowired ItemMapaRepository repositoryIM;
	
	private @Autowired MapaColetaRepository repositoryM;
	
	private @Autowired UsuarioRepository repositoryU;

	private @Autowired TipoUsuarioRepository repositoryT;

	/* PRODUTO REPOSITORY */
	
	private @Autowired BrickRepository repositoryBri;

	private @Autowired ClasseRepository repositoryCla;

	private @Autowired SegmentoRepository repositorySeg;

	private @Autowired FamiliaRepository repositoryFam;
		
	private @Autowired NCMRepository repositoryNcm;

	private @Autowired GTIN_EANRepository repositoryGti;

	private @Autowired ProdutoRepository repositoryPro;

	/* @NOTNULL UNIDADE MEDIDA */
	
	public UnidadeMedida validUnidadeMedida() {
		UnidadeMedida unid = UnidadeMedida.KILO;
		return unid;
	}
	
	/* @NOTNULL ORCAMENTO */
	
	public Usuario validUsuarioFornecedor() {
		TipoUsuario tip = validTipoUsuarioFornecedor();
		Usuario usu = new Usuario("usuarioforn.usuario@gmail.com", "123Usuario@", "13579111315171",
				"Razao Social do Usuario", "Nome fantasia do Usuario", tip);
		this.repositoryU.save(usu);
		return usu;
	}
	
	public TipoUsuario validTipoUsuarioFornecedor() {
		TipoUsuario tip = new TipoUsuario("Nome do TipoUsuarioForn", "Descricao do TipoUsuario", ModalidadeTipoUsuario.INTERNO);
		this.repositoryT.save(tip);
		return tip;
	}

	public Orcamento validOrcamento() {
		Usuario forn = validUsuarioFornecedor();
		Status sta = validStatus();
		MapaColeta map = validMapaColeta();
		Orcamento orc = new Orcamento(LocalDate.now(), null, forn, sta, map, false);

		this.repositoryO.save(orc);
		return orc;
	}
	
	/* @NOTNULL ITEMMAPA */

	public ItemMapa validItemMapa() {
		MapaColeta map = validMapaColeta();
		Produto prod = validProduto();
		Brick bri = prod.getGtin().getBrick();

		ItemMapa itemM = new ItemMapa(0.9, UnidadeMedida.KILO, map, bri, prod);
		this.repositoryIM.save(itemM);
		return itemM;
	}
	
	/* @NOTNULL MAPA COLETA */
	
	public TipoUsuario validTipoUsuarioComprador() {
		TipoUsuario tip = new TipoUsuario("Nome do TipoUsuario", "Descricao do TipoUsuario", ModalidadeTipoUsuario.INTERNO);
		this.repositoryT.save(tip);
		return tip;
	}
	
	public Usuario validUsuarioComprador() {
		TipoUsuario tip = validTipoUsuarioComprador();
		Usuario usu = new Usuario("usuariocomp.usuario@gmail.com", "123Usuario@", "12345678910111",
				"Razao Social do Usuario", "Nome fantasia do Usuario", tip);
		this.repositoryU.save(usu);
		return usu;
	}
	
	public Status validStatus() {
		Status sta = new Status(1L);
		return sta;
	}
	
	public MapaColeta validMapaColeta() {		
		LocalDate dataRegistro = LocalDate.now();
		LocalDate dataEncerramento = null;
		Usuario comp = validUsuarioComprador();
		Status sta = validStatus();
		
		MapaColeta map = new MapaColeta(dataRegistro, dataEncerramento, "Descricao do MapaColeta", comp, sta);
		this.repositoryM.save(map);
		return map;
	}
		
	/* PRODUTO + BRICK */
	
	public Segmento validSegmento() {
		Segmento seg = new Segmento("Nome do Segmentoo", "Descricao do Segmento");
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
	
	@Test
	public void saveItemOrcamentoWithNotNullIdShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			Orcamento orc = validOrcamento();
			Produto prod = validProduto();
			
			/* ITEM MAPA */
			LocalDate dataRegistro = LocalDate.now();
			LocalDate dataEncerramento = null;

			Status sta = validStatus();
			
			MapaColeta map = new MapaColeta(dataRegistro, dataEncerramento, "Descricao do MapaColeta", orc.getFornecedor(), sta);

			this.repositoryM.save(map);
			Brick bri = prod.getGtin().getBrick();

			ItemMapa itemM = new ItemMapa(0.9, UnidadeMedida.KILO, map, bri, prod);
			this.repositoryIM.save(itemM);
			
			/* FIM DO ITEM MAPA */
					
			ItemOrcamento itemO = new ItemOrcamento(0.9, 0.9, orc, itemM, prod);
			this.repositoryIO.save(itemO);
			assertThat(itemO.getId()).isNotNull();
		});
	}
	
	@Test
	public void saveItemOrcamentoNullShouldThrowsInvalidDataAccessApiUsageException() {

		Throwable exception = assertThrows(InvalidDataAccessApiUsageException.class, () -> {
			
			ItemOrcamento itemO = null;
			this.repositoryIO.save(itemO);
		});

		assertEquals(
				"Target object must not be null; nested exception is java.lang.IllegalArgumentException: Target object must not be null",
				exception.getMessage());
	}

	@Test
	public void saveItemOrcamentoWithNullOrcamentoShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Produto prod = validProduto();
			
			/* ITEM MAPA */
			LocalDate dataRegistro = LocalDate.now();
			LocalDate dataEncerramento = null;

			Status sta = validStatus();
			Usuario usuForn = validUsuarioFornecedor();
			
			MapaColeta map = new MapaColeta(dataRegistro, dataEncerramento, "Descricao do MapaColeta", usuForn, sta);

			this.repositoryM.save(map);
			Brick bri = prod.getGtin().getBrick();

			ItemMapa itemM = new ItemMapa(0.9, UnidadeMedida.KILO, map, bri, prod);
			this.repositoryIM.save(itemM);
			
			/* FIM DO ITEM MAPA */
					
			ItemOrcamento itemO = new ItemOrcamento(0.9, 0.9, null, itemM, prod);
			this.repositoryIO.save(itemO);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} é obrigatório(a).'"));
	}
	
	@Test
	public void saveItemOrcamentoWithNullItemMapaShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Orcamento orc = validOrcamento();
			Produto prod = validProduto();
					
			ItemOrcamento itemO = new ItemOrcamento(0.9, 0.9, orc, null, prod);
			this.repositoryIO.save(itemO);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} é obrigatório(a).'"));
	}
	
	@Test
	public void saveItemOrcamentoWithNullProdutoShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			Orcamento orc = validOrcamento();
			Produto prod = validProduto();
			
			/* ITEM MAPA */
			LocalDate dataRegistro = LocalDate.now();
			LocalDate dataEncerramento = null;

			Status sta = validStatus();
			
			MapaColeta map = new MapaColeta(dataRegistro, dataEncerramento, "Descricao do MapaColeta", orc.getFornecedor(), sta);

			this.repositoryM.save(map);
			Brick bri = prod.getGtin().getBrick();

			ItemMapa itemM = new ItemMapa(0.9, UnidadeMedida.KILO, map, bri, prod);
			this.repositoryIM.save(itemM);
			
			/* FIM DO ITEM MAPA */
					
			ItemOrcamento itemO = new ItemOrcamento(0.9, 0.9, orc, itemM, null);
			this.repositoryIO.save(itemO);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} é obrigatório(a).'"));
	}
}
