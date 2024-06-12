package controller.api;

import java.util.List;
import model.entidade.Venda;


public interface VendaController {

	
		public void salvar(Venda venda) throws RuntimeException;
		
		public void excluir(int id);
		
		public List<Venda> listarTodos();
		
		public Venda buscarPorId(int id);
		
		public Venda atualizar(Venda venda);
	}

