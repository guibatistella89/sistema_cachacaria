package model.entidade;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Utensilio {

    private int id;
    private String nome;
    private LocalDate dataCompra;
    private String lote;
    private String quantidade;
    private String fornecedor;
    private BigDecimal valorTotal;
    private String notaFiscal;

    public Utensilio() {
    };

    public Utensilio(int id, String nome, LocalDate dataCompra, String lote, String quantidade, String fornecedor, 
    		BigDecimal valorTotal, String notaFiscal) {
        this.id = id;
        this.nome = nome;
        this.dataCompra = dataCompra;
        this.lote = lote;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
        this.valorTotal = valorTotal;
        this.notaFiscal = notaFiscal;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	
	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
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
