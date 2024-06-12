package controller.impl;

import java.util.List;

import controller.api.ProdutoController;
import model.entidade.Produto;
import model.service.api.ProdutoService;
import model.service.impl.ProdutoServiceImpl;

public class ProdutoControllerImpl implements ProdutoController {

	private ProdutoService produtoService;
	
	public ProdutoControllerImpl() {
		this.produtoService = new ProdutoServiceImpl();
	}
	
	@Override
	public void salvar(Produto produto) throws RuntimeException {
		this.produtoService.salvar(produto);
		
	}

	@Override
	public void excluir(int id) {
		this.produtoService.excluir(id);
	}

	@Override
	public List<Produto> listarTodos() {
		return this.produtoService.listarTodos();
	}

	@Override
	public Produto atualizar(Produto produto) {
		return this.produtoService.atualizar(produto);
	}
	
	@Override
	public Produto buscarPorId(int id) {
		return this.produtoService.buscarPorId(id);
	}

}