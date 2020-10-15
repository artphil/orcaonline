package com.orcaolineapi.resource.usuario;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

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
	public void buscar_por_email_existente() {
		Usuario u = new Usuario();
		u.setEmail("email@email");
		when(this.repository.findByEmail("email@email")).thenReturn(Optional.of(u));
		given().accept(ContentType.JSON).when().get("usuarios/getByEmail/{email}", "email@email").then().statusCode(HttpStatus.OK.value());
		
	}
	
}
