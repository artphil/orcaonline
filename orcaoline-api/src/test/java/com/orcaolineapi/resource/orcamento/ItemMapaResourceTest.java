package com.orcaolineapi.resource.orcamento;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.exceptions.verification.opentest4j.ArgumentsAreDifferent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.orcaolineapi.modelo.orcamento.ItemMapa;
import com.orcaolineapi.repository.orcamento.ItemMapaRepository;
import com.orcaolineapi.service.orcamento.ItemMapaService;

import io.restassured.http.ContentType;

@SpringBootTest
@AutoConfigureMockMvc
class ItemMapaResourceTest {

	private @Autowired ItemMapaResouce resource;

	private @MockBean ItemMapaRepository repository;

	private @MockBean ItemMapaService service;

	private @MockBean HttpServletResponse response;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.resource);
	}

	// nomeDoMetodo / resultadoEsperado / emQueSituação

	@Test
	public void putRecursos_Sucesso_InserirRecursosExistentes() throws Exception {
						
		ItemMapa item1 = new ItemMapa(1.2, null, null, null, null);
		ItemMapa item2 = new ItemMapa(1.2, null, null, null, null);
		
		when(this.repository.save(item1)).thenReturn(item1);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(item2);
				
		json = json.replaceAll("\"" + "id" + "\"[ ]*:[^,}\\]]*[,]?", "");
				
		System.out.println(json);
		
		given().body(json).contentType(ContentType.JSON).when().put("/itemmapas/{id}", item1.getId()).andReturn().then().statusCode(HttpStatus.OK.value());
			
	}
	
	@Test
	public void postRecursos_Sucesso_InserirRecursosExistentes() throws Exception {
						
		ItemMapa item1 = new ItemMapa(1.2, null, null, null, null);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(item1);
				
		json = json.replaceAll("\"" + "id" + "\"[ ]*:[^,}\\]]*[,]?", "");
				
		System.out.println(json);
		
		given().body(json).contentType(ContentType.JSON).when().post("/itemmapas").andReturn().then().statusCode(HttpStatus.OK.value());
			
	}
	
	@Test
	public void getRecursos_Sucesso_BuscarRecursosExistentes() throws Exception {

		ItemMapa item1 = new ItemMapa(1.2, null, null, null, null);
		ItemMapa item2 = new ItemMapa(1.2, null, null, null, null);
		ItemMapa item3 = new ItemMapa(1.2, null, null, null, null);
		ItemMapa item4 = new ItemMapa(1.2, null, null, null, null);
		
		item1.setId(Long.valueOf(1));
		item2.setId(Long.valueOf(2));
		item3.setId(Long.valueOf(3));
		item4.setId(Long.valueOf(4));
		
		when(this.repository.findAll()).thenReturn(Arrays.asList(item1, item2, item3, item4));
		
		given().accept(ContentType.JSON).when().get("/itemmapas").andReturn().then().statusCode(HttpStatus.OK.value())
	    .expect(jsonPath("$[*]", hasSize(4)))
	    .expect(jsonPath("$[0].id", is(1)))
	    .expect(jsonPath("$[1].id", is(2)))
	    .expect(jsonPath("$[2].id", is(3)))
	    .expect(jsonPath("$[3].id", is(4)));
		
		verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
   }
	
	@Test
	public void getRecursos_EmptyList_BuscarRecursosInexistentes() {
		
		when(this.repository.findAll()).thenReturn(Collections.emptyList());

		given().accept(ContentType.JSON).when().get("/itemmapas").then().statusCode(HttpStatus.OK.value())
		.expect(jsonPath("$[*]", hasSize(0)));
		
		verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);

	}
	
	@Test()
	public void getById_Sucesso_BuscarUmRecursoExistente() {

		ItemMapa item1 = new ItemMapa(1.2, null, null, null, null);
		item1.setId(1L);

		when(this.repository.findById(1L)).thenReturn(Optional.of(item1));

		given().accept(ContentType.JSON).when().get("/itemmapas/{id}", 1L).then().statusCode(HttpStatus.OK.value())
		.expect(jsonPath("$.id", is(1)));
		
		verify(repository, times(1)).findById(1L);
        verifyNoMoreInteractions(repository);

	}

	@Test
	public void getById_NestedServletException_BuscarUmRecursoInexistente() {
        
		Throwable exception = assertThrows(NestedServletException.class, () -> {
			when(this.repository.findById(140L)).thenReturn(null);

			given().accept(ContentType.JSON).when().get("/itemmapas/{id}", 140L).then()
					.statusCode(HttpStatus.NOT_FOUND.value());
			
			verify(repository, times(1)).findById(1L);
	        verifyNoMoreInteractions(repository);
		});
        
	}
	
	@Test
	public void deleteById_NestedServletException_BuscarUmRecursoInexistente() {
        
		Throwable exception = assertThrows(ArgumentsAreDifferent.class, () -> {
			ItemMapa item1 = new ItemMapa(1.2, null, null, null, null);
			item1.setId(1L);
			
			when(this.repository.save(item1)).thenReturn(item1);
			
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(item1);
					
			json = json.replaceAll("\"" + "id" + "\"[ ]*:[^,}\\]]*[,]?", "");
							
			given().contentType(ContentType.JSON).when().delete("/itemmapas/{id}", 140L).andReturn().then().statusCode(HttpStatus.NO_CONTENT.value());
				
			verify(repository, times(1)).deleteById(1L);
		    verifyNoMoreInteractions(repository);        
		});
        
	}
	
	@Test
	public void deleteById_Sucesso_BuscarUmRecursoExistente() throws JsonProcessingException {
        			
		ItemMapa item1 = new ItemMapa(1.2, null, null, null, null);
		item1.setId(1L);
		
		when(this.repository.save(item1)).thenReturn(item1);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(item1);
				
		json = json.replaceAll("\"" + "id" + "\"[ ]*:[^,}\\]]*[,]?", "");
						
		given().contentType(ContentType.JSON).when().delete("/itemmapas/{id}", 1L).andReturn().then().statusCode(HttpStatus.NO_CONTENT.value());
			
		verify(repository, times(1)).deleteById(1L);
	    verifyNoMoreInteractions(repository);        
	}

}
