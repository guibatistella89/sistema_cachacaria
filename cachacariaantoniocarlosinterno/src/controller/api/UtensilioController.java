package controller.api;

import java.util.List;
import model.entidade.Utensilio;

public interface UtensilioController {

	public void salvar(Utensilio utensilio) throws RuntimeException;
	
	public void excluir(int id);
	
	public List<Utensilio> listarTodos();
		
	public Utensilio atualizar(Utensilio utensilio);

	public Utensilio buscarPorId(int id);

}
