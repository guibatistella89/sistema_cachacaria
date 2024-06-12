package model.service.impl;

import java.util.List;


import model.dao.api.SaborDao;
import model.dao.api.ProdutoDao;
import model.dao.impl.SaborDaoImpl;
import model.dao.impl.ProdutoDaoImpl;
import model.entidade.Sabor;
import model.service.api.ExclusaoSaborCallback;
import model.service.api.SaborService;


public class SaborServiceImpl implements SaborService {

	private SaborDao saborDao;
	private ProdutoDao produtoDao;
	
	public SaborServiceImpl() {
		this.saborDao = new SaborDaoImpl();
		this.produtoDao = new ProdutoDaoImpl();
	}
	
	@Override
	public void salvar(Sabor sabor) {
		this.saborDao.salvar(sabor);
	}

	@Override
	
	
	public void excluir(int id, ExclusaoSaborCallback callback) {
		if(this.produtoDao.buscarQtdPorSabor(id) == 0) {
			this.saborDao.excluir(id);
			callback.onSaborExcluidoComSucesso();
		}else {
			callback.onFalhaAoExcluirSabor();			
		}
	}
 	
	@Override
	public List<Sabor> listarTodos() {
		return this.saborDao.listarTodos();
	}

	@Override
	public Sabor buscarPorId(int id) {
		return this.saborDao.buscarPorId(id);
	}

	@Override
	public Sabor atualizar(Sabor sabor) {
		return this.saborDao.atualizar(sabor);
	}

}
