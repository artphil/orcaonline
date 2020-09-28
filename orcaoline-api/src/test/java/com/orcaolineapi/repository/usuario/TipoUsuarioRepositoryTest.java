package com.orcaolineapi.repository.usuario;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.orcaolineapi.modelo.usuario.Permissao;
import com.orcaolineapi.modelo.usuario.TipoUsuario;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TipoUsuarioRepositoryTest {

	private @Autowired TipoUsuarioRepository repositoryT;

	private @Autowired PermissaoRepository repositoryP;

	public List<Permissao> validPermissao() {
			List<Permissao> permissoes = null;
			return permissoes;
	}

	@Test
	public void saveTipoUsuarioWithNotNullIdShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			List<Permissao> permissoes = validPermissao();

			TipoUsuario tip = new TipoUsuario("Nome do TipoUsuario", "Descricao do TipoUsuario", permissoes);
			this.repositoryT.save(tip);
			assertThat(tip.getId()).isNotNull();
		});

	}

	@Test
	public void saveTipoUsuarioNullShouldThrowsInvalidDataAccessApiUsageException() {

		Throwable exception = assertThrows(InvalidDataAccessApiUsageException.class, () -> {
			TipoUsuario tip = null;
			this.repositoryT.save(tip);
		});

		assertEquals(
				"Target object must not be null; nested exception is java.lang.IllegalArgumentException: Target object must not be null",
				exception.getMessage());
	}

	@Test
	public void saveTipoUsuarioWithNullPermissaoShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			List<Permissao> permissoes = null;

			TipoUsuario tip = new TipoUsuario("Nome do TipoUsuario", "Descricao do TipoUsuario", permissoes);
			this.repositoryT.save(tip);
			assertThat(tip.getId()).isNotNull();
		});
	}

	@Test
	public void saveTipoUsuarioWithInvalidIdPermissaoShouldThrowsDataIntegrityViolationException() {

		Throwable exception = assertThrows(DataIntegrityViolationException.class, () -> {

			List<Permissao> permissoes = new ArrayList<Permissao>();
			Permissao per = new Permissao("Nome da Permissao", "Descricao da Permissao");
			this.repositoryP.save(per);
			per.setId(Long.valueOf(999999999));
			permissoes.add(per);

			// falta codigo aqui para mudar o id para um invalido
			TipoUsuario tip = new TipoUsuario("Nome do TipoUsuario", "Descricao do TipoUsuario", permissoes);
			this.repositoryT.save(tip);

		});

		assertEquals(
				"could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
				exception.getMessage());
	}

	@Test
	public void saveTipoUsuarioWithBlankNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			List<Permissao> permissoes = validPermissao();

			TipoUsuario tip = new TipoUsuario("", "Descrição do TipoUsuario", permissoes);
			this.repositoryT.save(tip);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 150.'"));
	}

	@Test
	public void saveTipoUsuarioWithBlankDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			List<Permissao> permissoes = validPermissao();

			TipoUsuario tip = new TipoUsuario("Nome do TipoUsuario", "", permissoes);
			this.repositoryT.save(tip);
		});

		assertThat((exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'")
				&& (exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 200.'"));
	}

	@Test
	public void saveTipoUsuarioWithNumbersInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			List<Permissao> permissoes = validPermissao();

			TipoUsuario tip = new TipoUsuario("123456789", "Descrição do TipoUsuario", permissoes);
			this.repositoryT.save(tip);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}

	@Test
	public void saveTipoUsuarioWithNumbersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			List<Permissao> permissoes = validPermissao();

			TipoUsuario tip = new TipoUsuario("Nome da Descrição", "123456789", permissoes);
			this.repositoryT.save(tip);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");

	}

	@Test
	public void saveTipoUsuarioWithBlankSpacesInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			List<Permissao> permissoes = validPermissao();

			TipoUsuario tip = new TipoUsuario("        ", "Descrição do TipoUsuario", permissoes);
			this.repositoryT.save(tip);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} não pode estar em branco(a).'");
	}

	@Test
	public void saveTipoUsuarioWithSpecialCharactersInNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			List<Permissao> permissoes = validPermissao();

			TipoUsuario tip = new TipoUsuario("@@@@@@@", "Descrição do TipoUsuario", permissoes);
			this.repositoryT.save(tip);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}

	@Test
	public void saveTipoUsuarioWithSpecialCharactersInDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			List<Permissao> permissoes = validPermissao();

			TipoUsuario tip = new TipoUsuario("Nome do TipoUsuario", "@@@@@@@", permissoes);
			this.repositoryT.save(tip);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve conter apenas letras.'");
	}

	@Test
	public void saveTipoUsuarioWithTooLongNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			List<Permissao> permissoes = validPermissao();

			TipoUsuario tip = new TipoUsuario(
					"Nome do TipoUsuarioNome do TipoUsuarioNome do TipoUsuarioNome do TipoUsuarioNome do TipoUsuarioNome do TipoUsuario"
							+ "Nome do TipoUsuarioNome do TipoUsuarioNome do TipoUsuarioNome do TipoUsuarioNome do TipoUsuario",
					"Descrição do TipoUsuario", permissoes);
			this.repositoryT.save(tip);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 200.'");
	}

	@Test
	public void saveTipoUsuarioWithTooShortNameShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			List<Permissao> permissoes = validPermissao();

			TipoUsuario tip = new TipoUsuario("Nome", "Descrição do TipoUsuario", permissoes);
			this.repositoryT.save(tip);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 200.'");
	}

	@Test
	public void saveTipoUsuarioWithTooLongDescriptionShouldThrowsConstraintViolationException() {

		Throwable exception = assertThrows(ConstraintViolationException.class, () -> {
			List<Permissao> permissoes = validPermissao();

			TipoUsuario tip = new TipoUsuario("Nome do TipoUsuario",
					"Descrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuario"
							+ "Descrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuario"
							+ "Descrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuario"
							+ "Descrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuarioDescrição do TipoUsuario",
					permissoes);
			this.repositoryT.save(tip);
		});

		assertThat(exception.getMessage()).contains("interpolatedMessage='{0} deve ter o tamanho entre 5 e 200.'");
	}
}