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
import controller.api.ProdutoController;
import controller.api.VendaController;
import controller.impl.ProdutoControllerImpl;
import controller.impl.VendaControllerImpl;
import model.entidade.Venda;
import java.time.LocalDate; 
import java.time.format.DateTimeFormatter;

@SuppressWarnings("serial")
public class VendaTelaLista extends JFrame {

    private JTable table;
    private DefaultTableModel defaultTableModel;
    private VendaController vendaController;
    @SuppressWarnings("unused")
    private ProdutoController produtoController;

    public VendaTelaLista() {
        this.vendaController = new VendaControllerImpl();
        this.produtoController = new ProdutoControllerImpl();
        setTitle("Vendas Registradas");
        setSize(new Dimension(800, 600));
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);
        table.setPreferredSize(new Dimension(900, 450));
        table.setSize(new Dimension(900, 400));

        defaultTableModel.addColumn("ID");
        defaultTableModel.addColumn("DATA DA VENDA");
        defaultTableModel.addColumn("CLIENTE");
        defaultTableModel.addColumn("LOTE");
        defaultTableModel.addColumn("QUANTIDADE");
        defaultTableModel.addColumn("VALOR TOTAL");
        defaultTableModel.addColumn("NOTA FISCAL");

        List<Venda> vendas = this.vendaController.listarTodos();
        for (Venda venda : vendas) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
            String dataVendaFormatada = venda.getDataVenda().format(formatter); 
            Object[] linha = new Object[] { venda.getId(), dataVendaFormatada, venda.getCliente(),
                    venda.getProduto().getLote(), venda.getQuantidade(), venda.getValorTotal(), venda.getNotaFiscal() }; 
            defaultTableModel.addRow(linha);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 400));
        add(scrollPane);

        criarBotao("Cadastrar", new BotaoCadastrarVenda());
        criarBotao("Editar", new BotaoEditarVenda(this.vendaController, this.table));
        criarBotao("Excluir", new BotaoExcluirVenda(vendaController));
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

    private class BotaoCadastrarVenda implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new VendaTelaCriarEditar();
            setVisible(false);
        }
    }

    private class BotaoEditarVenda implements ActionListener {

        private VendaController vendaController;
        private JTable table;

        public BotaoEditarVenda(VendaController vendaController, JTable table) {
            this.vendaController = vendaController;
            this.table = table;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = table.getSelectedRow();

            if (linhaSelecionada > -1) {
                int id = Integer.parseInt(table.getModel().getValueAt(linhaSelecionada, 0).toString());
                Venda venda = this.vendaController.buscarPorId(id);

                // Obtenha os valores dos campos de data como strings
                String dataVendaStr = table.getModel().getValueAt(linhaSelecionada, 1).toString();
                

                // Converter as strings para LocalDate
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataVenda = LocalDate.parse(dataVendaStr, formatter);
               

                // Configure as datas no produto
                venda.setDataVenda(dataVenda);
                

                // Atualize o produto no banco de dados
                Venda vendaAtualizada = this.vendaController.atualizar(venda);
                new VendaTelaCriarEditar(vendaAtualizada);

                setVisible(false);

            } else {
                JOptionPane.showMessageDialog(null, "Selecione uma venda para edição!");
            }
        }
    }

    private class BotaoExcluirVenda implements ActionListener {

        private VendaController vendaController;

        public BotaoExcluirVenda(VendaController vendaController) {
            this.vendaController = vendaController;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = table.getSelectedRow();
            if (linhaSelecionada > -1) {
                int id = Integer.parseInt(table.getModel().getValueAt(linhaSelecionada, 0).toString());
                vendaController.excluir(id);
                defaultTableModel.removeRow(linhaSelecionada);
                JOptionPane.showMessageDialog(null, "Venda excluída com sucesso!");
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