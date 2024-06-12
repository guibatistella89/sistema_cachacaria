package model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.dao.api.VendaDao;
import model.entidade.Produto;
import model.entidade.Venda;

public class VendaDaoImpl implements VendaDao {

    @Override
    public void salvar(Venda venda) {
        try {
            PreparedStatement ps = Conexao.getInstance()
            		.getConnection()
                    .prepareStatement("INSERT INTO venda (dataVenda, cliente, produto_id, quantidade, valorTotal, notaFiscal) "
                    		+ "VALUES (?, ?, ?, ?, ?, ?)");
            ps.setObject(1, venda.getDataVenda());
            ps.setString(2, venda.getCliente());
            ps.setInt(3, venda.getProduto().getId());
            ps.setInt(4, venda.getQuantidade());
            ps.setBigDecimal(5, venda.getValorTotal());
            ps.setString(6, venda.getNotaFiscal());
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
                    .prepareStatement("DELETE FROM venda WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Venda atualizar(Venda venda) {
        try {
            PreparedStatement ps = Conexao.getInstance()
                    .getConnection()
                    .prepareStatement
                    ("UPDATE venda SET dataVenda = ?, cliente = ?, produto_id = ?, quantidade = ?, valorTotal = ?, notaFiscal = ? "
                    		+ "WHERE id = ?");
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dataVendaFormatted = venda.getDataVenda().format(formatter);
            ps.setString(1, dataVendaFormatted);
            ps.setString(2, venda.getCliente());
            ps.setInt(3, venda.getProduto().getId());
            ps.setInt(4, venda.getQuantidade());
            ps.setBigDecimal(5, venda.getValorTotal());
            ps.setString(6, venda.getNotaFiscal());
            ps.setInt(7, venda.getId());

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                venda = buscarPorId(venda.getId());
            } else {
                System.out.println("Nenhuma linha afetada pela atualização.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venda;
    }

    
    @Override
    public List<Venda> listarTodos() {
        List<Venda> vendas = new ArrayList<>();
        try {
            PreparedStatement ps = Conexao.getInstance().getConnection().prepareStatement(
                    "SELECT v.id AS vId, v.dataVenda AS vDataVenda, v.cliente AS vCliente, p.lote AS pLote, v.quantidade AS vQuantidade, "
                    + "v.valorTotal AS vValorTotal, v.notaFiscal AS vNotaFiscal " +
                    "FROM venda v INNER JOIN produto p ON v.produto_id = p.id "
            		);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getInt("vId"));
                venda.setDataVenda(rs.getObject("vDataVenda", LocalDate.class));
                venda.setCliente(rs.getString("vCliente"));
                Produto produto = new Produto();
                produto.setLote(rs.getString("pLote"));
               
                venda.setProduto(produto);
                venda.setQuantidade(rs.getInt("vQuantidade"));
                venda.setValorTotal(rs.getBigDecimal("vValorTotal"));
                venda.setNotaFiscal(rs.getString("vNotaFiscal"));
                vendas.add(venda);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar a consulta SQL: " + e.getMessage());
            e.printStackTrace();
        }
        return vendas;
    }

    @Override
    public Venda buscarPorId(int id) {
        Venda venda = null;
        try {
            PreparedStatement ps = Conexao.getInstance()
                    .getConnection()
                    .prepareStatement(
                        "SELECT v.id AS vId, v.dataVenda AS vDataVenda, v.cliente AS vCliente, " +
                        "v.produto_id AS vProduto_id, v.quantidade AS vQuantidade, " +
                        "v.valorTotal AS vValorTotal, v.notaFiscal AS vNotaFiscal, " +
                        "p.lote AS pLote " +
                        "FROM venda v " +
                        "INNER JOIN produto p ON v.produto_id = p.id " +
                        "WHERE v.id = ?"
                    );
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    venda = new Venda();
                    venda.setId(rs.getInt("vId"));
                    venda.setDataVenda(rs.getObject("vDataVenda", LocalDate.class));
                    venda.setCliente(rs.getString("vCliente"));
                    Produto produto = new Produto();
                    produto.setId(rs.getInt("vProduto_id"));
                    produto.setLote(rs.getString("pLote"));
                    venda.setProduto(produto);
                    venda.setQuantidade(rs.getInt("vQuantidade"));
                    venda.setValorTotal(rs.getBigDecimal("vValorTotal"));
                    venda.setNotaFiscal(rs.getString("vNotaFiscal"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venda;
    }

    
    @Override
    public int buscarQtdPorProduto(int produto) {
        int result = 0;
        try {
            PreparedStatement ps = Conexao.getInstance()
                    .getConnection()
                    .prepareStatement("SELECT COUNT(DISTINCT produto_id) AS count FROM venda WHERE produto_id = ?");
            ps.setInt(1, produto);
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
