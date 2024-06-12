package model.service.api;

import java.util.List;
import model.entidade.Ingrediente;

public interface IngredienteService {

	public void salvar(Ingrediente ingrediente);
	
	public void excluir(int id);
	
	public List<Ingrediente> listarTodos();
		
	public Ingrediente atualizar(Ingrediente ingrediente);

	public Ingrediente buscarPorId(int id);
	
}
