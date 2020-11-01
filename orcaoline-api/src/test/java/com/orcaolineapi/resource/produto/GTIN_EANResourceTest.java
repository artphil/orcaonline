package com.orcaolineapi.resource.produto;

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

import java.math.BigInteger;
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
import com.orcaolineapi.modelo.produto.Familia;
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

	@Test
	public void putRecursos_Sucesso_InserirRecursosExistentes() throws Exception {
						
		GTIN_EAN gt1 = new GTIN_EAN(BigInteger.valueOf(123), null);
		GTIN_EAN gt2 = new GTIN_EAN(BigInteger.valueOf(123), null);
		
		when(this.repository.save(gt1)).thenReturn(gt1);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(gt2);
				
		json = json.replaceAll("\"" + "id" + "\"[ ]*:[^,}\\]]*[,]?", "");
				
		System.out.println(json);
		
		given().body(json).contentType(ContentType.JSON).when().put("/gtins/{id}", gt1.getId()).andReturn().then().statusCode(HttpStatus.OK.value());
			
	}
	
	@Test
	public void postRecursos_Sucesso_InserirRecursosExistentes() throws Exception {
						
		GTIN_EAN gt1 = new GTIN_EAN(BigInteger.valueOf(123), null);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(gt1);
				
		json = json.replaceAll("\"" + "id" + "\"[ ]*:[^,}\\]]*[,]?", "");
				
		System.out.println(json);
		
		given().body(json).contentType(ContentType.JSON).when().post("/gtins").andReturn().then().statusCode(HttpStatus.OK.value());
			
	}
	
	@Test
	public void getRecursos_Sucesso_BuscarRecursosExistentes() throws Exception {

		GTIN_EAN gt1 = new GTIN_EAN(BigInteger.valueOf(123), null);
		GTIN_EAN gt2 = new GTIN_EAN(BigInteger.valueOf(456), null);		
		GTIN_EAN gt3 = new GTIN_EAN(BigInteger.valueOf(789), null);
		GTIN_EAN gt4 = new GTIN_EAN(BigInteger.valueOf(101112), null);
		
		gt1.setId(Long.valueOf(1));
		gt2.setId(Long.valueOf(2));
		gt3.setId(Long.valueOf(3));
		gt4.setId(Long.valueOf(4));
		
		when(this.repository.findAll()).thenReturn(Arrays.asList(gt1, gt2, gt3, gt4));
		
		given().accept(ContentType.JSON).when().get("/gtins").andReturn().then().statusCode(HttpStatus.OK.value())
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

		given().accept(ContentType.JSON).when().get("/gtins").then().statusCode(HttpStatus.OK.value())
		.expect(jsonPath("$[*]", hasSize(0)));
		
		verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);

	}
	
	@Test()
	public void getById_Sucesso_BuscarUmRecursoExistente() {

		GTIN_EAN gt1 = new GTIN_EAN(BigInteger.valueOf(123), null);
		gt1.setId(1L);

		when(this.repository.findById(1L)).thenReturn(Optional.of(gt1));

		given().accept(ContentType.JSON).when().get("/gtins/{id}", 1L).then().statusCode(HttpStatus.OK.value())
		.expect(jsonPath("$.id", is(1)));
		
		verify(repository, times(1)).findById(1L);
        verifyNoMoreInteractions(repository);

	}

	@Test
	public void getById_NestedServletException_BuscarUmRecursoInexistente() {
        
		Throwable exception = assertThrows(NestedServletException.class, () -> {
			when(this.repository.findById(140L)).thenReturn(null);

			given().accept(ContentType.JSON).when().get("/gtins/{id}", 140L).then()
					.statusCode(HttpStatus.NOT_FOUND.value());
			
			verify(repository, times(1)).findById(1L);
	        verifyNoMoreInteractions(repository);
		});
        
	}
	
	@Test
	public void deleteById_NestedServletException_BuscarUmRecursoInexistente() {
        
		Throwable exception = assertThrows(ArgumentsAreDifferent.class, () -> {
			GTIN_EAN gt1 = new GTIN_EAN(BigInteger.valueOf(123), null);
			gt1.setId(1L);
			
			when(this.repository.save(gt1)).thenReturn(gt1);
			
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(gt1);
					
			json = json.replaceAll("\"" + "id" + "\"[ ]*:[^,}\\]]*[,]?", "");
							
			given().contentType(ContentType.JSON).when().delete("/gtins/{id}", 140L).andReturn().then().statusCode(HttpStatus.NO_CONTENT.value());
				
			verify(repository, times(1)).deleteById(1L);
		    verifyNoMoreInteractions(repository);        
		});
        
	}
	
	@Test
	public void deleteById_Sucesso_BuscarUmRecursoExistente() throws JsonProcessingException {
        			
		GTIN_EAN gt1 = new GTIN_EAN(BigInteger.valueOf(123), null);
		gt1.setId(1L);
		
		when(this.repository.save(gt1)).thenReturn(gt1);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(gt1);
				
		json = json.replaceAll("\"" + "id" + "\"[ ]*:[^,}\\]]*[,]?", "");
						
		given().contentType(ContentType.JSON).when().delete("/gtins/{id}", 1L).andReturn().then().statusCode(HttpStatus.NO_CONTENT.value());
			
		verify(repository, times(1)).deleteById(1L);
	    verifyNoMoreInteractions(repository);        
	}


}
