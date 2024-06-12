package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import controller.api.SaborController;
import controller.api.ProdutoController;
import controller.impl.SaborControllerImpl;
import controller.impl.ProdutoControllerImpl;
import model.entidade.Produto;
import model.entidade.Sabor;

@SuppressWarnings("serial")
public class ProdutoTelaCriarEditar extends JFrame {

    private JPanel panel;
    private JTextField tfId;
    private JComboBox<String> cbSabor;
    private JTextField tfLote;
    private JFormattedTextField tfFabricacao;
    private JTextField tfQuantidade;
    private JTextField tfRestante;
    private ProdutoController produtoController;
    private SaborController saborController;

    public ProdutoTelaCriarEditar() {
        this.produtoController = new ProdutoControllerImpl();
        this.saborController = new SaborControllerImpl();
        setTitle("Cadastro de Bebidas");

        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(800, 800));
        add(panel);
        this.criarComboBoxSabor();
        this.tfLote = criarTextField("LOTE");
        this.panel.add(this.tfLote);
        this.tfFabricacao = criarFormattedTextField("DATA DE FABRICAÇÃO", "##/##/####");
        this.panel.add(this.tfFabricacao);
        this.tfQuantidade = criarTextField("QUANTIDADE");
        this.panel.add(this.tfQuantidade);


        criarBotao("Salvar", new BotaoSalvar());
        criarBotao("Voltar", new BotaoListar());
        criarBotao("Menu Principal", new BotaoVoltar());

        setSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(900, 600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public ProdutoTelaCriarEditar(Produto produto) {
        this.produtoController = new ProdutoControllerImpl();
        this.saborController = new SaborControllerImpl();
        setTitle("Edição de Bebidas");

        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(800, 800));
        add(panel);
        
        this.tfId = criarTextField("CÓDIGO");
        this.tfId.setEditable(false);
        this.panel.add(this.tfId);
        this.criarComboBoxSabor();
        this.tfLote = criarTextField("LOTE");
        this.panel.add(this.tfLote);
        this.tfFabricacao = criarFormattedTextField("DATA DE FABRICAÇÃO", "##/##/####");
        this.panel.add(this.tfFabricacao);
        this.tfQuantidade = criarTextField("QUANTIDADE");
        this.panel.add(this.tfQuantidade);
        this.tfRestante = criarTextField("RESTANTES");
        this.panel.add(this.tfRestante);

        criarBotao("Salvar", new BotaoEditar());
        criarBotao("Voltar", new BotaoListar());
        criarBotao("Menu Principal", new BotaoVoltar());

        setSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(900, 600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

   
        tfId.setText(String.valueOf(produto.getId()));
        cbSabor.setSelectedItem(produto.getSabor().getTipoSabor());
        tfLote.setText(produto.getLote());
        tfFabricacao.setText(produto.getFabricacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        tfQuantidade.setText(String.valueOf(produto.getQuantidade()));
        tfRestante.setText(String.valueOf(produto.getRestante()));
    }

    private void criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setPreferredSize(new Dimension(700, 25));
        this.panel.add(label);
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

    private void criarComboBoxSabor() {
        criarLabel("SABOR");
        List<Sabor> sabores = this.saborController.listarTodos();
        cbSabor = new JComboBox<String>();
        for (Sabor sabor : sabores) {
            cbSabor.addItem(sabor.getTipoSabor());
        }
        cbSabor.setPreferredSize(new Dimension(700, 25));
        this.panel.add(cbSabor);
    }

    private void criarProduto() {
        try {
            Produto produto = new Produto();
            Sabor sabor = new Sabor();
            String saborTipo = (String) cbSabor.getSelectedItem();
            for (Sabor item : this.saborController.listarTodos()) {
                if (item.getTipoSabor().equals(saborTipo)) {
                    sabor = item;
                    break;
                }
            }
            if (sabor != null) {
                produto.setSabor(sabor);
            }
            produto.setLote(tfLote.getText());
            int quantidade = Integer.parseInt(tfQuantidade.getText());
            produto.setQuantidade(quantidade);
         // Definir o valor da quantidade para o campo "restante"
            produto.setRestante(quantidade);

            // Converter strings para LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fabricacao = LocalDate.parse(tfFabricacao.getText(), formatter);

            // Definir as datas no produto
            produto.setFabricacao(fabricacao);

            // Salvar o produto
            this.produtoController.salvar(produto);

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
            criarProduto();
			JOptionPane.showMessageDialog(null, "Produto registrado com sucesso!");
			setVisible(false);
			new ProdutoTelaLista();	
        }

    }

    private class BotaoEditar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
			editarObjetoProduto();
			setVisible(false);
            new ProdutoTelaLista();
        }
    }

    private void editarObjetoProduto() {
        try {
            
            int id = Integer.parseInt(tfId.getText());
            String lote = tfLote.getText();
            String saborSelecionado = (String) cbSabor.getSelectedItem();
            int quantidade = Integer.parseInt(tfQuantidade.getText());
            int restante = Integer.parseInt(tfRestante.getText());
            LocalDate fabricacao = LocalDate.parse(tfFabricacao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            Sabor sabor = null;
            List<Sabor> sabores = saborController.listarTodos();
            for (Sabor s : sabores) {
                if (s.getTipoSabor().equals(saborSelecionado)) {
                    sabor = s;
                    break;
                }
            }

            Produto produto = new Produto();
            produto.setId(id);
            produto.setSabor(sabor);
            produto.setLote(lote);
            produto.setQuantidade(quantidade);
            produto.setRestante(restante);
            produto.setFabricacao(fabricacao);

            produtoController.atualizar(produto);

            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");

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
            new ProdutoTelaLista();
            setVisible(false);
        }
    }
}
