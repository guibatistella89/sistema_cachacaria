package model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.dao.api.IngredienteDao;
import model.entidade.Ingrediente;


public class IngredienteDaoImpl implements IngredienteDao {

	
	
    @Override
    public void salvar(Ingrediente ingrediente) {
        try {
            PreparedStatement ps = Conexao.getInstance()
                    .getConnection()
                    .prepareStatement("INSERT INTO ingrediente (id, nome, dataCompra, lote, kg_l, fabricacao, validade, fornecedor, "
                    		+ "valorTotal, notaFiscal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, ingrediente.getId());
            ps.setString(2, ingrediente.getNome());
            ps.setObject(3, ingrediente.getDataCompra());
            ps.setString(4, ingrediente.getLote());
            ps.setString(5, ingrediente.getKg_l());
            ps.setObject(6, ingrediente.getFabricacao());
            ps.setObject(7, ingrediente.getValidade());
            ps.setString(8, ingrediente.getFornecedor());
            ps.setBigDecimal(9, ingrediente.getValorTotal());
            ps.setString(10, ingrediente.getNotaFiscal());
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
                    .prepareStatement("DELETE FROM ingrediente WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Ingrediente atualizar(Ingrediente ingrediente) {
        try {
            PreparedStatement ps = Conexao.getInstance()
                    .getConnection()
                    .prepareStatement("UPDATE ingrediente SET nome = ?, dataCompra = ?, lote = ?, kg_l = ?, fabricacao = ?, "
                    		+ "validade = ?, fornecedor = ?, valorTotal = ?, notaFiscal = ? WHERE id = ?");

            ps.setString(1, ingrediente.getNome());
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            String dataCompraFormatted = ingrediente.getDataCompra().format(formatter);
            ps.setString(2, dataCompraFormatted);

            ps.setString(3, ingrediente.getLote());
            ps.setString(4, ingrediente.getKg_l());
            
            String fabricacaoFormatted = ingrediente.getFabricacao().format(formatter);
            ps.setString(5, fabricacaoFormatted);
            
            String validadeFormatted = ingrediente.getValidade().format(formatter);
            ps.setString(6, validadeFormatted);

            ps.setString(7, ingrediente.getFornecedor());
            ps.setBigDecimal(8, ingrediente.getValorTotal());
            ps.setString(9, ingrediente.getNotaFiscal());
            ps.setInt(10, ingrediente.getId());

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                ingrediente = buscarPorId(ingrediente.getId());
            } else {
                System.out.println("Nenhuma linha afetada pela atualização.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingrediente;
    }
 
   
    @Override
    public List<Ingrediente> listarTodos() {
        List<Ingrediente> ingredientes = new ArrayList<>();
        try {
            PreparedStatement ps = Conexao.getInstance().getConnection().prepareStatement(
                "SELECT id, nome, dataCompra, lote, kg_l, fabricacao, validade, fornecedor, valorTotal, notaFiscal " +
                "FROM ingrediente " +
                "ORDER BY nome"
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ingrediente ingrediente = new Ingrediente();
                ingrediente.setId(rs.getInt("id"));
                ingrediente.setNome(rs.getString("nome"));
                ingrediente.setDataCompra(rs.getObject("dataCompra", LocalDate.class));
                ingrediente.setLote(rs.getString("lote"));
                ingrediente.setKg_l(rs.getString("kg_l"));
                ingrediente.setFabricacao(rs.getObject("fabricacao", LocalDate.class));
                ingrediente.setValidade(rs.getObject("validade", LocalDate.class));
                ingrediente.setFornecedor(rs.getString("fornecedor"));
                ingrediente.setValorTotal(rs.getBigDecimal("valorTotal"));
                ingrediente.setNotaFiscal(rs.getString("notaFiscal"));
                ingredientes.add(ingrediente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar a consulta SQL: " + e.getMessage());
            e.printStackTrace();
        }
        return ingredientes;
    }

    @Override
    public Ingrediente buscarPorId(int id) {
        Ingrediente ingrediente = null;
        try {
            PreparedStatement ps = Conexao.getInstance()
                    .getConnection()
                    .prepareStatement(
                        "SELECT id, nome, dataCompra, lote, kg_l, fabricacao, validade, fornecedor, valorTotal, notaFiscal " +
                        "FROM ingrediente WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ingrediente = new Ingrediente();
                ingrediente.setId(rs.getInt("id"));
                ingrediente.setNome(rs.getString("nome"));
                ingrediente.setDataCompra(rs.getObject("dataCompra", LocalDate.class));
                ingrediente.setLote(rs.getString("lote"));
                ingrediente.setKg_l(rs.getString("kg_l"));
                ingrediente.setFabricacao(rs.getObject("fabricacao", LocalDate.class));
                ingrediente.setValidade(rs.getObject("validade", LocalDate.class));
                ingrediente.setFornecedor(rs.getString("fornecedor"));
                ingrediente.setValorTotal(rs.getBigDecimal("valorTotal"));
                ingrediente.setNotaFiscal(rs.getString("notaFiscal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingrediente;
    }
}


   

    

