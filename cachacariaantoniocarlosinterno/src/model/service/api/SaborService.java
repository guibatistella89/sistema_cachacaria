package model.service.api;

import java.util.List;


import model.entidade.Sabor;


public interface SaborService {

	public void salvar(Sabor sabor);
	
	public void excluir(int id, ExclusaoSaborCallback callback);
	
	public List<Sabor> listarTodos();
	
	public Sabor buscarPorId(int id);
	
	public Sabor atualizar(Sabor sabor);
}

