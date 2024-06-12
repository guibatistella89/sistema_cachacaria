package model.service.impl;

import java.util.List;

import model.dao.api.UtensilioDao;
import model.dao.impl.UtensilioDaoImpl;
import model.entidade.Utensilio;
import model.service.api.UtensilioService;

public class UtensilioServiceImpl implements UtensilioService {

	private UtensilioDao utensilioDao;
	
	public UtensilioServiceImpl() {
		this.utensilioDao = new UtensilioDaoImpl();
	}
	
	@Override
	public void salvar(Utensilio utensilio) {
		this.utensilioDao.salvar(utensilio);
	}

	@Override
	public void excluir(int id) {
		this.utensilioDao.excluir(id);
		
		}
	
	@Override
	public List<Utensilio> listarTodos() {
		return this.utensilioDao.listarTodos();
	}

	
	@Override
	public Utensilio atualizar(Utensilio utensilio) {
		return this.utensilioDao.atualizar(utensilio);
	}
	
	@Override
	public Utensilio buscarPorId(int id) {
		return this.utensilioDao.buscarPorId(id);
	}
 

}



