package com.orcaolineapi.resource.produto;

import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.orcaolineapi.modelo.produto.Segmento;
import com.orcaolineapi.repository.produto.SegmentoRepository;
import com.orcaolineapi.service.produto.SegmentoService;

import io.restassured.http.ContentType;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SegmentoResourceTest {

	private @Autowired SegmentoResource resource;
	
	private @MockBean SegmentoRepository repository;
	
	private @MockBean SegmentoService service;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.resource);
	}
	
	//nomeDoMetodo / resultadoEsperado / emQueSituação 
	@Test
	public void teste() {
	
		Segmento seg = new Segmento("fdsfsa", "dsfsafas");
		seg.setId(1L);
		
		when(this.repository.findById(1L)).thenReturn(Optional.of(seg));
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/segmentos/{id}", 1L)
		.then()
			.statusCode(HttpStatus.OK.value());
		
	}
	
}