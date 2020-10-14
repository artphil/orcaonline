package com.orcaolineapi.modelo.orcamento;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrcamentoModeloTest {
	
	private Orcamento orc;
	private List<Status> orcS;
	
	@BeforeEach
	void setUp() throws Exception {
		orc = new Orcamento(LocalDate.now(), LocalDate.now(), null, Status.EM_ANDAMENTO, null, false);
		orcS = Orcamento.usedStatus();
	}

	@Test
	public void OrcamentoIsRunningWithEmAndamentoStatusShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {			
			assertEquals(orc.isRunning(), true);			
		});
	}
	
	@Test
	public void OrcamentoIsRunningWithAnotherStatusShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			orc.setStatus(Status.CANCELADO);
			assertEquals(orc.isRunning(), false);		
		});
	}
	
	@Test
	public void OrcamentoIsOpenWithAbertoStatusShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			orc.setStatus(Status.ABERTO);
			assertEquals(orc.isOpen(), true);			
		});
	}
	
	@Test
	public void OrcamentoIsOpenWithAnotherStatusShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			orc.setStatus(Status.CANCELADO);			
			assertEquals(orc.isOpen(), false);		
		});
	}
	
	@Test
	public void OrcamentoIsClosedWithFechadoStatusShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			orc.setStatus(Status.FECHADO);
			assertEquals(orc.isClosed(), true);			
		});
	}
	
	@Test
	public void OrcamentoIsClosedWithAnotherStatusShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			orc.setStatus(Status.CANCELADO);			
			assertEquals(orc.isClosed(), false);		
		});
	}
	
	@Test
	public void saveOrcamentoWithUsedStatusShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			assertThat(orcS.contains(Status.ABERTO)&&orcS.contains(Status.EM_ANDAMENTO)&&orcS.contains(Status.FECHADO));
		});
	}
	
	@Test
	public void saveOrcamentoWithNotUsedStatusShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			assertThat(orcS.contains(Status.ATIVO)&&orcS.contains(Status.INATIVO)&&orcS.contains(Status.CANCELADO));
		});
	}
	
	@Test
	public void saveOrcamentoEnviarWithStatusAbertoShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			orc.setStatus(Status.ABERTO);			
			orc.enviar();
			assertThat(orc.getStatus().equals(Status.EM_ANDAMENTO) && orc.getDataEnvio().equals(LocalDate.now()));
		});
	}
	
	@Test
	public void saveOrcamentoEnviarWithAnotherStatusShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			orc.setStatus(Status.ATIVO);			
			orc.enviar();
			assertThat(orc.getStatus().equals(Status.ATIVO) && !orc.getDataEnvio().equals(LocalDate.now()));		
		});
	}

}
