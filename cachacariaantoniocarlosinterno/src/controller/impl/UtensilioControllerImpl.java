package controller.impl;

import java.util.List;

import controller.api.UtensilioController;
import model.entidade.Utensilio;
import model.service.api.UtensilioService;
import model.service.impl.UtensilioServiceImpl;

public class UtensilioControllerImpl implements UtensilioController {

	private UtensilioService utensilioService;
	
	public UtensilioControllerImpl() {
		this.utensilioService = new UtensilioServiceImpl();
	}
	
	@Override
	public void salvar(Utensilio utensilio) throws RuntimeException {
		this.utensilioService.salvar(utensilio);
		
	}

	@Override
	public void excluir(int id) {
		this.utensilioService.excluir(id);
	}

	@Override
	public List<Utensilio> listarTodos() {
		return this.utensilioService.listarTodos();
	}

	@Override
	public Utensilio atualizar(Utensilio utensilio) {
		return this.utensilioService.atualizar(utensilio);
	}
	
	@Override
	public Utensilio buscarPorId(int id) {
		return this.utensilioService.buscarPorId(id);
	}

}