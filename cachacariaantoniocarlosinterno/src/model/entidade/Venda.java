package model.entidade;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Venda {
	private int id;
    private LocalDate dataVenda;
    private String cliente;
    private Produto produto;
    private int quantidade;
    private BigDecimal valorTotal;
    private String notaFiscal;

    public Venda() {
    	
    }
    
    public Venda(int id, LocalDate dataVenda, String cliente, Produto produto, int quantidade, BigDecimal valorTotal, String notaFiscal) {
        this.id = id;
        this.dataVenda = dataVenda;
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.notaFiscal = notaFiscal;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	
}