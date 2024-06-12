package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
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

import controller.api.ProdutoController;
import controller.api.VendaController;
import controller.impl.ProdutoControllerImpl;
import controller.impl.VendaControllerImpl;
import model.entidade.Produto;
import model.entidade.Venda;

@SuppressWarnings("serial")
public class VendaTelaCriarEditar extends JFrame {

    private JPanel panel;
    private JTextField tfId;
    private JFormattedTextField tfDataVenda;
    private JTextField tfCliente;
    private JComboBox<String> cbProduto;
    private JTextField tfQuantidade;
    private JTextField tfValorTotal;
    private JTextField tfNotaFiscal;
    private VendaController vendaController;
    private ProdutoController produtoController;

    public VendaTelaCriarEditar() {
        this.vendaController = new VendaControllerImpl();
        this.produtoController = new ProdutoControllerImpl();
        setTitle("Cadastro de Vendas");

        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(800, 800));
        add(panel);

        this.tfDataVenda = criarFormattedTextField("DATA DA VENDA", "##/##/####");
        this.panel.add(this.tfDataVenda);
        this.tfCliente = criarTextField("CLIENTE");
        this.panel.add(this.tfCliente);
        this.criarComboBoxProduto();
        this.tfQuantidade = criarTextField("QUANTIDADE");
        this.panel.add(this.tfQuantidade);
        this.tfValorTotal = criarTextField("VALOR TOTAL");
        this.panel.add(this.tfValorTotal);
        this.tfNotaFiscal = criarTextField("NOTA FISCAL");
        this.panel.add(this.tfNotaFiscal);

        criarBotao("Salvar", new BotaoSalvar());
        criarBotao("Voltar", new BotaoVoltar());
        criarBotao("Menu Principal", new BotaoMenuPrincipal());

        setSize(new Dimension(900, 700));
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(900, 600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public VendaTelaCriarEditar(Venda venda) {
        this.vendaController = new VendaControllerImpl();
        this.produtoController = new ProdutoControllerImpl();
        setTitle("Edição de Vendas");

        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(800, 800));
        add(panel);
        
        this.tfId = criarTextField("CÓDIGO");
        this.tfId.setEditable(false);
        this.panel.add(this.tfId);
        this.tfDataVenda = criarFormattedTextField("DATA DA VENDA", "##/##/####");
        this.panel.add(this.tfDataVenda);
        this.tfCliente = criarTextField("CLIENTE");
        this.panel.add(this.tfCliente);
        this.criarComboBoxProduto();
        this.tfQuantidade = criarTextField("QUANTIDADE");
        this.panel.add(this.tfQuantidade);
        this.tfValorTotal = criarTextField("VALOR TOTAL");
        this.panel.add(this.tfValorTotal);
        this.tfNotaFiscal = criarTextField("NOTA FISCAL");
        this.panel.add(this.tfNotaFiscal);

        criarBotao("Salvar", new BotaoEditar());
        criarBotao("Voltar", new BotaoVoltar());
        criarBotao("Menu Principal", new BotaoMenuPrincipal());

        setSize(new Dimension(900, 700));
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(900, 600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        
        tfId.setText(String.valueOf(venda.getId()));
        tfDataVenda.setText(venda.getDataVenda().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        tfCliente.setText(String.valueOf(venda.getCliente()));
        cbProduto.setSelectedItem(venda.getProduto().getLote());
        tfQuantidade.setText(String.valueOf(venda.getQuantidade()));
        tfValorTotal.setText(String.valueOf(venda.getValorTotal()));
        tfNotaFiscal.setText(String.valueOf(venda.getNotaFiscal()));
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
    
    private void criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setPreferredSize(new Dimension(700, 25));
        this.panel.add(label);
    }
    
    private void criarComboBoxProduto() {
        criarLabel("LOTE");
        List<Produto> produtos = this.produtoController.listarTodos();
        cbProduto = new JComboBox<String>();
        for (Produto produto : produtos) {
            cbProduto.addItem(produto.getLote());
        }
        cbProduto.setPreferredSize(new Dimension(700, 25));
        this.panel.add(cbProduto);
    }

    private void criarVenda() {
        try {
            Venda venda = new Venda();
            Produto produto = new Produto();
            
            
            // Converter strings para LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataVenda = LocalDate.parse(tfDataVenda.getText(), formatter);

            // Definir as datas no produto
            venda.setDataVenda(dataVenda);
            venda.setCliente(tfCliente.getText());
           
            
            String lote = (String) cbProduto.getSelectedItem();
            for (Produto item : this.produtoController.listarTodos()) {
                if (item.getLote().equals(lote)) {
                    produto = item;
                    break;
                }
            }
            if (produto != null) {
                venda.setProduto(produto);
            }
            
            
            int quantidade = Integer.parseInt(tfQuantidade.getText());
            venda.setQuantidade(quantidade);
            BigDecimal valorTotal = new BigDecimal(tfValorTotal.getText());
            venda.setValorTotal(valorTotal); 
            venda.setNotaFiscal(tfNotaFiscal.getText());
            

            
            this.vendaController.salvar(venda);

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
            criarVenda();
			JOptionPane.showMessageDialog(null, "Venda registrada com sucesso!");
			setVisible(false);
			new VendaTelaLista();	
        }

    }

    private class BotaoEditar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	editarObjetoVenda();
            setVisible(false);
            new VendaTelaLista();
        }
    }
    
    private void editarObjetoVenda() {
        try {
            
            int id = Integer.parseInt(tfId.getText());
            LocalDate dataVenda = LocalDate.parse(tfDataVenda.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String cliente = tfCliente.getText();
            String produtoSelecionado = (String) cbProduto.getSelectedItem();
            int quantidade = Integer.parseInt(tfQuantidade.getText());
            BigDecimal valorTotal = new BigDecimal(tfValorTotal.getText());
            String notaFiscal = tfNotaFiscal.getText();
            
            Produto produto = null;
            List<Produto> produtos = produtoController.listarTodos();
            for (Produto p : produtos) {
                if (p.getLote().equals(produtoSelecionado)) {
                    produto = p;
                    break;
                }
            }

            Venda venda = new Venda();
            venda.setId(id);
            venda.setDataVenda(dataVenda);
            venda.setCliente(cliente);
            venda.setProduto(produto);
            venda.setQuantidade(quantidade);
            venda.setValorTotal(valorTotal);
            venda.setNotaFiscal(notaFiscal);
            

            vendaController.atualizar(venda);

            JOptionPane.showMessageDialog(null, "Venda atualizada com sucesso!");

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
            new VendaTelaLista();
            setVisible(false);
        }
    }
    
    private class BotaoMenuPrincipal implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new TelaInicial();
            setVisible(false);
        }
    }
}
