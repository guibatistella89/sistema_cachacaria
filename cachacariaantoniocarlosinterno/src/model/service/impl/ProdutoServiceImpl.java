package model.service.impl;

import java.util.List;

import model.dao.api.ProdutoDao;
import model.dao.impl.ProdutoDaoImpl;
import model.entidade.Produto;
import model.service.api.ProdutoService;

public class ProdutoServiceImpl implements ProdutoService {

	private ProdutoDao produtoDao;
	
	public ProdutoServiceImpl() {
		this.produtoDao = new ProdutoDaoImpl();
	}
	
	@Override
	public void salvar(Produto produto) {
		this.produtoDao.salvar(produto);
	}

	@Override
	public void excluir(int id) {
		this.produtoDao.excluir(id);
		
		}
	

	@Override
	public List<Produto> listarTodos() {
		return this.produtoDao.listarTodos();
	}

	
	@Override
	public Produto atualizar(Produto produto) {
		return this.produtoDao.atualizar(produto);
	}
	
	@Override
	public Produto buscarPorId(int id) {
		return this.produtoDao.buscarPorId(id);
	}


	@Override
	public int buscarQtdPorSabor(int sabor) {
		return this.produtoDao.buscarQtdPorSabor(sabor);
	}  

}


