package controller.api;

import java.util.List;
import model.entidade.Produto;

public interface ProdutoController {

	public void salvar(Produto produto) throws RuntimeException;
	
	public void excluir(int id);
	
	public List<Produto> listarTodos();
		
	public Produto atualizar(Produto produto);

	public Produto buscarPorId(int id);

}
