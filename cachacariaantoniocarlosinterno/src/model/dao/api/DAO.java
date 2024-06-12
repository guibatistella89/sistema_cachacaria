package model.dao.api;

import java.util.List;

public interface DAO<T> {

	public void salvar(T t);
	
	public void excluir(int id);
	
	public List<T> listarTodos();
	
	public T atualizar(T item);
	
	public T buscarPorId(int id);
}

