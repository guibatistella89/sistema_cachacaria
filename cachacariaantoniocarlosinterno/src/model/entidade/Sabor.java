package model.entidade;

public class Sabor {
	private int id;
	private String tipoSabor;
	private boolean validade;
	private String codigoBarra;
	
	public Sabor () {};

	public Sabor(int id, String tipoSabor, boolean validade, String codigoBarra) {
		this.id = id;
		this.tipoSabor = tipoSabor;
		this.validade = validade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipoSabor() {
		return tipoSabor;
	}

	public void setTipoSabor(String tipoSabor) {
		this.tipoSabor = tipoSabor;
	}

	public boolean isValidade() {
		return validade;
	}

	public void setValidade(boolean validade) {
		this.validade = validade;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	
	
		
}
