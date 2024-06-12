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
import controller.impl.SaborControllerImpl;
import model.entidade.Sabor;
import model.service.api.ExclusaoSaborCallback;


@SuppressWarnings("serial")
public class SaborTelaLista extends JFrame {
	
	private JTable table;
	private DefaultTableModel defaultTableModel;
	private SaborController saborController;  
	
	public SaborTelaLista(){
		this.saborController = new SaborControllerImpl();
		setTitle("Sabores Cadastrados");
		setSize(new Dimension(800, 600));
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		
		defaultTableModel = new DefaultTableModel();
		table = new JTable(defaultTableModel);
		table.setPreferredSize(new Dimension(700, 400));
		table.setSize(new Dimension(700,400));
		
		defaultTableModel.addColumn("CÓDIGO");
		defaultTableModel.addColumn("SABOR ");
		defaultTableModel.addColumn("VALIDADE ");
		defaultTableModel.addColumn("CÓDIGO DE BARRAS");
		
		
		List<Sabor> sabores = this.saborController.listarTodos();
		for (Sabor sabor : sabores) {
			Object[] linha = new Object[] {
					sabor.getId(),
					sabor.getTipoSabor(),
					sabor.isValidade() ? "1 ano" : "Indeterminada",
					sabor.getCodigoBarra(),
			};
			defaultTableModel.addRow(linha);
		}
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(700, 400));
		add(scrollPane);
	
	criarBotao("Cadastrar Novo Sabor", new BotaoCadastrarSabor());
	criarBotao("Editar Sabor", new BotaoEditarSabor(saborController));
	criarBotao("Excluir Sabor", new BotaoExcluirSabor(saborController, defaultTableModel, table));
	criarBotao("Voltar", new BotaoVoltar());
	
	
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(true);
	
}

	private void criarBotao(String label, ActionListener listener) {
		JButton botao = new JButton(label);
		botao.setPreferredSize(new Dimension(170, 50));
		botao.addActionListener(listener);
		add(botao);
	}
	
	private class BotaoCadastrarSabor implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new SaborTelaCriarEditar();
			setVisible(false);
		}
	}
	
	private class BotaoEditarSabor implements ActionListener {

		private SaborController saborController;

		public BotaoEditarSabor(SaborController saborController) {
			this.saborController = saborController;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			int linhaSelecionada = table.getSelectedRow();
			
			if (linhaSelecionada > -1) {
				int id = Integer.parseInt(table.getModel().getValueAt(linhaSelecionada, 0).toString());
				Sabor sabor = this.saborController.buscarPorId(id);
				new SaborTelaCriarEditar(sabor);
				setVisible(false);
				
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um sabor para edição!");
			}
		}
		
	}

	
	public class BotaoExcluirSabor implements ActionListener, ExclusaoSaborCallback {
	    private SaborController saborController;
	    private DefaultTableModel defaultTableModel;
	    private JTable table;

	    public BotaoExcluirSabor(SaborController saborController, DefaultTableModel defaultTableModel, JTable table) {
	        this.saborController = saborController;
	        this.defaultTableModel = defaultTableModel;
	        this.table = table;
	    }

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        int linhaSelecionada = table.getSelectedRow();
	        if (linhaSelecionada > -1) {
	            int resposta = JOptionPane.showConfirmDialog(null, "O sabor só poderá ser excluído se o "
	                    + "estoque dele estiver zerado.\n"
	                    + "Deseja continuar?", "Aviso", JOptionPane.YES_NO_OPTION);
	            if (resposta == JOptionPane.YES_OPTION) {
	                int id = Integer.parseInt(table.getModel().getValueAt(linhaSelecionada, 0).toString());
	                saborController.excluir(id, this); // Passando esta classe como callback
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Selecione um sabor para exclusão!");
	        }
	    }

	    @Override
	    public void onSaborExcluidoComSucesso() {
	        JOptionPane.showMessageDialog(null, "Sabor excluído com sucesso!");
	        int linhaSelecionada = table.getSelectedRow();
	        defaultTableModel.removeRow(linhaSelecionada);

	    }

	    @Override
	    public void onFalhaAoExcluirSabor() {
	        JOptionPane.showMessageDialog(null, "Falha ao excluir o sabor!");
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


