package model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.dao.api.ProdutoDao;
import model.entidade.Sabor;
import model.entidade.Produto;

public class ProdutoDaoImpl implements ProdutoDao {

	
	
    @Override
    public void salvar(Produto produto) {
        try {
            PreparedStatement ps = Conexao.getInstance()
                    .getConnection()
                    .prepareStatement("INSERT INTO produto (sabor_id, lote, fabricacao, quantidade, restante) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, produto.getSabor().getId());
            ps.setString(2, produto.getLote());
            ps.setObject(3, produto.getFabricacao());
            ps.setInt(4, produto.getQuantidade());
            ps.setInt(5, produto.getRestante());
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
                    .prepareStatement("DELETE FROM produto WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Produto atualizar(Produto produto) {
        try {
            PreparedStatement ps = Conexao.getInstance()
                    .getConnection()
                    .prepareStatement("UPDATE produto SET sabor_id = ?, lote = ?, fabricacao = ?, quantidade = ?, restante = ? WHERE id = ?");

            ps.setInt(1, produto.getSabor().getId());
            ps.setString(2, produto.getLote());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fabricacaoFormatted = produto.getFabricacao().format(formatter);
            ps.setString(3, fabricacaoFormatted);
            ps.setInt(4, produto.getQuantidade());
            ps.setInt(5, produto.getRestante());
            ps.setInt(6, produto.getId());

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                produto = buscarPorId(produto.getId());
            } else {
                System.out.println("Nenhuma linha afetada pela atualização.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produto;
    }

   
    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        try {
            
        	PreparedStatement ps = Conexao.getInstance().getConnection().prepareStatement(
        		    "SELECT p.id AS pId, s.tipoSabor AS stipoSabor, p.lote AS pLote, DATE_FORMAT(p.fabricacao, '%d/%m/%Y') AS pFabricacao, "
        		    + "s.validade AS sValidade, p.quantidade AS pQuantidade, p.restante AS pRestante " +
        		    "FROM produto p INNER JOIN sabor s ON p.sabor_id = s.id " +
        		    "ORDER BY stipoSabor, pLote"
        		);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("pId"));
                Sabor sabor = new Sabor();
                sabor.setTipoSabor(rs.getString("stipoSabor"));
                produto.setLote(rs.getString("pLote"));
                String fabricacaoStr = rs.getString("pFabricacao");
                LocalDate fabricacao = LocalDate.parse(fabricacaoStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                produto.setFabricacao(fabricacao);
                sabor.setValidade(rs.getBoolean("sValidade"));
                produto.setSabor(sabor);
                produto.setQuantidade(rs.getInt("pQuantidade"));
                produto.setRestante(rs.getInt("pRestante"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar a consulta SQL: " + e.getMessage());
            e.printStackTrace();
        }
        return produtos;
    }
 
    @Override
    public Produto buscarPorId(int id) {
        Produto produto = null;
        try {
            PreparedStatement ps = Conexao.getInstance()
                    .getConnection()
                    .prepareStatement(
                            "SELECT p.id AS pId, p.sabor_id AS pSaborId, p.lote AS pLote, p.fabricacao AS pFabricacao, "
                            + "p.quantidade AS pQuantidade, p.restante AS pRestante, " +
                            "s.tipoSabor AS sTipoSabor, s.validade AS sValidade " +
                            "FROM produto p " +
                            "INNER JOIN sabor s ON p.sabor_id = s.id " +
                            "WHERE p.id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("pId"));
                produto.setLote(rs.getString("pLote"));
                produto.setFabricacao(rs.getObject("pFabricacao", LocalDate.class));
                Sabor sabor = new Sabor();
                sabor.setId(rs.getInt("pSaborId"));
                sabor.setTipoSabor(rs.getString("sTipoSabor"));
                sabor.setValidade(rs.getBoolean("sValidade"));
                produto.setSabor(sabor);
                produto.setQuantidade(rs.getInt("pQuantidade"));
                produto.setRestante(rs.getInt("pRestante"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produto;
    }

    @Override
    public int buscarQtdPorSabor(int sabor) {
        int result = 0;
        try {
            PreparedStatement ps = Conexao.getInstance()
                    .getConnection()
                    .prepareStatement("SELECT COUNT(DISTINCT sabor_id) AS count FROM produto WHERE sabor_id = ?");
            ps.setInt(1, sabor);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt("count");
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
