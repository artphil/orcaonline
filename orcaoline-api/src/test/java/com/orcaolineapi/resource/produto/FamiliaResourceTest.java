package com.orcaolineapi.resource.produto;

import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.orcaolineapi.modelo.produto.Familia;
import com.orcaolineapi.repository.produto.FamiliaRepository;
import com.orcaolineapi.service.produto.FamiliaService;

import io.restassured.http.ContentType;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FamiliaResourceTest {

	private @Autowired FamiliaResource resource;

	private @MockBean FamiliaRepository repository;

	private @MockBean FamiliaService service;

	private @MockBean HttpServletResponse response;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.resource);
	}

	// nomeDoMetodo / resultadoEsperado / emQueSituação
	@Test()
	public void getById_Sucesso_BuscarUmRecursoExistente() {

		Familia fam = new Familia("Nome", "descricao", null);
		fam.setId(1L);

		when(this.repository.findById(1L)).thenReturn(Optional.of(fam));

		given().accept(ContentType.JSON).when().get("/familias/{id}", 1L).then().statusCode(HttpStatus.OK.value());

	}

//	@Test
	public void getById_Falha_BuscarUmRecursoInexistente() {
		when(this.repository.findById(1L)).thenReturn(null);

		given().accept(ContentType.JSON).when().get("/familias/{id}", 140L).then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

}
