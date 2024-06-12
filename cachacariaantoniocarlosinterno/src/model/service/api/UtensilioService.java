package model.service.api;

import java.util.List;
import model.entidade.Utensilio;

public interface UtensilioService {

	public void salvar(Utensilio utensilio);
	
	public void excluir(int id);
	
	public List<Utensilio> listarTodos();
		
	public Utensilio atualizar(Utensilio utensilio);

	public Utensilio buscarPorId(int id);
	
}
