package com.orcaolineapi.resource.usuario;

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
		@Test()
		public void getById_Sucesso_BuscarUmRecursoExistente() {

			ModalidadeTipoUsuario mtp = null;
			TipoUsuario tp = new TipoUsuario("Nome", "Descricao", mtp);
			tp.setId(1L);

			when(this.repository.findById(1L)).thenReturn(Optional.of(tp));

			given().accept(ContentType.JSON).when().get("/tiposUsuarios/{id}", 1L).then().statusCode(HttpStatus.OK.value());

		}

//		@Test
		public void getById_Falha_BuscarUmRecursoInexistente() {
			when(this.repository.findById(1L)).thenReturn(null);

			given().accept(ContentType.JSON).when().get("/tiposUsuarios/{id}", 140L).then()
					.statusCode(HttpStatus.NOT_FOUND.value());
		}

}
