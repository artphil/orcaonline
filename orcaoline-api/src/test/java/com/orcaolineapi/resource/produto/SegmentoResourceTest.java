package com.orcaolineapi.resource.produto;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.NestedServletException;

import com.orcaolineapi.modelo.produto.Segmento;
import com.orcaolineapi.repository.produto.SegmentoRepository;
import com.orcaolineapi.service.produto.SegmentoService;

import io.restassured.http.ContentType;

@SpringBootTest
@AutoConfigureMockMvc

public class SegmentoResourceTest {
	
	private @Autowired SegmentoResource resource;
 
	private @MockBean SegmentoRepository repository;

	private @MockBean HttpServletResponse response;
	
	private @MockBean SegmentoService service;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.resource);
	}

	// nomeDoMetodo / resultadoEsperado / emQueSituação

	@Test
	public void postRecursos_Sucesso_InserirRecursosExistentes() {
						
		given().body("{ \"message\" : \"hello world\"}").contentType(ContentType.JSON).when().post("/segmentos").andReturn().then().statusCode(400)
		.expect(jsonPath("$[0].id", is(1)))
	    .expect(jsonPath("$[0].nome", is("Nome1")))
	    .expect(jsonPath("$[0].descricao", is("descricao1")));
	}
	
	@Test
	public void getRecursos_Sucesso_BuscarRecursosExistentes() throws Exception {

		Segmento seg1 = new Segmento("Nome1", "descricao1");
		Segmento seg2 = new Segmento("Nome2", "descricao2");
		Segmento seg3 = new Segmento("Nome3", "descricao3");
		Segmento seg4 = new Segmento("Nome4", "descricao4");
		
		seg1.setId(Long.valueOf(1));
		seg2.setId(Long.valueOf(2));
		seg3.setId(Long.valueOf(3));
		seg4.setId(Long.valueOf(4));
		
		when(this.repository.findAll()).thenReturn(Arrays.asList(seg1, seg2, seg3, seg4));
		
		given().accept(ContentType.JSON).when().get("/segmentos").andReturn().then().statusCode(HttpStatus.OK.value())
	    .expect(jsonPath("$[*]", hasSize(4)))
	    .expect(jsonPath("$[0].id", is(1)))
	    .expect(jsonPath("$[0].nome", is("Nome1")))
	    .expect(jsonPath("$[0].descricao", is("descricao1")))
	    .expect(jsonPath("$[1].id", is(2)))
	    .expect(jsonPath("$[1].nome", is("Nome2")))
	    .expect(jsonPath("$[1].descricao", is("descricao2")))
	    .expect(jsonPath("$[2].id", is(3)))
	    .expect(jsonPath("$[2].nome", is("Nome3")))
	    .expect(jsonPath("$[2].descricao", is("descricao3")))
	    .expect(jsonPath("$[3].id", is(4)))
	    .expect(jsonPath("$[3].nome", is("Nome4")))
	    .expect(jsonPath("$[3].descricao", is("descricao4")));
		
		verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
   }
	
	@Test
	public void getRecursos_Sucesso_BuscarRecursosInexistentes() {
		
		when(this.repository.findAll()).thenReturn(Collections.emptyList());

		given().accept(ContentType.JSON).when().get("/segmentos").then().statusCode(HttpStatus.OK.value());
		
		verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);

	}
	
	@Test
	public void getById_Sucesso_BuscarUmRecursoExistente() {

		Segmento seg = new Segmento("Nome", "descricao");
		seg.setId(1L);

		when(this.repository.findById(1L)).thenReturn(Optional.of(seg));

		given().accept(ContentType.JSON).when().get("/segmentos/{id}", 1L).then().statusCode(HttpStatus.OK.value())
		.expect(jsonPath("$.id", is(1)))
	    .expect(jsonPath("$.nome", is("Nome")))
	    .expect(jsonPath("$.descricao", is("descricao")));
		
		verify(repository, times(1)).findById(1L);
        verifyNoMoreInteractions(repository);
	}
	
	@Test
	public void updateById_Sucesso_BuscarUmRecursoExistente() {

		Segmento seg = new Segmento("Nome", "descricao");
		seg.setId(1L);

		when(this.repository.findById(1L)).thenReturn(Optional.of(seg));

		given().accept(ContentType.JSON).when().get("/segmentos/{id}", 1L).then().statusCode(HttpStatus.OK.value())
		.expect(jsonPath("$.id", is(1)))
	    .expect(jsonPath("$.nome", is("Nome")))
	    .expect(jsonPath("$.descricao", is("descricao")));
		
		verify(repository, times(1)).findById(1L);
        verifyNoMoreInteractions(repository);
	}

	@Test
	public void getById_NestedServletException_BuscarUmRecursoInexistente() {
        
		Throwable exception = assertThrows(NestedServletException.class, () -> {
			when(this.repository.findById(140L)).thenReturn(null);

			given().accept(ContentType.JSON).when().get("/segmentos/{id}", 140L).then()
					.statusCode(HttpStatus.NOT_FOUND.value());
			
			verify(repository, times(1)).findById(1L);
	        verifyNoMoreInteractions(repository);
		});
        
	}
	
	@Test
	public void deleteById_NestedServletException_BuscarUmRecursoInexistente() {
        
		Throwable exception = assertThrows(NestedServletException.class, () -> {
			doNothing().when(this.repository).deleteById(140L);

			given().accept(ContentType.JSON).when().get("/segmentos/{id}", 140L).then()
					.statusCode(HttpStatus.NOT_FOUND.value());
			
			verify(repository, times(1)).findAll();
	        verifyNoMoreInteractions(repository);
		});
        
	}
	
	@Test
	public void deleteById_Sucesso_BuscarUmRecursoExistente() {
        			
		Segmento seg1 = new Segmento("Nome", "descricao");
		seg1.setId(1L);
		
		when(this.repository.save(seg1)).thenReturn(seg1);

		when(this.repository.deleteById(1L)).thenReturn("SUCESS");
		
		given().accept(ContentType.JSON).when().get("/segmentos/{id}", 1L).then()
			.statusCode(HttpStatus.OK.value());
			
		verify(repository, times(1)).findAll();
	    verifyNoMoreInteractions(repository);        
	}

}
