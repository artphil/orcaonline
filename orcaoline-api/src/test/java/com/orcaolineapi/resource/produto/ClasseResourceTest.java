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

import com.orcaolineapi.modelo.produto.Classe;
import com.orcaolineapi.repository.produto.ClasseRepository;
import com.orcaolineapi.service.produto.ClasseService;

import io.restassured.http.ContentType;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClasseResourceTest {

	private @Autowired ClasseResource resource;

	private @MockBean ClasseRepository repository;

	private @MockBean ClasseService service;

	private @MockBean HttpServletResponse response;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.resource);
	}

	// nomeDoMetodo / resultadoEsperado / emQueSituação
	@Test()
	public void getById_Sucesso_BuscarUmRecursoExistente() {

		Classe cla = new Classe("Nome", "descricao", null);
		cla.setId(1L);

		when(this.repository.findById(1L)).thenReturn(Optional.of(cla));

		given().accept(ContentType.JSON).when().get("/classes/{id}", 1L).then().statusCode(HttpStatus.OK.value());

	}

//	@Test
	public void getById_Falha_BuscarUmRecursoInexistente() {
		when(this.repository.findById(1L)).thenReturn(null);

		given().accept(ContentType.JSON).when().get("/classes/{id}", 140L).then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

}
