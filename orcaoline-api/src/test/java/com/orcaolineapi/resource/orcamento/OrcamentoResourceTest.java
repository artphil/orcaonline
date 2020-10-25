package com.orcaolineapi.resource.orcamento;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
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

import com.orcaolineapi.modelo.orcamento.Orcamento;
import com.orcaolineapi.repository.orcamento.OrcamentoRepository;
import com.orcaolineapi.service.orcamento.OrcamentoService;

import io.restassured.http.ContentType;

@SpringBootTest
@AutoConfigureMockMvc
class OrcamentoResourceTest {

	private @Autowired OrcamentoResource resource;

	private @MockBean OrcamentoRepository repository;

	private @MockBean OrcamentoService service;

	private @MockBean HttpServletResponse response;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.resource);
	}

	// nomeDoMetodo / resultadoEsperado / emQueSituação
	@Test()
	public void getById_Sucesso_BuscarUmRecursoExistente() {

		Orcamento orc = new Orcamento(null, null, null, null, null, false);
		
		orc.setId(1L);

		when(this.repository.findById(1L)).thenReturn(Optional.of(orc));

		given().accept(ContentType.JSON).when().get("/orcamentos/{id}", 1L).then().statusCode(HttpStatus.OK.value());

	}

//	@Test
	public void getById_Falha_BuscarUmRecursoInexistente() {
		when(this.repository.findById(1L)).thenReturn(null);

		given().accept(ContentType.JSON).when().get("/orcamentos/{id}", 140L).then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

}
