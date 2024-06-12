package model.service.impl;

import java.util.List;

import model.dao.api.IngredienteDao;
import model.dao.impl.IngredienteDaoImpl;
import model.entidade.Ingrediente;
import model.service.api.IngredienteService;

public class IngredienteServiceImpl implements IngredienteService {

	private IngredienteDao ingredienteDao;
	
	public IngredienteServiceImpl() {
		this.ingredienteDao = new IngredienteDaoImpl();
	}
	
	@Override
	public void salvar(Ingrediente ingrediente) {
		this.ingredienteDao.salvar(ingrediente);
	}

	@Override
	public void excluir(int id) {
		this.ingredienteDao.excluir(id);
		
		}
	
	@Override
	public List<Ingrediente> listarTodos() {
		return this.ingredienteDao.listarTodos();
	}

	
	@Override
	public Ingrediente atualizar(Ingrediente ingrediente) {
		return this.ingredienteDao.atualizar(ingrediente);
	}
	
	@Override
	public Ingrediente buscarPorId(int id) {
		return this.ingredienteDao.buscarPorId(id);
	}
  

}


