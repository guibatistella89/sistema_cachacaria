package model.service.api;

import java.util.List;

import model.entidade.Produto;

public interface ProdutoService {

	public void salvar(Produto produto);
	
	public void excluir(int id);
	
	public List<Produto> listarTodos();
		
	public Produto atualizar(Produto produto);

	public Produto buscarPorId(int id);

	public int buscarQtdPorSabor(int sabor);
	
}
