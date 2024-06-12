package model.entidade;

import java.time.LocalDate;

public class Produto {

    private int id;
    private Sabor sabor;
    private String lote;
    private LocalDate fabricacao;
    private int quantidade;
    private int restante;

    public Produto() {
    };

    public Produto(Sabor sabor, String lote, LocalDate fabricacao, int quantidade, int restante) {
        this.sabor = sabor;
        this.lote = lote;
        this.fabricacao = fabricacao;
        this.quantidade = quantidade;
        this.restante = restante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sabor getSabor() {
        return sabor;
    }

    public void setSabor(Sabor sabor) {
        this.sabor = sabor;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public LocalDate getFabricacao() {
        return fabricacao;
    }

    public void setFabricacao(LocalDate fabricacao) {
        this.fabricacao = fabricacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public int getRestante() {
		return restante;
	}

	public void setRestante(int restante) {
		this.restante = restante;
	}

    public boolean isValidade() {
        if (sabor != null) {
            return sabor.isValidade();
        }
        return false; 
    }
}
