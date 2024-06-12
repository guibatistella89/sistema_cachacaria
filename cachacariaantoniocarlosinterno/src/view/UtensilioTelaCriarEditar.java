package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import controller.api.UtensilioController;
import controller.impl.UtensilioControllerImpl;
import model.entidade.Utensilio;


@SuppressWarnings("serial")
public class UtensilioTelaCriarEditar extends JFrame {

    private JPanel panel;
    private JTextField tfId;
    private JTextField tfNome;
    private JFormattedTextField tfDataCompra;
    private JTextField tfLote;
    private JTextField tfQuantidade;
    private JTextField tfFornecedor;
    private JTextField tfValorTotal;
    private JTextField tfNotaFiscal;
    private UtensilioController utensilioController;
    

    public UtensilioTelaCriarEditar() {
        this.utensilioController = new UtensilioControllerImpl();
        setTitle("Cadastro de Utensílios");

        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(1000, 1000));
        add(panel);
        this.tfNome = criarTextField("NOME");
        this.panel.add(this.tfNome);
        this.tfDataCompra = criarFormattedTextField("DATA DE COMPRA", "##/##/####");
        this.panel.add(this.tfDataCompra);
        this.tfLote = criarTextField("LOTE");
        this.panel.add(this.tfLote);
        this.tfQuantidade = criarTextField("QUANTIDADE");
        this.panel.add(this.tfQuantidade);
        this.tfFornecedor = criarTextField("FORNECEDOR");
        this.panel.add(this.tfFornecedor);
        this.tfValorTotal = criarTextField("VALOR TOTAL");
        this.panel.add(this.tfValorTotal);
        this.tfNotaFiscal = criarTextField("NOTA FISCAL");
        this.panel.add(this.tfNotaFiscal);


        criarBotao("Salvar", new BotaoSalvar());
        criarBotao("Voltar", new BotaoListar());
        criarBotao("Menu Principal", new BotaoVoltar());

        setSize(new Dimension(800, 750));
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(800, 750));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public UtensilioTelaCriarEditar(Utensilio utensilio) {
        this.utensilioController = new UtensilioControllerImpl();
        setTitle("Edição de Utensílios");

        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(900, 900));
        add(panel);
        
        this.tfId = criarTextField("CÓDIGO");
        this.tfId.setEditable(false);
        this.panel.add(this.tfId);
        this.tfNome = criarTextField("NOME");
        this.panel.add(this.tfNome);
        this.tfDataCompra = criarFormattedTextField("DATA DE COMPRA", "##/##/####");
        this.panel.add(this.tfDataCompra);
        this.tfLote = criarTextField("LOTE");
        this.panel.add(this.tfLote);
        this.tfQuantidade = criarTextField("QUANTIDADE");
        this.panel.add(this.tfQuantidade);
        this.tfFornecedor = criarTextField("FORNECEDOR");
        this.panel.add(this.tfFornecedor);
        this.tfValorTotal = criarTextField("VALOR TOTAL");
        this.panel.add(this.tfValorTotal);
        this.tfNotaFiscal = criarTextField("NOTA FISCAL");
        this.panel.add(this.tfNotaFiscal);

        criarBotao("Salvar", new BotaoEditar());
        criarBotao("Voltar", new BotaoListar());
        criarBotao("Menu Principal", new BotaoVoltar());

        setSize(new Dimension(800, 820));
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(800, 820));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

   
        tfId.setText(String.valueOf(utensilio.getId()));
        tfNome.setText(utensilio.getNome());
        tfDataCompra.setText(utensilio.getDataCompra().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        tfLote.setText(utensilio.getLote());
        tfQuantidade.setText(utensilio.getQuantidade());
        tfFornecedor.setText(String.valueOf(utensilio.getFornecedor()));
        tfValorTotal.setText(String.valueOf(utensilio.getValorTotal()));
        tfNotaFiscal.setText(String.valueOf(utensilio.getNotaFiscal()));
    }

    private JTextField criarTextField(String textoLabel) {
        JLabel label = new JLabel(textoLabel);
        label.setPreferredSize(new Dimension(700, 25));
        this.panel.add(label);

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(700, 25));
        return textField;
    }

    private JFormattedTextField criarFormattedTextField(String textoLabel, String mascara) {
        JLabel label = new JLabel(textoLabel);
        label.setPreferredSize(new Dimension(700, 25));
        this.panel.add(label);

        try {
            MaskFormatter formatador = new MaskFormatter(mascara);
            JFormattedTextField textField = new JFormattedTextField(formatador);
            textField.setPreferredSize(new Dimension(700, 25));
            return textField;
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Erro ao utilizar o formatador");
            return null;
        }
    }

    private void criarBotao(String textoLabel, ActionListener listener) {
        JButton botao = new JButton(textoLabel);
        botao.setPreferredSize(new Dimension(450, 40));
        botao.addActionListener(listener);
        this.panel.add(botao);
    }

    
    private void criarUtensilio() {
        try {
        	Utensilio utensilio = new Utensilio();
        	utensilio.setNome(tfNome.getText());
        	utensilio.setLote(tfLote.getText());
        	utensilio.setQuantidade(tfQuantidade.getText());
            utensilio.setFornecedor(tfFornecedor.getText());
            utensilio.setValorTotal(new BigDecimal(tfValorTotal.getText()));
            utensilio.setNotaFiscal(tfNotaFiscal.getText());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate dataCompra = LocalDate.parse(tfDataCompra.getText(), formatter);
            utensilio.setDataCompra(dataCompra);

            utensilioController.salvar(utensilio);
            JOptionPane.showMessageDialog(null, "Utensílio salvo com sucesso!");
            dispose();
            new UtensilioTelaLista();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Tipo numérico errado!");
            e.printStackTrace();
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de data inválido. Use o formato dd/MM/yyyy.");
            e.printStackTrace();
        }
    }


    private class BotaoSalvar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            criarUtensilio();
			setVisible(false);
			
        }

    }

    private class BotaoEditar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
			editarObjetoUtensilio();
			setVisible(false);
            new UtensilioTelaLista();
        }
    }

    private void editarObjetoUtensilio() {
        try {
            int id = Integer.parseInt(tfId.getText());
            String nome = tfNome.getText();
            LocalDate dataCompra = LocalDate.parse(tfDataCompra.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String lote = tfLote.getText();
            String quantidade = tfQuantidade.getText();
            String fornecedor = tfFornecedor.getText();
            BigDecimal valorTotal = new BigDecimal(tfValorTotal.getText());
            String notaFiscal = tfNotaFiscal.getText();

            Utensilio utensilio = new Utensilio();
            utensilio.setId(id);
            utensilio.setNome(nome);
            utensilio.setDataCompra(dataCompra);
            utensilio.setLote(lote);
            utensilio.setQuantidade(quantidade);
            utensilio.setFornecedor(fornecedor);
            utensilio.setValorTotal(valorTotal);
            utensilio.setNotaFiscal(notaFiscal);

            utensilioController.atualizar(utensilio);

            JOptionPane.showMessageDialog(null, "Utensílio atualizado com sucesso!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro: Tipo numérico errado.");
            e.printStackTrace();
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Erro: Formato de data inválido. Use o formato dd/MM/yyyy.");
            e.printStackTrace();
        }
    }


     
    private class BotaoVoltar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new TelaInicial();
            setVisible(false);
        }
    }

    private class BotaoListar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new UtensilioTelaLista();
            setVisible(false);
        }
    }
}


