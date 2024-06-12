package controller.impl;

import java.util.List;

import controller.api.IngredienteController;
import model.entidade.Ingrediente;
import model.service.api.IngredienteService;
import model.service.impl.IngredienteServiceImpl;

public class IngredienteControllerImpl implements IngredienteController {

	private IngredienteService ingredienteService;
	
	public IngredienteControllerImpl() {
		this.ingredienteService = new IngredienteServiceImpl();
	}
	
	@Override
	public void salvar(Ingrediente ingrediente) throws RuntimeException {
		this.ingredienteService.salvar(ingrediente);
		
	}

	@Override
	public void excluir(int id) {
		this.ingredienteService.excluir(id);
	}

	@Override
	public List<Ingrediente> listarTodos() {
		return this.ingredienteService.listarTodos();
	}

	@Override
	public Ingrediente atualizar(Ingrediente ingrediente) {
		return this.ingredienteService.atualizar(ingrediente);
	}
	
	@Override
	public Ingrediente buscarPorId(int id) {
		return this.ingredienteService.buscarPorId(id);
	}

}
