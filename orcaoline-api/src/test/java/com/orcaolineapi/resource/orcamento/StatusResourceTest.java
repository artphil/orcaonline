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

import com.orcaolineapi.modelo.orcamento.Status;
import com.orcaolineapi.repository.orcamento.StatusRepository;
import com.orcaolineapi.service.orcamento.StatusService;

import io.restassured.http.ContentType;

@SpringBootTest
@AutoConfigureMockMvc
class StatusResourceTest {

	private @Autowired StatusResource resource;

	private @MockBean StatusRepository repository;

	private @MockBean StatusService service;

	private @MockBean HttpServletResponse response;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.resource);
	}

	// nomeDoMetodo / resultadoEsperado / emQueSituação
	@Test()
	public void getById_Sucesso_BuscarUmRecursoExistente() {

		Status sta = new Status(1L);
		
		sta.setId(1L);

		when(this.repository.findById(1L)).thenReturn(Optional.of(sta));

		given().accept(ContentType.JSON).when().get("/status/{id}", 1L).then().statusCode(HttpStatus.OK.value());

	}

//	@Test
	public void getById_Falha_BuscarUmRecursoInexistente() {
		when(this.repository.findById(1L)).thenReturn(null);

		given().accept(ContentType.JSON).when().get("/status/{id}", 140L).then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

}
