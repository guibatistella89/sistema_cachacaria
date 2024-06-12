package model.service.api;

import java.util.List;


import model.entidade.Venda;


public interface VendaService {

	public void salvar(Venda venda);
	
	public void excluir(int id);
	
	public List<Venda> listarTodos();
	
	public Venda buscarPorId(int id);
	
	public Venda atualizar(Venda venda);
	
	public int buscarQtdPorProduto(int produto);
}