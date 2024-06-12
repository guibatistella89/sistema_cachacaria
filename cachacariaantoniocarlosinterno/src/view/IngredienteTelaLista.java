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

import controller.api.IngredienteController;
import controller.impl.IngredienteControllerImpl;
import model.entidade.Ingrediente;
import java.time.LocalDate; 
import java.time.format.DateTimeFormatter;

@SuppressWarnings("serial")
public class IngredienteTelaLista extends JFrame {

    private JTable table;
    private DefaultTableModel defaultTableModel;
    private IngredienteController ingredienteController;

    public IngredienteTelaLista() {
        this.ingredienteController = new IngredienteControllerImpl();
        setTitle("Registro de Compras de Ingredientes");
        setSize(new Dimension(800, 600));
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);
        table.setPreferredSize(new Dimension(900, 450));
        table.setSize(new Dimension(900, 400));

        defaultTableModel.addColumn("CÓDIGO");
        defaultTableModel.addColumn("NOME");
        defaultTableModel.addColumn("DATA COMPRA");
        defaultTableModel.addColumn("LOTE");
        defaultTableModel.addColumn("Kg / L");
        defaultTableModel.addColumn("FABRICAÇÃO");
        defaultTableModel.addColumn("VALIDADE");
        defaultTableModel.addColumn("FORNECEDOR");
        defaultTableModel.addColumn("VALOR TOTAL");
        defaultTableModel.addColumn("NOTA FISCAL");

        List<Ingrediente> ingredientes = this.ingredienteController.listarTodos();
        for (Ingrediente ingrediente : ingredientes) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
            String dataCompraFormatada = ingrediente.getDataCompra().format(formatter); 
            String fabricacaoFormatada = ingrediente.getFabricacao().format(formatter); 
            String validadeFormatada = ingrediente.getFabricacao().format(formatter); 
            
            Object[] linha = new Object[] { ingrediente.getId(), ingrediente.getNome(), dataCompraFormatada,
            		ingrediente.getLote(), ingrediente.getKg_l(), fabricacaoFormatada, validadeFormatada, 
            		ingrediente.getFornecedor(), ingrediente.getValorTotal(), ingrediente.getNotaFiscal() }; 
            defaultTableModel.addRow(linha);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 400));
        add(scrollPane);

        criarBotao("Cadastrar", new BotaoCadastrarIngrediente());
        criarBotao("Editar", new BotaoEditarIngrediente(this.ingredienteController, this.table));
        criarBotao("Excluir", new BotaoExcluirIngrediente(ingredienteController));
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

    private class BotaoCadastrarIngrediente implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new IngredienteTelaCriarEditar();
            setVisible(false);
        }
    }

    private class BotaoEditarIngrediente implements ActionListener {

        private IngredienteController ingredienteController;
        private JTable table;

        public BotaoEditarIngrediente(IngredienteController ingredienteController, JTable table) {
            this.ingredienteController = ingredienteController;
            this.table = table;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = table.getSelectedRow();

            if (linhaSelecionada > -1) {
                int id = Integer.parseInt(table.getModel().getValueAt(linhaSelecionada, 0).toString());
                Ingrediente ingrediente = this.ingredienteController.buscarPorId(id);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                String dataCompraStr = table.getModel().getValueAt(linhaSelecionada, 2).toString();
                LocalDate dataCompra = LocalDate.parse(dataCompraStr, formatter);

                String fabricacaoStr = table.getModel().getValueAt(linhaSelecionada, 5).toString();
                LocalDate fabricacao = LocalDate.parse(fabricacaoStr, formatter);

                String validadeStr = table.getModel().getValueAt(linhaSelecionada, 6).toString();
                LocalDate validade = LocalDate.parse(validadeStr, formatter);

                ingrediente.setDataCompra(dataCompra);
                ingrediente.setFabricacao(fabricacao);
                ingrediente.setValidade(validade);

                Ingrediente ingredienteAtualizado = this.ingredienteController.atualizar(ingrediente);
                new IngredienteTelaCriarEditar(ingredienteAtualizado);

                setVisible(false);

            } else {
                JOptionPane.showMessageDialog(null, "Selecione um ingrediente para edição!");
            }
        }
    }

    private class BotaoExcluirIngrediente implements ActionListener {

        private IngredienteController ingredienteController;

        public BotaoExcluirIngrediente(IngredienteController ingredienteController) {
            this.ingredienteController = ingredienteController;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = table.getSelectedRow();
            if (linhaSelecionada > -1) {
                int id = Integer.parseInt(table.getModel().getValueAt(linhaSelecionada, 0).toString());
                ingredienteController.excluir(id);
                defaultTableModel.removeRow(linhaSelecionada);
                JOptionPane.showMessageDialog(null, "Ingrediente excluído com sucesso!");
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

