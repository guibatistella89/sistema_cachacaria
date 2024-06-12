package model.service.impl;

import java.util.List;

import model.dao.api.VendaDao;
import model.dao.api.ProdutoDao;
import model.dao.impl.ProdutoDaoImpl;
import model.dao.impl.VendaDaoImpl;
import model.entidade.Venda;
import model.service.api.VendaService;


public class VendaServiceImpl implements VendaService {

	private VendaDao vendaDao;
	@SuppressWarnings("unused")
	private ProdutoDao produtoDao;
	
	public VendaServiceImpl() {
		this.vendaDao = new VendaDaoImpl();
		this.produtoDao = new ProdutoDaoImpl();
	}
	
	@Override
	public void salvar(Venda venda) {
		this.vendaDao.salvar(venda);
	}

	@Override
	public void excluir(int id) {
		this.vendaDao.excluir(id);
	}
 	
	@Override
	public List<Venda> listarTodos() {
		return this.vendaDao.listarTodos();
	}

	@Override
	public Venda buscarPorId(int id) {
		return this.vendaDao.buscarPorId(id);
	}

	@Override
	public Venda atualizar(Venda venda) {
		return this.vendaDao.atualizar(venda);
	}
	
	@Override
	public int buscarQtdPorProduto(int produto) {
		return this.vendaDao.buscarQtdPorProduto(produto);
	} 

}
