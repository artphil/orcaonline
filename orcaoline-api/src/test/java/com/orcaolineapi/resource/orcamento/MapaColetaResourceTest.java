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

import com.orcaolineapi.modelo.orcamento.MapaColeta;
import com.orcaolineapi.repository.orcamento.MapaColetaRepository;
import com.orcaolineapi.service.orcamento.MapaColetaService;

import io.restassured.http.ContentType;

@SpringBootTest
@AutoConfigureMockMvc
class MapaColetaResourceTest {

	private @Autowired MapaColetaResource resource;

	private @MockBean MapaColetaRepository repository;

	private @MockBean MapaColetaService service;

	private @MockBean HttpServletResponse response;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.resource);
	}

	// nomeDoMetodo / resultadoEsperado / emQueSituação
	@Test()
	public void getById_Sucesso_BuscarUmRecursoExistente() {

		MapaColeta it = new MapaColeta(null, null, "Descricao", null, null);
		
		it.setId(1L);

		when(this.repository.findById(1L)).thenReturn(Optional.of(it));

		given().accept(ContentType.JSON).when().get("/mapas/{id}", 1L).then().statusCode(HttpStatus.OK.value());

	}

//	@Test
	public void getById_Falha_BuscarUmRecursoInexistente() {
		when(this.repository.findById(1L)).thenReturn(null);

		given().accept(ContentType.JSON).when().get("/mapas/{id}", 140L).then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

}
