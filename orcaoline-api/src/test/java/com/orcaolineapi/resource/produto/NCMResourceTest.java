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

import com.orcaolineapi.modelo.produto.NCM;
import com.orcaolineapi.repository.produto.NCMRepository;
import com.orcaolineapi.service.produto.NCMService;

import io.restassured.http.ContentType;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@SpringBootTest
@AutoConfigureMockMvc
public class NCMResourceTest {

	private @Autowired NCMResource resource;

	private @MockBean NCMRepository repository;

	private @MockBean NCMService service;

	private @MockBean HttpServletResponse response;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.resource);
	}

	// nomeDoMetodo / resultadoEsperado / emQueSituação
	@Test()
	public void getById_Sucesso_BuscarUmRecursoExistente() {

		NCM ncm = new NCM("Nome", "descricao");
		ncm.setId(1L);

		when(this.repository.findById(1L)).thenReturn(Optional.of(ncm));

		given().accept(ContentType.JSON).when().get("/ncms/{id}", 1L).then().statusCode(HttpStatus.OK.value());

	}

//	@Test
	public void getById_Falha_BuscarUmRecursoInexistente() {
		when(this.repository.findById(1L)).thenReturn(null);

		given().accept(ContentType.JSON).when().get("/ncms/{id}", 140L).then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

}
