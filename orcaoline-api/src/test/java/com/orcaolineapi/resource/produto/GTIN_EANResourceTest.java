package com.orcaolineapi.resource.produto;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.orcaolineapi.modelo.produto.GTIN_EAN;
import com.orcaolineapi.repository.produto.GTIN_EANRepository;
import com.orcaolineapi.service.produto.GTIN_EANService;

import io.restassured.http.ContentType;

@SpringBootTest
@AutoConfigureMockMvc
public class GTIN_EANResourceTest {

	private @Autowired GTIN_EANResource resource;

	private @MockBean GTIN_EANRepository repository;

	private @MockBean GTIN_EANService service;

	private @MockBean HttpServletResponse response;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.resource);
	}

	// nomeDoMetodo / resultadoEsperado / emQueSituação
	@Test()
	public void getById_Sucesso_BuscarUmRecursoExistente() {

		GTIN_EAN ge = new GTIN_EAN(BigInteger.valueOf(123), null);
		ge.setId(1L);

		when(this.repository.findById(1L)).thenReturn(Optional.of(ge));

		given().accept(ContentType.JSON).when().get("/gtins/{id}", 1L).then().statusCode(HttpStatus.OK.value());

	}

//	@Test
	public void getById_Falha_BuscarUmRecursoInexistente() {
		when(this.repository.findById(1L)).thenReturn(null);

		given().accept(ContentType.JSON).when().get("/gtins/{id}", 140L).then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

}
