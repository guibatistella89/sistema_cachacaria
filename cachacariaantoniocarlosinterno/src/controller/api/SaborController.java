package controller.api;

import java.util.List;


import model.entidade.Sabor;
import model.service.api.ExclusaoSaborCallback;


public interface SaborController {

	public void salvar(Sabor sabor) throws RuntimeException;
	
	public void excluir(int id, ExclusaoSaborCallback callback);
	
	public List<Sabor> listarTodos();
	
	public Sabor buscarPorId(int id);
	
	public Sabor atualizar(Sabor sabor);
}
