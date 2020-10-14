package com.orcaolineapi.repository.orcamento;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;

import com.orcaolineapi.modelo.orcamento.MapaColeta;
import com.orcaolineapi.modelo.orcamento.Orcamento;
import com.orcaolineapi.modelo.orcamento.Status;
import com.orcaolineapi.modelo.usuario.ModalidadeTipoUsuario;
import com.orcaolineapi.modelo.usuario.TipoUsuario;
import com.orcaolineapi.modelo.usuario.Usuario;
import com.orcaolineapi.repository.usuario.TipoUsuarioRepository;
import com.orcaolineapi.repository.usuario.UsuarioRepository;

@DataJpaTest
@ActiveProfiles("test")

@AutoConfigureTestDatabase(replace = Replace.NONE)
class OrcamentoRepositoryTest {

	private @Autowired OrcamentoRepository repositoryOrc;
	
	/* MAPACOLETA REPOSITORY */
	
	private @Autowired MapaColetaRepository repositoryMP;

	/* USUARIO REPOSITORY */
			
	private @Autowired UsuarioRepository repositoryU;

	private @Autowired TipoUsuarioRepository repositoryT;
		
	/* @NOTNULL COMPRADOR */
	
	public TipoUsuario validTipoUsuarioFornecedor() {
		TipoUsuario tip = new TipoUsuario("Nome do TipoUsuarioForn", "Descricao do TipoUsuario", ModalidadeTipoUsuario.INTERNO);
		this.repositoryT.save(tip);
		return tip;
	}
	
	public Usuario validUsuarioFornecedor() {
		TipoUsuario tip = validTipoUsuarioFornecedor();
		Usuario usu = new Usuario("usuarioforn.usuario@gmail.com", "123Usuario@", "12345678910111",
				"Razao Social do Usuario", "Nome fantasia do Usuario", tip);
		this.repositoryU.save(usu);
		return usu;
	}
	
	public TipoUsuario validTipoUsuarioComprador() {
		TipoUsuario tip = new TipoUsuario("Nome do TipoUsuarioComp", "Descricao do TipoUsuario", ModalidadeTipoUsuario.INTERNO);
		this.repositoryT.save(tip);
		return tip;
	}
	
	public Usuario validUsuarioComprador() {
		TipoUsuario tip = validTipoUsuarioComprador();
		Usuario usu = new Usuario("usuariocomp.usuario@gmail.com", "123Usuario@", "12345678910112",
				"Razao Social do Usuario", "Nome fantasia do Usuario", tip);
		this.repositoryU.save(usu);
		return usu;
	}
	
	public Status validStatus() {
		Status sta = new Status(1L);
		return sta;
	}
	
	public MapaColeta validMapaColeta(){
		LocalDate dataRegistro = LocalDate.now();
		LocalDate dataEncerramento = null;

		Usuario comp = validUsuarioComprador();
		Status sta = validStatus();
		
		MapaColeta map = new MapaColeta(dataRegistro, dataEncerramento, "Descricao do MapaColeta" , comp, sta);
		this.repositoryMP.save(map);
		return map;
	}
	
	@Test
	public void saveOrcamentoWithNotNullIdShouldThrowsNoneException() {
		assertDoesNotThrow(() -> {
			
			LocalDate dataRegistro = LocalDate.now();
			LocalDate dataEnvio = null;
			
			Usuario forn = validUsuarioFornecedor();
			Status sta = validStatus();
			MapaColeta mapa = validMapaColeta();
			
			Orcamento orc = new Orcamento(dataRegistro, dataEnvio, forn, sta, mapa, false);
			this.repositoryOrc.save(orc);
		
			assertThat(orc.getId()).isNotNull();
		});
	}	
	
	@Test
	public void saveOrcamentoNullShouldThrowsInvalidDataAccessApiUsageException() {

		Throwable exception = assertThrows(InvalidDataAccessApiUsageException.class, () -> {
			
			Orcamento orc = null;
			this.repositoryOrc.save(orc);
		});

		assertEquals(
				"Target object must not be null; nested exception is java.lang.IllegalArgumentException: Target object must not be null",
				exception.getMessage());
	}
	
	@Test
	public void saveOrcamentoWithNullUsuarioFornecedorShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			LocalDate dataRegistro = LocalDate.now();
			LocalDate dataEnvio = null;
			
			Usuario forn = null;
			Status sta = Status.ATIVO;
			MapaColeta mapa = validMapaColeta();
			
			Orcamento orc = new Orcamento(dataRegistro, dataEnvio, forn, sta, mapa, false);
			this.repositoryOrc.save(orc);
			
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} é obrigatório(a).'"));
	}
	
	@Test
	public void saveOrcamentoWithNullStatusShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			LocalDate dataRegistro = LocalDate.now();
			LocalDate dataEnvio = null;
			
			Usuario forn = validUsuarioFornecedor();
			Status sta = null;
			MapaColeta mapa = validMapaColeta();
			
			Orcamento orc = new Orcamento(dataRegistro, dataEnvio, forn, sta, mapa, false);
			this.repositoryOrc.save(orc);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} é obrigatório(a).'"));
	}
	
	@Test
	public void saveOrcamentoWithNullMapaColetaShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			LocalDate dataRegistro = LocalDate.now();
			LocalDate dataEnvio = null;
			
			Usuario forn = validUsuarioFornecedor();
			Status sta = validStatus();
			MapaColeta mapa = null;
			
			Orcamento orc = new Orcamento(dataRegistro, dataEnvio, forn, sta, mapa, false);
			this.repositoryOrc.save(orc);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} é obrigatório(a).'"));
	}
}
