package com.orcaolineapi.service.orcamento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcaolineapi.modelo.LogicException;
import com.orcaolineapi.modelo.orcamento.ItemOrcamento;
import com.orcaolineapi.modelo.orcamento.MapaColeta;
import com.orcaolineapi.modelo.orcamento.Orcamento;
import com.orcaolineapi.modelo.produto.Produto;
import com.orcaolineapi.repository.orcamento.ItemOrcamentoRepository;
import com.orcaolineapi.repository.orcamento.MapaColetaRepository;
import com.orcaolineapi.repository.orcamento.OrcamentoRepository;
import com.orcaolineapi.repository.orcamento.filter.OrcamentoFilter;
import com.orcaolineapi.repository.produto.ProdutoRepository;
import com.orcaolineapi.service.AbstractService;

@Service
public class OrcamentoService extends AbstractService<Orcamento> {

	private @Autowired OrcamentoRepository repository;
	
	private @Autowired MapaColetaRepository mapaRepository;
	
	private @Autowired ItemOrcamentoRepository itemOrcamentoRepository;
	
	private @Autowired ProdutoRepository produtoRepository;

	@Override
	public OrcamentoRepository getRepository() {
		return repository;
	}
	
	public Orcamento create(Long idMapa) throws LogicException {
		MapaColeta mapa = mapaRepository.findById(idMapa).get();
		return repository.save(mapa.criaNovoOrcamento(getUsuarioLogado()));
	}
	
	public Orcamento enviar(Long id) {
		Orcamento o = repository.findById(id).get();
		o.enviar();
		return repository.save(o);
	}
	
	public void deletar(Long idOrcamento) {
		Orcamento orcamento = repository.findById(idOrcamento).get();
		if(orcamento.isOpen()) {
			repository.delete(orcamento);
		}
	}
	
	public Orcamento updateItem(ItemOrcamento item) {
		ItemOrcamento itemSalvo = itemOrcamentoRepository.findById(item.getId()).get();
		itemSalvo.setValorUnitario(item.getValorUnitario());
		itemSalvo.setValorUnitarioPrazo(item.getValorUnitarioPrazo());
		if(item.getProduto() != null && item.getProduto().getId() != null) {
			Produto p = produtoRepository.findById(item.getProduto().getId()).get();
			itemSalvo.setProduto(p);
		}
		itemOrcamentoRepository.save(itemSalvo);
		return itemSalvo.getOrcamento();
	}
	
	public List<Orcamento> filtrar(OrcamentoFilter filtro){
		filtro.setIdUsuario(getUsuarioLogado().getId());
		return repository.filtrar(filtro);
	}
}
