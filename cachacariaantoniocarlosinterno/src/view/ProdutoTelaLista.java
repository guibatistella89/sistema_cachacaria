package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.api.SaborController;
import controller.api.ProdutoController;
import controller.impl.SaborControllerImpl;
import controller.impl.ProdutoControllerImpl;
import model.entidade.Produto;
import java.time.LocalDate; 
import java.time.format.DateTimeFormatter;

@SuppressWarnings("serial")
public class ProdutoTelaLista extends JFrame {

    private JTable table;
    private DefaultTableModel defaultTableModel;
    private ProdutoController produtoController;
    @SuppressWarnings("unused")
    private SaborController saborController;

    public ProdutoTelaLista() {
        this.produtoController = new ProdutoControllerImpl();
        this.saborController = new SaborControllerImpl();
        setTitle("Produtos em Estoque");
        setSize(new Dimension(800, 600));
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);
        table.setPreferredSize(new Dimension(900, 450));
        table.setSize(new Dimension(900, 400));

        defaultTableModel.addColumn("CÓDIGO");
        defaultTableModel.addColumn("NOME");
        defaultTableModel.addColumn("LOTE");
        defaultTableModel.addColumn("FABRICAÇÃO");
        defaultTableModel.addColumn("VALIDADE");
        defaultTableModel.addColumn("PRODUZIDAS");
        defaultTableModel.addColumn("RESTANTES");

        List<Produto> produtos = this.produtoController.listarTodos();
        for (Produto produto : produtos) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
            String fabricacaoFormatada = produto.getFabricacao().format(formatter); 
            String validadeFormatada = produto.getSabor().isValidade() ? "1 ano" : "Indeterminada"; 
            Object[] linha = new Object[] { produto.getId(), produto.getSabor().getTipoSabor(), produto.getLote(),
                    fabricacaoFormatada, validadeFormatada, produto.getQuantidade(), produto.getRestante() }; 
            defaultTableModel.addRow(linha);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 400));
        add(scrollPane);

        criarBotao("Cadastrar", new BotaoCadastrarProduto());
        criarBotao("Editar", new BotaoEditarProduto(this.produtoController, this.table));
        criarBotao("Excluir", new BotaoExcluirProduto(produtoController));
        criarBotao("Voltar", new BotaoVoltar());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void criarBotao(String label, ActionListener listener) {
        JButton botao = new JButton(label);
        botao.setPreferredSize(new Dimension(150, 50));
        botao.addActionListener(listener);
        add(botao);
    }

    private class BotaoCadastrarProduto implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new ProdutoTelaCriarEditar();
            setVisible(false);
        }
    }

    private class BotaoEditarProduto implements ActionListener {

        private ProdutoController produtoController;
        private JTable table;

        public BotaoEditarProduto(ProdutoController produtoController, JTable table) {
            this.produtoController = produtoController;
            this.table = table;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = table.getSelectedRow();

            if (linhaSelecionada > -1) {
                int id = Integer.parseInt(table.getModel().getValueAt(linhaSelecionada, 0).toString());
                Produto produto = this.produtoController.buscarPorId(id);
                String fabricacaoStr = table.getModel().getValueAt(linhaSelecionada, 3).toString();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate fabricacao = LocalDate.parse(fabricacaoStr, formatter);
                produto.setFabricacao(fabricacao);
                Produto produtoAtualizado = this.produtoController.atualizar(produto);
                new ProdutoTelaCriarEditar(produtoAtualizado);

                setVisible(false);

            } else {
                JOptionPane.showMessageDialog(null, "Selecione um produto para edição!");
            }
        }
    }

    private class BotaoExcluirProduto implements ActionListener {

        private ProdutoController produtoController;

        public BotaoExcluirProduto(ProdutoController produtoController) {
            this.produtoController = produtoController;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = table.getSelectedRow();
            if (linhaSelecionada > -1) {
                int id = Integer.parseInt(table.getModel().getValueAt(linhaSelecionada, 0).toString());
                produtoController.excluir(id);
                defaultTableModel.removeRow(linhaSelecionada);
                JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Selecione uma linha para exclusão!");
            }
        }

    }

    private class BotaoVoltar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new TelaInicial();
            setVisible(false);
        }

    }
}
