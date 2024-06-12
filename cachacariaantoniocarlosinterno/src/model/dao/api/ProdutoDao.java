package model.dao.api;

import model.entidade.Produto;

public interface ProdutoDao extends DAO<Produto> {

	public int buscarQtdPorSabor(int sabor);
}


