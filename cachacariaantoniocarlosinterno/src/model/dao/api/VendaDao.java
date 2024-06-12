package model.dao.api;

import model.entidade.Venda;

public interface VendaDao extends DAO<Venda> {

	public int buscarQtdPorProduto(int produto);
}
