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
import com.orcaolineapi.modelo.usuario.ModalidadeTipoUsuario;
import com.orcaolineapi.modelo.usuario.TipoUsuario;
import com.orcaolineapi.repository.usuario.TipoUsuarioRepository;
import com.orcaolineapi.service.usuario.TipoUsuarioService;

import io.restassured.http.ContentType;

@SpringBootTest
@AutoConfigureMockMvc
class TipoUsuarioResourceTest {

	private @MockBean TipoUsuarioRepository repository;
	
	private @Autowired TipoUsuarioResource resource;
	
	private @MockBean TipoUsuarioService service;

	private @MockBean HttpServletResponse response;
	
	@BeforeEach
	public void setUp(){
		standaloneSetup(this.resource);
	}

	// nomeDoMetodo / resultadoEsperado / emQueSituação

	@Test
	public void putRecursos_Sucesso_InserirRecursosExistentes() throws Exception {
							
		
			
		TipoUsuario tp1 = new TipoUsuario("Nome", "Descricao", ModalidadeTipoUsuario.INTERNO);
		TipoUsuario tp2 = new TipoUsuario("Nome modificado", "descricao modificada", ModalidadeTipoUsuario.INTERNO);
			
		when(this.repository.save(tp1)).thenReturn(tp1);
			
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(tp2);
					
		json = json.replaceAll("\"" + "id" + "\"[ ]*:[^,}\\]]*[,]?", "");
					
		System.out.println(json);
			
		given().body(json).contentType(ContentType.JSON).when().put("/tiposUsuarios/{id}", tp1.getId()).andReturn().then().statusCode(HttpStatus.OK.value());
				
		}
		
		@Test
		public void postRecursos_Sucesso_InserirRecursosExistentes() throws Exception {
							
			
			
			TipoUsuario tp1 = new TipoUsuario("Nome", "Descricao", ModalidadeTipoUsuario.INTERNO);
			
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(tp1);
					
			json = json.replaceAll("\"" + "id" + "\"[ ]*:[^,}\\]]*[,]?", "");
					
			System.out.println(json);
			
			given().body(json).contentType(ContentType.JSON).when().post("/tiposUsuarios").andReturn().then().statusCode(HttpStatus.OK.value());
				
		}
		
		@Test
		public void getRecursos_Sucesso_BuscarRecursosExistentes() throws Exception {
			
			
			
			TipoUsuario tp1 = new TipoUsuario("Nome1", "descricao1", ModalidadeTipoUsuario.INTERNO);
			TipoUsuario tp2 = new TipoUsuario("Nome2", "descricao2", ModalidadeTipoUsuario.INTERNO);
			TipoUsuario tp3 = new TipoUsuario("Nome3", "descricao3", ModalidadeTipoUsuario.INTERNO);			
			TipoUsuario tp4 = new TipoUsuario("Nome4", "descricao4", ModalidadeTipoUsuario.INTERNO);
			
			tp1.setId(Long.valueOf(1));
			tp2.setId(Long.valueOf(2));
			tp3.setId(Long.valueOf(3));
			tp4.setId(Long.valueOf(4));
			
			when(this.repository.findAll()).thenReturn(Arrays.asList(tp1, tp2, tp3, tp4));
			
			given().accept(ContentType.JSON).when().get("/tiposUsuarios").andReturn().then().statusCode(HttpStatus.OK.value())
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
		public void getRecursos_EmptyList_BuscarRecursosInexistentes() {
			
			when(this.repository.findAll()).thenReturn(Collections.emptyList());

			given().accept(ContentType.JSON).when().get("/tiposUsuarios").then().statusCode(HttpStatus.OK.value())
			.expect(jsonPath("$[*]", hasSize(0)));
			
			verify(repository, times(1)).findAll();
	        verifyNoMoreInteractions(repository);

		}
		
		@Test()
		public void getById_Sucesso_BuscarUmRecursoExistente() {

			

			TipoUsuario tp1 = new TipoUsuario("Nome", "descricao", ModalidadeTipoUsuario.INTERNO);
			tp1.setId(1L);

			when(this.repository.findById(1L)).thenReturn(Optional.of(tp1));

			given().accept(ContentType.JSON).when().get("/tiposUsuarios/{id}", 1L).then().statusCode(HttpStatus.OK.value())
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

				given().accept(ContentType.JSON).when().get("/tiposUsuarios/{id}", 140L).then()
						.statusCode(HttpStatus.NOT_FOUND.value());
				
				verify(repository, times(1)).findById(1L);
		        verifyNoMoreInteractions(repository);
			});
	        
		}
		
		@Test
		public void deleteById_NestedServletException_BuscarUmRecursoInexistente() {
	        
			Throwable exception = assertThrows(ArgumentsAreDifferent.class, () -> {
				

				TipoUsuario tp1 = new TipoUsuario("Nome", "Descricao", ModalidadeTipoUsuario.INTERNO);
				tp1.setId(1L);
				
				when(this.repository.save(tp1)).thenReturn(tp1);
				
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String json = ow.writeValueAsString(tp1);
						
				json = json.replaceAll("\"" + "id" + "\"[ ]*:[^,}\\]]*[,]?", "");
								
				given().contentType(ContentType.JSON).when().delete("/tiposUsuarios/{id}", 140L).andReturn().then().statusCode(HttpStatus.NO_CONTENT.value());
					
				verify(repository, times(1)).deleteById(1L);
			    verifyNoMoreInteractions(repository);        
			});
	        
		}
		
		@Test
		public void deleteById_Sucesso_BuscarUmRecursoExistente() throws JsonProcessingException {
	        			
			

			TipoUsuario tp1 = new TipoUsuario("Nome", "Descricao", ModalidadeTipoUsuario.INTERNO);
			tp1.setId(1L);
			
			when(this.repository.save(tp1)).thenReturn(tp1);
			
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(tp1);
					
			json = json.replaceAll("\"" + "id" + "\"[ ]*:[^,}\\]]*[,]?", "");
							
			given().contentType(ContentType.JSON).when().delete("/tiposUsuarios/{id}", 1L).andReturn().then().statusCode(HttpStatus.NO_CONTENT.value());
				
			verify(repository, times(1)).deleteById(1L);
		    verifyNoMoreInteractions(repository);        
		}


}
