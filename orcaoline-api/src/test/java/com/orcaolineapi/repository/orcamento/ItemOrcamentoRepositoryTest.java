package com.orcaolineapi.repository.orcamento;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.orcaolineapi.repository.produto.BrickRepository;
import com.orcaolineapi.repository.produto.ClasseRepository;
import com.orcaolineapi.repository.produto.FamiliaRepository;
import com.orcaolineapi.repository.produto.GTIN_EANRepository;
import com.orcaolineapi.repository.produto.NCMRepository;
import com.orcaolineapi.repository.produto.ProdutoRepository;
import com.orcaolineapi.repository.produto.SegmentoRepository;
import com.orcaolineapi.repository.usuario.TipoUsuarioRepository;
import com.orcaolineapi.repository.usuario.UsuarioRepository;

class ItemOrcamentoRepositoryTest {
	
private @Autowired ItemMapaRepository repositoryI;

	/* ITEMORCAMENTO REPOSITORY */

	
	/* ITEMMAPA REPOSITORY */
	
	private @Autowired MapaColetaRepository repositoryM;
	
	private @Autowired UsuarioRepository repositoryU;

	private @Autowired TipoUsuarioRepository repositoryT;

		
	/* PRODUTO + BRICK REPOSITORY */
	
	private @Autowired BrickRepository repositoryBri;

	private @Autowired ClasseRepository repositoryCla;

	private @Autowired SegmentoRepository repositorySeg;

	private @Autowired FamiliaRepository repositoryFam;
		
	private @Autowired NCMRepository repositoryNcm;

	private @Autowired GTIN_EANRepository repositoryGti;

	private @Autowired ProdutoRepository repositoryPro;

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
