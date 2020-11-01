package com.orcaolineapi.resource.usuario;

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
import com.orcaolineapi.modelo.usuario.Usuario;
import com.orcaolineapi.repository.usuario.UsuarioRepository;

import io.restassured.http.ContentType;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioResourceTest {

	private @MockBean UsuarioRepository repository;
	
	private @Autowired UsuarioResource resource;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.resource);
	}

	@Test
	public void putRecursos_Sucesso_InserirRecursosExistentes() throws Exception {
						
		Usuario usu1 = new Usuario();
		usu1.setEmail("email@email");
		
		Usuario usu2 = new Usuario();
		usu2.setEmail("emailmod@email");
		
		when(this.repository.save(usu1)).thenReturn(usu1);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(usu2);
				
		json = json.replaceAll("\"" + "id" + "\"[ ]*:[^,}\\]]*[,]?", "");
				
		System.out.println(json);
		
		given().body(json).contentType(ContentType.JSON).when().put("/usuarios/{id}", usu1.getId()).andReturn().then().statusCode(HttpStatus.OK.value());
			
	}
	
	@Test
	public void postRecursos_Sucesso_InserirRecursosExistentes() throws Exception {
						
		Usuario usu1 = new Usuario();
		usu1.setEmail("email@email");
		usu1.setId(1L);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(usu1);
				
		json = json.replaceAll("\"" + "id" + "\"[ ]*:[^,}\\]]*[,]?", "");
				
		System.out.println(json);
		
		given().body(json).contentType(ContentType.JSON).when().post("/usuarios").andReturn().then().statusCode(HttpStatus.OK.value());
			
	}
	
	@Test
	public void getRecursos_Sucesso_BuscarRecursosExistentes() throws Exception {
		
		Usuario usu1 = new Usuario();
		usu1.setEmail("email1@email");
		
		Usuario usu2 = new Usuario();
		usu2.setEmail("email2@email");
		
		Usuario usu3 = new Usuario();
		usu3.setEmail("email3@email");
		
		Usuario usu4 = new Usuario();
		usu4.setEmail("email4@email");
		
		usu1.setId(Long.valueOf(1));
		usu2.setId(Long.valueOf(2));
		usu3.setId(Long.valueOf(3));
		usu4.setId(Long.valueOf(4));
		
		when(this.repository.findAll()).thenReturn(Arrays.asList(usu1, usu2, usu3, usu4));
		
		given().accept(ContentType.JSON).when().get("/usuarios").andReturn().then().statusCode(HttpStatus.OK.value())
	    .expect(jsonPath("$[*]", hasSize(4)))
	    .expect(jsonPath("$[0].id", is(1)))
	    .expect(jsonPath("$[0].email", is("email1@email")))
	    .expect(jsonPath("$[1].id", is(2)))
	    .expect(jsonPath("$[1].email", is("email2@email")))
	    .expect(jsonPath("$[2].id", is(3)))
	    .expect(jsonPath("$[2].email", is("email3@email")))
	    .expect(jsonPath("$[3].id", is(4)))
	    .expect(jsonPath("$[3].email", is("email4@email")));
		
		verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
   }
	
	@Test
	public void getRecursos_EmptyList_BuscarRecursosInexistentes() {
		
		when(this.repository.findAll()).thenReturn(Collections.emptyList());

		given().accept(ContentType.JSON).when().get("/usuarios").then().statusCode(HttpStatus.OK.value())
		.expect(jsonPath("$[*]", hasSize(0)));
		
		verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);

	}
	
	@Test()
	public void getById_Sucesso_BuscarUmRecursoExistente() {

		Usuario usu1 = new Usuario();
		usu1.setEmail("email@email");
		usu1.setId(1L);

		when(this.repository.findById(1L)).thenReturn(Optional.of(usu1));

		given().accept(ContentType.JSON).when().get("/usuarios/{id}", 1L).then().statusCode(HttpStatus.OK.value())
		.expect(jsonPath("$.id", is(1)))
	    .expect(jsonPath("$.email", is("email@email")));
		
		verify(repository, times(1)).findById(1L);
        verifyNoMoreInteractions(repository);

	}
	
	@Test()
	public void getByEmail_Sucesso_BuscarUmRecursoExistente() {

		Usuario usu1 = new Usuario();
		usu1.setEmail("email@email");
		usu1.setId(1L);

		when(this.repository.findByEmail("email@email")).thenReturn(Optional.of(usu1));

		given().accept(ContentType.JSON).when().get("usuarios/getByEmail/{email}", "email@email").then().statusCode(HttpStatus.OK.value())
		.expect(jsonPath("$.id", is(1)))
	    .expect(jsonPath("$.email", is("email@email")));
		
		verify(repository, times(1)).findByEmail("email@email");
        verifyNoMoreInteractions(repository);
	}

	@Test
	public void getById_NestedServletException_BuscarUmRecursoInexistente() {
        
		Throwable exception = assertThrows(NestedServletException.class, () -> {
			when(this.repository.findById(140L)).thenReturn(null);

			given().accept(ContentType.JSON).when().get("/usuarios/{id}", 140L).then()
					.statusCode(HttpStatus.NOT_FOUND.value());
			
			verify(repository, times(1)).findById(1L);
	        verifyNoMoreInteractions(repository);
		});
        
	}
	
	@Test
	public void deleteById_NestedServletException_BuscarUmRecursoInexistente() {
        
		Throwable exception = assertThrows(ArgumentsAreDifferent.class, () -> {
			Usuario usu1 = new Usuario();
			usu1.setEmail("email@email");
			usu1.setId(1L);
			
			when(this.repository.save(usu1)).thenReturn(usu1);
			
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(usu1);
					
			json = json.replaceAll("\"" + "id" + "\"[ ]*:[^,}\\]]*[,]?", "");
							
			given().contentType(ContentType.JSON).when().delete("/usuarios/{id}", 140L).andReturn().then().statusCode(HttpStatus.NO_CONTENT.value());
				
			verify(repository, times(1)).deleteById(1L);
		    verifyNoMoreInteractions(repository);        
		});
        
	}
	
	@Test
	public void deleteById_Sucesso_BuscarUmRecursoExistente() throws JsonProcessingException {
        			
		Usuario usu1 = new Usuario();
		usu1.setEmail("email@email");
		usu1.setId(1L);
		
		when(this.repository.save(usu1)).thenReturn(usu1);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(usu1);
				
		json = json.replaceAll("\"" + "id" + "\"[ ]*:[^,}\\]]*[,]?", "");
						
		given().contentType(ContentType.JSON).when().delete("/usuarios/{id}", 1L).andReturn().then().statusCode(HttpStatus.NO_CONTENT.value());
			
		verify(repository, times(1)).deleteById(1L);
	    verifyNoMoreInteractions(repository);        
	}

}
