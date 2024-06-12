package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TelaInicial extends JFrame{

	private JPanel panel;
	
	public TelaInicial() {
		setTitle("Tela Inicial");
		
		this.panel = new JPanel();
		this.panel.setPreferredSize(new Dimension(700, 600));
		add(panel);

		criarBotao("Registro de Vendas", "icones/venda.png", new BotaoVendas());
		criarBotao("Estoque de Bebidas", "icones/estoque.png", new BotaoBebidas());
		criarBotao("Sabores", "icones/sabor.png", new BotaoSabores());
		criarBotao("Estoque de Ingredientes", "icones/ingredientes.png", new BotaoIngredientes());
		criarBotao("Estoque de Utensílios", "icones/utensilio.png", new BotaoUtensilios());
		criarBotao("Sair do Programa", "icones/exit.png", new BotaoSair());
		
		setPreferredSize(new Dimension(700, 500));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(500,400);
		setLocationRelativeTo(null);
		
	}
	
	   private void criarBotao(String textoLabel, String iconePath, ActionListener listener) {
	        JButton botao = new JButton();
	        botao.setLayout(new BorderLayout());
	        botao.setPreferredSize(new Dimension(450, 40));

	        ImageIcon icon = new ImageIcon(iconePath);
	        Image img = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
	        icon = new ImageIcon(img);

	        JLabel iconLabel = new JLabel(icon);
	        iconLabel.setHorizontalAlignment(JLabel.LEFT);
	        botao.add(iconLabel, BorderLayout.WEST);

	        JLabel textLabel = new JLabel(textoLabel);
	        textLabel.setHorizontalAlignment(JLabel.CENTER);
	        botao.add(textLabel, BorderLayout.CENTER);

	        botao.addActionListener(listener);
	        this.panel.add(botao);
	    }

	
	
	private class BotaoVendas implements ActionListener {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				new VendaTelaLista();
				setVisible(false);
			}
		}
	
		private class BotaoBebidas implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ProdutoTelaLista();
				setVisible(false);
			}
		}
		
		
		private class BotaoSabores implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SaborTelaLista();
				setVisible(false);
			}
		}
		
		private class BotaoIngredientes implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				new IngredienteTelaLista();
				setVisible(false);
			}
		}
		
		private class BotaoUtensilios implements ActionListener {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UtensilioTelaLista();
				setVisible(false);
			}
		}
			
		private class BotaoSair implements ActionListener {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		}
}

	
	