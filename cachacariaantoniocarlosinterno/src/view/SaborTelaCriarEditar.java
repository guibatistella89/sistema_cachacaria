
package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.api.SaborController;
import controller.impl.SaborControllerImpl;
import model.entidade.Sabor;
 


@SuppressWarnings("serial")
public class SaborTelaCriarEditar extends JFrame {

	private JPanel panel;
	private JTextField tfId;
	private JTextField textFieldAdicionarSabor;
	private JCheckBox JCValidade;
	private JTextField tfCodigoBarra;
	private SaborController saborController;	
	

	public SaborTelaCriarEditar() {
		this.saborController = new SaborControllerImpl();
		setTitle("Cadastro de Sabores");
		
		this.panel = new JPanel();
		this.panel.setPreferredSize(new Dimension(500, 500));
		add(panel);
		

		this.textFieldAdicionarSabor = criarTextField("NOME DO SABOR:");
		this.panel.add(this.textFieldAdicionarSabor);
		this.tfCodigoBarra = criarTextField("CÓDIGO DE BARRAS:");
		this.panel.add(this.tfCodigoBarra);
		this.JCValidade = new JCheckBox("Possui validade de 1 ano?");
		this.panel.add(this.JCValidade);

		
		criarBotao("Salvar", new BotaoSalvar());
		criarBotao("Voltar", new BotaoListar());
		criarBotao("Menu Principal", new BotaoVoltar());
		
		setPreferredSize(new Dimension(500, 500));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(500,500);
		setLocationRelativeTo(null);
			
	}

	
	public SaborTelaCriarEditar(Sabor sabor) {
		this.saborController = new SaborControllerImpl();
		setTitle("Edição dos Sabores");
		
		this.panel = new JPanel();
		this.panel.setPreferredSize(new Dimension(500, 500));
		add(panel);
		
		this.tfId = criarTextField("CÓDIGO");
		this.tfId.setEditable(false);
		this.panel.add(this.tfId);
		this.textFieldAdicionarSabor = criarTextField("SABOR");
		this.panel.add(this.textFieldAdicionarSabor);
		this.tfCodigoBarra = criarTextField("CÓDIGO DE BARRAS:");
		this.panel.add(this.tfCodigoBarra);
		this.JCValidade = new JCheckBox("Possui validade de 1 ano?");
		this.panel.add(this.JCValidade);
		
		criarBotao("Salvar", new BotaoEditar());
		criarBotao("Voltar", new BotaoListar());
		criarBotao("Menu Principal", new BotaoVoltar());
		
		setSize(500,500);
		setPreferredSize(new Dimension(500, 500));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		this.saborController = new SaborControllerImpl();
		this.tfId.setText(String.valueOf(sabor.getId()));
		this.textFieldAdicionarSabor.setText(sabor.getTipoSabor());
		this.JCValidade.setSelected(sabor.isValidade());
		this.tfCodigoBarra.setText(sabor.getCodigoBarra());
		

    }
		
		
	private JTextField criarTextField(String textoLabel) {
		JLabel label = new JLabel(textoLabel);
		label.setPreferredSize(new Dimension(450,20));
		this.panel.add(label);
		
		JTextField textField = new JTextField();
		textField.setPreferredSize(new Dimension(450,40));
		return textField;
	}
	
	private void criarBotao(String textoLabel, ActionListener listener) {
		JButton botao = new JButton(textoLabel);
		botao.setPreferredSize(new Dimension(450, 40));
		botao.addActionListener(listener);
		this.panel.add(botao);
	}
	
	private void criarObjetoSabor() {
		Sabor sabor = new Sabor();
		sabor.setTipoSabor(textFieldAdicionarSabor.getText());
		sabor.setValidade(JCValidade.isSelected());
		sabor.setCodigoBarra(tfCodigoBarra.getText());
		
		this.saborController.salvar(sabor);				
	}
			

	private class BotaoSalvar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			criarObjetoSabor();
			JOptionPane.showMessageDialog(null, "Sabor registrado com sucesso!");
			setVisible(false);
			new SaborTelaLista();	
		}
		
	}
	
	private class BotaoEditar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			editarObjetoSabor();
			JOptionPane.showMessageDialog(null, "Sabor editado com sucesso!");
			setVisible(false);
			new SaborTelaLista();	
		}
		
	}
	
	private void editarObjetoSabor() {
		Sabor sabor = new Sabor(); 
		sabor.setId(Integer.parseInt(tfId.getText()));
		sabor.setTipoSabor(textFieldAdicionarSabor.getText());
		sabor.setValidade(JCValidade.isSelected());
		sabor.setCodigoBarra(tfCodigoBarra.getText());
		this.saborController.atualizar(sabor);				
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
			new SaborTelaLista();
			setVisible(false);
		}
		
	}

}