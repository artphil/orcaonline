package com.orcaolineapi.modelo.produto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.orcaolineapi.repository.produto.SegmentoRepository;

class SegmentoTest {
	
	@BeforeEach
	public static void setup() {

	    // Setup resource needed by all tests.
	}
	
	@Test
	final void testSaveStringInWrongFormat() {
//		SegmentoRepository repository = null;
//		Segmento segmento = new Segmento("nome9+", "descricao");
//		segmento.setId(123L);
//		Segmento result = repository.save(segmento);
//		
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	final void testSaveWithoutId() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	final void testSaveWithoutName() {
		
		Segmento segmento = new Segmento();
		//segmento = rep.save(segmento);
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	final void testSaveWithoutDescricao() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	final void testGetId() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testSetId() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testGetNome() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testSetNome() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testGetDescricao() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testSetDescricao() {
		fail("Not yet implemented"); // TODO
	}

}
