package com.orcaolineapi.modelo.orcamento;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.orcaolineapi.modelo.usuario.Usuario;

class MapaColetaModeloTest {
	
	private MapaColeta map;
	private List<Orcamento>  orcamentosAntes;
	private List<Orcamento>  orcamentosDepois;
	private Orcamento orc1, orc2, orc3;

	@BeforeEach
	void setUp() throws Exception {
		map = new MapaColeta(LocalDate.now(), null, "Descricao do MapaColeta" , null, Status.EM_ANDAMENTO);
		
		orcamentosAntes = map.getOrcamentos();
		
		orc1 = map.criaNovoOrcamento();
		orc2 = map.criaNovoOrcamento();
		orc3 = map.criaNovoOrcamento();
		
		orcamentosDepois = map.getOrcamentos();
	}
	
	@Test
	public void MapaColetaIsRunningWithEmAndamentoStatusShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {						
			assertEquals(map.isRunning(), true);			
		});
	}
	
	@Test
	public void MapaColetaIsRunningWithAnotherStatusShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			map.setStatus(Status.FECHADO);
			assertEquals(map.isRunning(), false);			
		});
	}
	
	@Test
	public void saveMapaColetaWithUsedStatusShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {			
			List<Status> usedS = Usuario.usedStatus();			
			assertThat(usedS.contains(Status.ABERTO)&&usedS.contains(Status.EM_ANDAMENTO)&&usedS.contains(Status.FECHADO));
		});
	}
	
	@Test
	public void saveMapaColetaWithNotUsedStatusShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			List<Status> usedS = Usuario.usedStatus();			
			assertThat(usedS.contains(Status.ATIVO)&&usedS.contains(Status.INATIVO)&&usedS.contains(Status.CANCELADO));
		});
	}
	
	@Test
	public void saveMapaColetaCriaNovoOrcamentoWithStatusEmAndamentoShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {						
			assertThat(!orc1.equals(null)
					&& !orc2.equals(null)
					&& !orc3.equals(null)
					&& (orcamentosDepois.size() > orcamentosAntes.size()));
		});
	}
	
	@Test
	public void saveMapaColetaCriaNovoOrcamentoWithAnotherStatusShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {		
			map.setStatus(Status.FECHADO);
			assertThat(orc1.equals(null)
					&& orc2.equals(null)
					&& orc3.equals(null)
					&& (orcamentosDepois.size() == orcamentosAntes.size()));
			
		});
	}
	
	@Test
	public void saveMapaColetaEncerraShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			map.encerrar();
			assertThat(map.getDataEncerramento().equals(LocalDate.now()) && (map.getStatus()).equals(Status.FECHADO));
		});
	}
	
	@Test
	public void saveMapaColetaAprovarOrcamentoShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {	
			map.aprovarOrcamento(orc1.getId());
			map.aprovarOrcamento(orc2.getId());
			map.aprovarOrcamento(orc3.getId());
			
			for(Orcamento orcamento : map.getOrcamentos()) {
				assertThat(orcamento.getAprovado().equals(true) 
						&& map.getDataEncerramento().equals(LocalDate.now()) 
						&& map.getStatus().equals(Status.FECHADO)
						&& (orcamentosDepois.size() > orcamentosAntes.size()));
			}		
		});
	}

}
