package controller.api;

import java.util.List;
import model.entidade.Ingrediente;

public interface IngredienteController {

	public void salvar(Ingrediente ingrediente) throws RuntimeException;
	
	public void excluir(int id);
	
	public List<Ingrediente> listarTodos();
		
	public Ingrediente atualizar(Ingrediente ingrediente);

	public Ingrediente buscarPorId(int id);

}
