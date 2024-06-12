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
import controller.api.IngredienteController;
import controller.impl.IngredienteControllerImpl;
import model.entidade.Ingrediente;


@SuppressWarnings("serial")
public class IngredienteTelaCriarEditar extends JFrame {

    private JPanel panel;
    private JTextField tfId;
    private JTextField tfNome;
    private JFormattedTextField tfDataCompra;
    private JTextField tfLote;
    private JTextField tfKg_l;
    private JFormattedTextField tfFabricacao;
    private JFormattedTextField tfValidade;
    private JTextField tfFornecedor;
    private JTextField tfValorTotal;
    private JTextField tfNotaFiscal;
    private IngredienteController ingredienteController;
    

    public IngredienteTelaCriarEditar() {
        this.ingredienteController = new IngredienteControllerImpl();
        setTitle("Cadastro de Ingredientes");

        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(1000, 1000));
        add(panel);
        this.tfNome = criarTextField("NOME");
        this.panel.add(this.tfNome);
        this.tfDataCompra = criarFormattedTextField("DATA DE COMPRA", "##/##/####");
        this.panel.add(this.tfDataCompra);
        this.tfLote = criarTextField("LOTE");
        this.panel.add(this.tfLote);
        this.tfKg_l = criarTextField("KG / L");
        this.panel.add(this.tfKg_l);
        this.tfFabricacao = criarFormattedTextField("DATA DE FABRICAÇÃO", "##/##/####");
        this.panel.add(this.tfFabricacao);
        this.tfValidade = criarFormattedTextField("DATA DE VALIDADE", "##/##/####");
        this.panel.add(this.tfValidade);
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

    public IngredienteTelaCriarEditar(Ingrediente ingrediente) {
        this.ingredienteController = new IngredienteControllerImpl();
        setTitle("Edição de Ingredientes");

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
        this.tfKg_l = criarTextField("KG / L");
        this.panel.add(this.tfKg_l);
        this.tfFabricacao = criarFormattedTextField("DATA DE FABRICAÇÃO", "##/##/####");
        this.panel.add(this.tfFabricacao);
        this.tfValidade = criarFormattedTextField("DATA DE VALIDADE", "##/##/####");
        this.panel.add(this.tfValidade);
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

   
        tfId.setText(String.valueOf(ingrediente.getId()));
        tfNome.setText(ingrediente.getNome());
        tfDataCompra.setText(ingrediente.getDataCompra().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        tfLote.setText(ingrediente.getLote());
        tfKg_l.setText(ingrediente.getKg_l());
        tfFabricacao.setText(ingrediente.getFabricacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        tfValidade.setText(ingrediente.getValidade().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        tfFornecedor.setText(String.valueOf(ingrediente.getFornecedor()));
        tfValorTotal.setText(String.valueOf(ingrediente.getValorTotal()));
        tfNotaFiscal.setText(String.valueOf(ingrediente.getNotaFiscal()));
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

    
    private void criarIngrediente() {
        try {
            Ingrediente ingrediente = new Ingrediente();
            ingrediente.setNome(tfNome.getText());
            ingrediente.setLote(tfLote.getText());
            ingrediente.setKg_l(tfKg_l.getText());
            ingrediente.setFornecedor(tfFornecedor.getText());
            ingrediente.setValorTotal(new BigDecimal(tfValorTotal.getText()));
            ingrediente.setNotaFiscal(tfNotaFiscal.getText());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate dataCompra = LocalDate.parse(tfDataCompra.getText(), formatter);
            ingrediente.setDataCompra(dataCompra);

            LocalDate fabricacao = LocalDate.parse(tfFabricacao.getText(), formatter);
            ingrediente.setFabricacao(fabricacao);

            LocalDate validade = LocalDate.parse(tfValidade.getText(), formatter);
            ingrediente.setValidade(validade);

            ingredienteController.salvar(ingrediente);
            JOptionPane.showMessageDialog(null, "Ingrediente salvo com sucesso!");
            dispose();
            new IngredienteTelaLista();
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
            criarIngrediente();
			setVisible(false);
			
        }

    }

    private class BotaoEditar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
			editarObjetoIngrediente();
			setVisible(false);
            new IngredienteTelaLista();
        }
    }

    private void editarObjetoIngrediente() {
        try {
            int id = Integer.parseInt(tfId.getText());
            String nome = tfNome.getText();
            LocalDate dataCompra = LocalDate.parse(tfDataCompra.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String lote = tfLote.getText();
            String kg_l = tfKg_l.getText();
            LocalDate fabricacao = LocalDate.parse(tfFabricacao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate validade = LocalDate.parse(tfValidade.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String fornecedor = tfFornecedor.getText();
            BigDecimal valorTotal = new BigDecimal(tfValorTotal.getText());
            String notaFiscal = tfNotaFiscal.getText();

            Ingrediente ingrediente = new Ingrediente();
            ingrediente.setId(id);
            ingrediente.setNome(nome);
            ingrediente.setDataCompra(dataCompra);
            ingrediente.setLote(lote);
            ingrediente.setKg_l(kg_l);
            ingrediente.setFabricacao(fabricacao);
            ingrediente.setValidade(validade);
            ingrediente.setFornecedor(fornecedor);
            ingrediente.setValorTotal(valorTotal);
            ingrediente.setNotaFiscal(notaFiscal);

            ingredienteController.atualizar(ingrediente);

            JOptionPane.showMessageDialog(null, "Ingrediente atualizado com sucesso!");

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
            new IngredienteTelaLista();
            setVisible(false);
        }
    }
}

