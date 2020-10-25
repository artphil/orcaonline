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

import com.orcaolineapi.modelo.orcamento.ItemOrcamento;
import com.orcaolineapi.repository.orcamento.ItemOrcamentoRepository;
import com.orcaolineapi.service.orcamento.ItemOrcamentoService;

import io.restassured.http.ContentType;

@SpringBootTest
@AutoConfigureMockMvc
class ItemOrcamentoResourceTest {

	private @Autowired ItemOrcamentoResource resource;

	private @MockBean ItemOrcamentoRepository repository;

	private @MockBean ItemOrcamentoService service;

	private @MockBean HttpServletResponse response;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.resource);
	}

	// nomeDoMetodo / resultadoEsperado / emQueSituação
	@Test()
	public void getById_Sucesso_BuscarUmRecursoExistente() {

		ItemOrcamento it = new ItemOrcamento(1.2, 1.2, null, null, null);
		
		it.setId(1L);

		when(this.repository.findById(1L)).thenReturn(Optional.of(it));

		given().accept(ContentType.JSON).when().get("/itemorcamentos/{id}", 1L).then().statusCode(HttpStatus.OK.value());

	}

//	@Test
	public void getById_Falha_BuscarUmRecursoInexistente() {
		when(this.repository.findById(1L)).thenReturn(null);

		given().accept(ContentType.JSON).when().get("/itemorcamentos/{id}", 140L).then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

}
