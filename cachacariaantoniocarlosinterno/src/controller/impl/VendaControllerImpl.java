package controller.impl;

import java.util.List;

import controller.api.VendaController;
import model.entidade.Venda;
import model.service.api.VendaService;
import model.service.impl.VendaServiceImpl;


public class VendaControllerImpl implements VendaController {

	private VendaService vendaService;
	
	public VendaControllerImpl() {
		this.vendaService = new VendaServiceImpl();
	}
	
	@Override
	public void salvar(Venda venda) throws RuntimeException {
		this.vendaService.salvar(venda);
		
	}

	@Override
	public void excluir(int id) {
		this.vendaService.excluir(id);
	}

	@Override
	public List<Venda> listarTodos() {
		return this.vendaService.listarTodos();
	}

	@Override
	public Venda buscarPorId(int id) {
		return this.vendaService.buscarPorId(id);
	}

	@Override
	public Venda atualizar(Venda venda) {
		return this.vendaService.atualizar(venda);
	}

}