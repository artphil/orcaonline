package com.orcaolineapi.repository.orcamento;

import static org.assertj.core.api.Assertions.allOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
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

//@SpringBootTest
@DataJpaTest
@ActiveProfiles("test")

@AutoConfigureTestDatabase(replace = Replace.NONE)
class MapaColetaRepositoryTest {

	private @Autowired MapaColetaRepository repositoryMP;
	private @Autowired OrcamentoRepository repositoryOrc;

	/* USUARIO REPOSITORY */
		
	private @Autowired UsuarioRepository repositoryU;

	private @Autowired TipoUsuarioRepository repositoryT;

	/* @NOTNULL COMPRADOR */
	
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
	
	@Test
	public void saveMapaColetaWithNotNullIdShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			LocalDate dataRegistro = LocalDate.now();
			LocalDate dataEncerramento = null;

			Usuario comp = validUsuarioComprador();
			Status sta = validStatus();
			
			MapaColeta map = new MapaColeta(dataRegistro, dataEncerramento, "Descricao do MapaColeta" , comp, sta);
			this.repositoryMP.save(map);
			
			assertThat(map.getId()).isNotNull();
		});
	}
	
	@Test
	public void MapaColetaIsRunningWithEmAndamentoStatusShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			LocalDate dataRegistro = LocalDate.now();
			LocalDate dataEncerramento = null;

			Usuario comp = validUsuarioComprador();
			Status sta = Status.EM_ANDAMENTO;
			
			MapaColeta map = new MapaColeta(dataRegistro, dataEncerramento, "Descricao do MapaColeta" , comp, sta);
			this.repositoryMP.save(map);
			
			assertEquals(map.isRunning(), true);			
		});
	}
	
	@Test
	public void MapaColetaIsRunningWithAnotherStatusShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			LocalDate dataRegistro = LocalDate.now();
			LocalDate dataEncerramento = null;

			Usuario comp = validUsuarioComprador();
			Status sta = validStatus();
			
			MapaColeta map = new MapaColeta(dataRegistro, dataEncerramento, "Descricao do MapaColeta" , comp, sta);
			this.repositoryMP.save(map);
			
			assertEquals(map.isRunning(), false);			
		});
	}
	
	@Test
	public void saveMapaColetaWithUsedStatusShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			LocalDate dataRegistro = LocalDate.now();
			LocalDate dataEncerramento = null;

			Usuario comp = validUsuarioComprador();
			Status sta = validStatus();
			
			MapaColeta map = new MapaColeta(dataRegistro, dataEncerramento, "Descricao do MapaColeta", comp, sta);
			this.repositoryMP.save(map);
			
			List<Status> usedS = Usuario.usedStatus();			
			assertThat(usedS.contains(Status.ABERTO)&&usedS.contains(Status.EM_ANDAMENTO)&&usedS.contains(Status.FECHADO));
		});
	}
	
	@Test
	public void saveMapaColetaWithNotUsedStatusShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			LocalDate dataRegistro = LocalDate.now();
			LocalDate dataEncerramento = null;

			Usuario comp = validUsuarioComprador();
			Status sta = validStatus();
			
			MapaColeta map = new MapaColeta(dataRegistro, dataEncerramento, "Descricao do MapaColeta", comp, sta);
			this.repositoryMP.save(map);
			
			List<Status> usedS = Usuario.usedStatus();			
			assertThat(usedS.contains(Status.ATIVO)&&usedS.contains(Status.INATIVO)&&usedS.contains(Status.CANCELADO));
		});
	}
	
	@Test
	public void saveMapaColetaCriaNovoOrcamentoWithStatusEmAndamentoShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			LocalDate dataRegistro = LocalDate.now();
			LocalDate dataEncerramento = null;

			Usuario comp = validUsuarioComprador();
			Status sta = Status.EM_ANDAMENTO;
			
			MapaColeta map = new MapaColeta(dataRegistro, dataEncerramento, "Descricao do MapaColeta", comp, sta);
			this.repositoryMP.save(map);
			
			List<Orcamento> orcamentosAntes = map.getOrcamentos();
			
			Orcamento orc1 = map.criaNovoOrcamento();
			Orcamento orc2 = map.criaNovoOrcamento();
			Orcamento orc3 = map.criaNovoOrcamento();
			
			List<Orcamento> orcamentosDepois = map.getOrcamentos();
			
			/*this.repositoryMP.deleteById(map.getId());
			this.repositoryMP.save(map);*/
			
			assertThat(!orc1.equals(null)
					&& !orc2.equals(null)
					&& !orc3.equals(null)
					&& (orcamentosDepois.size() > orcamentosAntes.size())
					&& !map.getId().equals(null));
		});
	}
	
	@Test
	public void saveMapaColetaCriaNovoOrcamentoWithAnotherStatusShouldThrowsNullPointerException() {

		Throwable exception = assertThrows(NullPointerException.class, () -> {
			LocalDate dataRegistro = LocalDate.now();
			LocalDate dataEncerramento = LocalDate.now();

			Usuario comp = validUsuarioComprador();
			Status sta = validStatus();
			
			MapaColeta map = new MapaColeta(dataRegistro, dataEncerramento, "Descricao do MapaColeta", comp, sta);
			this.repositoryMP.save(map);
			
			List<Orcamento>  orcamentosAntes = map.getOrcamentos();
			
			Orcamento orc1 = map.criaNovoOrcamento();
			Orcamento orc2 = map.criaNovoOrcamento();
			Orcamento orc3 = map.criaNovoOrcamento();
			
			/*this.repositoryOrc.save(orc1);
			this.repositoryOrc.save(orc2);
			this.repositoryOrc.save(orc3);*/
			
			List<Orcamento> orcamentosDepois = map.getOrcamentos();
			
			//assertThat(orc1.getId()).isNotNull();
			
			/*this.repositoryMP.deleteById(map.getId());
			this.repositoryMP.save(map);*/
		});
	}
	
	@Test
	public void saveMapaColetaEncerraShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			LocalDate dataRegistro = LocalDate.now();
			LocalDate dataEncerramento = null;

			Usuario comp = validUsuarioComprador();
			Status sta = validStatus();
			
			MapaColeta map = new MapaColeta(dataRegistro, dataEncerramento, "Descricao do MapaColeta", comp, sta);
			this.repositoryMP.save(map);
			
			map.encerrar();
			
			/*this.repositoryMP.deleteById(map.getId());
			this.repositoryMP.save(map);*/
			
			assertThat(map.getDataEncerramento().equals(LocalDate.now()) && (map.getStatus()).equals(Status.FECHADO) && !map.getId().equals(null));
		});
	}
	
	@Test
	public void saveMapaColetaAprovarOrcamentoShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			LocalDate dataRegistro = LocalDate.now();
			LocalDate dataEncerramento = LocalDate.now();

			Usuario comp = validUsuarioComprador();
			Status sta = Status.EM_ANDAMENTO;
			
			MapaColeta map = new MapaColeta(dataRegistro, dataEncerramento, "Descricao do MapaColeta", comp, sta);
			this.repositoryMP.save(map);
			
			List<Orcamento>  orcamentosAntes = map.getOrcamentos();
			
			Orcamento orc1 = map.criaNovoOrcamento();
			Orcamento orc2 = map.criaNovoOrcamento();
			Orcamento orc3 = map.criaNovoOrcamento();
			
			/*this.repositoryOrc.save(orc1);
			this.repositoryOrc.save(orc2);
			this.repositoryOrc.save(orc3);*/
			
			List<Orcamento>  orcamentosDepois = map.getOrcamentos();
			
			map.aprovarOrcamento(orc1.getId());
			map.aprovarOrcamento(orc2.getId());
			map.aprovarOrcamento(orc3.getId());
			
			/*this.repositoryMP.deleteById(map.getId());
			this.repositoryMP.save(map);*/
			
			for(Orcamento orcamento : map.getOrcamentos()) {
				assertThat(orcamento.getAprovado().equals(true) 
						&& map.getDataEncerramento().equals(LocalDate.now()) 
						&& map.getStatus().equals(Status.FECHADO) 
						&& !map.getId().equals(null)
						&& (orcamentosDepois.size() > orcamentosAntes.size()));
			}		
		});
	}
	
	@Test
	public void saveMapaColetaNullShouldThrowsInvalidDataAccessApiUsageException() {

		Throwable exception = assertThrows(InvalidDataAccessApiUsageException.class, () -> {
			
			MapaColeta mapaC = null;
			this.repositoryMP.save(mapaC);
		});

		assertEquals(
				"Target object must not be null; nested exception is java.lang.IllegalArgumentException: Target object must not be null",
				exception.getMessage());
	}
	
	@Test
	public void saveMapaColetaWithTooLongDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {

			LocalDate dataRegistro = LocalDate.now();
			LocalDate dataEncerramento = null;

			Usuario comp = validUsuarioComprador();
			comp.setId(Long.valueOf(99999999));
			Status sta = validStatus();
			
			MapaColeta map = new MapaColeta(dataRegistro, dataEncerramento, "Descrição do MapaColetaDescrição do MapaColetaDescrição do MapaColetaDescrição do TipoUsuario"
					+ "Descrição do MapaColetaDescrição do MapaColetaDescrição do MapaColetaDescrição do MapaColetaDescrição do MapaColetaDescrição do TipoUsuario"
					+ "Descrição do MapaColetaDescrição do MapaColetaDescrição do MapaColetaDescrição do MapaColetaDescrição do MapaColetaDescrição do TipoUsuario"
					+ "Descrição do MapaColetaDescrição do MapaColetaDescrição do MapaColetaDescrição do MapaColeta" , comp, sta);
			this.repositoryMP.save(map);

		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 0 e 200.'");
	}

	@Test
	public void saveMapaColetaWithNullUsuarioCompradorShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			LocalDate dataRegistro = LocalDate.now();
			LocalDate dataEncerramento = null;

			Usuario comp = null;
			Status sta = validStatus();
			
			MapaColeta map = new MapaColeta(dataRegistro, dataEncerramento, "Descricao do MapaColeta" , comp, sta);
			this.repositoryMP.save(map);
			
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} é obrigatório(a).'"));
	}
	
	@Test
	public void saveMapaColetaWithNullStatusShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			LocalDate dataRegistro = LocalDate.now();
			LocalDate dataEncerramento = null;

			Usuario comp = validUsuarioComprador();
			Status sta = null;
			
			MapaColeta map = new MapaColeta(dataRegistro, dataEncerramento, "Descricao do MapaColeta" , comp, sta);
			this.repositoryMP.save(map);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} é obrigatório(a).'"));
	}
	
	@Test
	public void saveMapaColetaWithInvalidIdUsuarioCompradorShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(DataIntegrityViolationException.class, () -> {
			LocalDate dataRegistro = LocalDate.now();
			LocalDate dataEncerramento = null;

			Usuario comp = validUsuarioComprador();
			comp.setId(Long.valueOf(99999999));
			Status sta = validStatus();
			
			MapaColeta map = new MapaColeta(dataRegistro, dataEncerramento, "Descricao do MapaColeta" , comp, sta);
			this.repositoryMP.save(map);

		});

		assertEquals(
				"could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
				exception.getMessage());
	}

	@Test
	public void saveMapaColetaWithInvalidIdStatusShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(DataIntegrityViolationException.class, () -> {
			
			LocalDate dataRegistro = LocalDate.now();
			LocalDate dataEncerramento = null;

			Usuario comp = validUsuarioComprador();
			Status sta = validStatus();
			sta.setId(Long.valueOf(99999999));
			
			MapaColeta map = new MapaColeta(dataRegistro, dataEncerramento, "Descricao do MapaColeta" , comp, sta);
			this.repositoryMP.save(map);

		});

		assertEquals(
				"could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
				exception.getMessage());
	}
}