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

import controller.api.UtensilioController;
import controller.impl.UtensilioControllerImpl;
import model.entidade.Utensilio;
import java.time.LocalDate; 
import java.time.format.DateTimeFormatter;

@SuppressWarnings("serial")
public class UtensilioTelaLista extends JFrame {

    private JTable table;
    private DefaultTableModel defaultTableModel;
    private UtensilioController utensilioController;

    public UtensilioTelaLista() {
        this.utensilioController = new UtensilioControllerImpl();
        setTitle("Registro de Compras de Utensílios");
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
        defaultTableModel.addColumn("QUANTIDADE");
        defaultTableModel.addColumn("FORNECEDOR");
        defaultTableModel.addColumn("VALOR TOTAL");
        defaultTableModel.addColumn("NOTA FISCAL");

        List<Utensilio> utensilios = this.utensilioController.listarTodos();
        for (Utensilio utensilio : utensilios) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
            String dataCompraFormatada = utensilio.getDataCompra().format(formatter); 
            
            
            Object[] linha = new Object[] { utensilio.getId(), utensilio.getNome(), dataCompraFormatada,
            		utensilio.getLote(), utensilio.getQuantidade(), utensilio.getFornecedor(), utensilio.getValorTotal(), 
            		utensilio.getNotaFiscal() }; 
            defaultTableModel.addRow(linha);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 400));
        add(scrollPane);

        criarBotao("Cadastrar", new BotaoCadastrarUtensilio());
        criarBotao("Editar", new BotaoEditarUtensilio(this.utensilioController, this.table));
        criarBotao("Excluir", new BotaoExcluirUtensilio(utensilioController));
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

    private class BotaoCadastrarUtensilio implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new UtensilioTelaCriarEditar();
            setVisible(false);
        }
    }

    private class BotaoEditarUtensilio implements ActionListener {

        private UtensilioController utensilioController;
        private JTable table;

        public BotaoEditarUtensilio(UtensilioController utensilioController, JTable table) {
            this.utensilioController = utensilioController;
            this.table = table;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = table.getSelectedRow();

            if (linhaSelecionada > -1) {
                int id = Integer.parseInt(table.getModel().getValueAt(linhaSelecionada, 0).toString());
                Utensilio utensilio = this.utensilioController.buscarPorId(id);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dataCompraStr = table.getModel().getValueAt(linhaSelecionada, 2).toString();
                LocalDate dataCompra = LocalDate.parse(dataCompraStr, formatter);
                utensilio.setDataCompra(dataCompra);
                Utensilio utensilioAtualizado = this.utensilioController.atualizar(utensilio);
                new UtensilioTelaCriarEditar(utensilioAtualizado);

                setVisible(false);

            } else {
                JOptionPane.showMessageDialog(null, "Selecione um utensílio para edição!");
            }
        }
    }

    private class BotaoExcluirUtensilio implements ActionListener {

        private UtensilioController utensilioController;

        public BotaoExcluirUtensilio(UtensilioController utensilioController) {
            this.utensilioController = utensilioController;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = table.getSelectedRow();
            if (linhaSelecionada > -1) {
                int id = Integer.parseInt(table.getModel().getValueAt(linhaSelecionada, 0).toString());
                utensilioController.excluir(id);
                defaultTableModel.removeRow(linhaSelecionada);
                JOptionPane.showMessageDialog(null, "Utensílio excluído com sucesso!");
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

