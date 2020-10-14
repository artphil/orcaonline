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
import com.orcaolineapi.modelo.orcamento.Status;
import com.orcaolineapi.modelo.usuario.ModalidadeTipoUsuario;
import com.orcaolineapi.modelo.usuario.TipoUsuario;
import com.orcaolineapi.modelo.usuario.Usuario;
import com.orcaolineapi.repository.usuario.TipoUsuarioRepository;
import com.orcaolineapi.repository.usuario.UsuarioRepository;

@DataJpaTest
@ActiveProfiles("test")

@AutoConfigureTestDatabase(replace = Replace.NONE)
class MapaColetaRepositoryTest {

	private @Autowired MapaColetaRepository repositoryMP;

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
}