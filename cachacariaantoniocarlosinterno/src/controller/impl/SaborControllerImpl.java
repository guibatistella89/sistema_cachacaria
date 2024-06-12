package controller.impl;

import java.util.List;
import controller.api.SaborController;
import model.entidade.Sabor;
import model.service.api.ExclusaoSaborCallback;
import model.service.api.SaborService;
import model.service.impl.SaborServiceImpl;

public class SaborControllerImpl implements SaborController {

	private SaborService saborService;
	
	public SaborControllerImpl() {
		this.saborService = new SaborServiceImpl();
	}
	
	@Override
	public void salvar(Sabor sabor) throws RuntimeException {
		this.saborService.salvar(sabor);
		
	}

	@Override
	public void excluir(int id, ExclusaoSaborCallback callback) {
		this.saborService.excluir(id, callback);
	}

	@Override
	public List<Sabor> listarTodos() {
		return this.saborService.listarTodos();
	}

	@Override
	public Sabor buscarPorId(int id) {
		return this.saborService.buscarPorId(id);
	}

	@Override
	public Sabor atualizar(Sabor sabor) {
		return this.saborService.atualizar(sabor);
	}

}

