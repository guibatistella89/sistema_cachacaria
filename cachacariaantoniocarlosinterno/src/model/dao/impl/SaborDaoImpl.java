package model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.api.SaborDao;
import model.entidade.Sabor;

public class SaborDaoImpl implements SaborDao {

	@Override
	public void salvar(Sabor sabor) {
		try {
			PreparedStatement ps = Conexao.getInstance()
					.getConnection()
					.prepareStatement("INSERT INTO sabor (tipoSabor, validade, codigoBarra) VALUES (?, ?, ?)");
			ps.setString(1, sabor.getTipoSabor());
			ps.setBoolean(2, sabor.isValidade());
			ps.setString(3, sabor.getCodigoBarra());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(int id) {
		try {
			PreparedStatement ps = Conexao.getInstance()
					.getConnection()
					.prepareStatement("DELETE FROM sabor WHERE id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Sabor> listarTodos() {
		List<Sabor> sabores = new ArrayList<Sabor>();
		try {
			PreparedStatement ps = Conexao.getInstance().getConnection().prepareStatement("SELECT * FROM sabor order by tipoSabor");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sabor sabor = new Sabor();
				sabor.setId(rs.getInt("id"));
				sabor.setTipoSabor(rs.getString("tipoSabor"));
				sabor.setValidade(rs.getBoolean("validade"));
				sabor.setCodigoBarra(rs.getString("codigoBarra"));
				sabores.add(sabor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sabores;
	}

	@Override
	public Sabor buscarPorId(int id) {
		Sabor sabor = new Sabor();
		try {
			PreparedStatement ps = Conexao.getInstance()
					.getConnection()
					.prepareStatement("SELECT id, tipoSabor, validade, codigoBarra FROM sabor WHERE id = ?");
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					sabor.setId(rs.getInt("id"));
					sabor.setTipoSabor(rs.getString("tipoSabor"));
					sabor.setValidade(rs.getBoolean("validade"));
					sabor.setCodigoBarra(rs.getString("codigoBarra"));
					break;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sabor;
	}

	@Override
	public Sabor atualizar(Sabor sabor) {
		try {
			PreparedStatement ps = Conexao.getInstance()
					.getConnection()
					.prepareStatement("UPDATE sabor SET tipoSabor = ?, validade = ?, codigoBarra = ? WHERE id = ?");
			
			
			ps.setString(1, sabor.getTipoSabor());
			ps.setBoolean(2, sabor.isValidade());
			ps.setString(3, sabor.getCodigoBarra());
			ps.setInt(4, sabor.getId());
						
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sabor;
	}

}
