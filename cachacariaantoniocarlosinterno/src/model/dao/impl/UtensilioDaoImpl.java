package model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.dao.api.UtensilioDao;
import model.entidade.Utensilio;


public class UtensilioDaoImpl implements UtensilioDao {

	
	
    @Override
    public void salvar(Utensilio utensilio) {
        try {
            PreparedStatement ps = Conexao.getInstance()
                    .getConnection()
                    .prepareStatement("INSERT INTO utensilio (id, nome, dataCompra, lote, quantidade, fornecedor, "
                    		+ "valorTotal, notaFiscal) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, utensilio.getId());
            ps.setString(2, utensilio.getNome());
            ps.setObject(3, utensilio.getDataCompra());
            ps.setString(4, utensilio.getLote());
            ps.setString(5, utensilio.getQuantidade());
            ps.setString(6, utensilio.getFornecedor());
            ps.setBigDecimal(7, utensilio.getValorTotal());
            ps.setString(8, utensilio.getNotaFiscal());
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
                    .prepareStatement("DELETE FROM utensilio WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Utensilio atualizar(Utensilio utensilio) {
        try {
            PreparedStatement ps = Conexao.getInstance()
                    .getConnection()
                    .prepareStatement("UPDATE utensilio SET nome = ?, dataCompra = ?, lote = ?, quantidade = ?, fornecedor = ?, "
                    		+ "valorTotal = ?, notaFiscal = ? WHERE id = ?");

            ps.setString(1, utensilio.getNome());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dataCompraFormatted = utensilio.getDataCompra().format(formatter);
            ps.setString(2, dataCompraFormatted);
            ps.setString(3, utensilio.getLote());
            ps.setString(4, utensilio.getQuantidade());
            ps.setString(5, utensilio.getFornecedor());
            ps.setBigDecimal(6, utensilio.getValorTotal());
            ps.setString(7, utensilio.getNotaFiscal());
            ps.setInt(8, utensilio.getId());

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
            	utensilio = buscarPorId(utensilio.getId());
            } else {
                System.out.println("Nenhuma linha afetada pela atualização.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utensilio;
    }
 
   
    @Override
    public List<Utensilio> listarTodos() {
        List<Utensilio> utensilios = new ArrayList<>();
        try {
            PreparedStatement ps = Conexao.getInstance().getConnection().prepareStatement(
                "SELECT id, nome, dataCompra, lote, quantidade, fornecedor, valorTotal, notaFiscal " +
                "FROM utensilio " +
                "ORDER BY nome"
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	Utensilio utensilio = new Utensilio();
            	utensilio.setId(rs.getInt("id"));
            	utensilio.setNome(rs.getString("nome"));
            	utensilio.setDataCompra(rs.getObject("dataCompra", LocalDate.class));
                utensilio.setLote(rs.getString("lote"));
                utensilio.setQuantidade(rs.getString("quantidade"));
                utensilio.setFornecedor(rs.getString("fornecedor"));
                utensilio.setValorTotal(rs.getBigDecimal("valorTotal"));
                utensilio.setNotaFiscal(rs.getString("notaFiscal"));
                utensilios.add(utensilio);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar a consulta SQL: " + e.getMessage());
            e.printStackTrace();
        }
        return utensilios;
    }

    @Override
    public Utensilio buscarPorId(int id) {
    	Utensilio utensilio = null;
        try {
            PreparedStatement ps = Conexao.getInstance()
                    .getConnection()
                    .prepareStatement(
                        "SELECT id, nome, dataCompra, lote, quantidade, fornecedor, valorTotal, notaFiscal " +
                        "FROM utensilio WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	utensilio = new Utensilio();
            	utensilio.setId(rs.getInt("id"));
                utensilio.setNome(rs.getString("nome"));
                utensilio.setDataCompra(rs.getObject("dataCompra", LocalDate.class));
                utensilio.setLote(rs.getString("lote"));
                utensilio.setQuantidade(rs.getString("quantidade"));
                utensilio.setFornecedor(rs.getString("fornecedor"));
                utensilio.setValorTotal(rs.getBigDecimal("valorTotal"));
                utensilio.setNotaFiscal(rs.getString("notaFiscal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utensilio;
    }
}


   

    

