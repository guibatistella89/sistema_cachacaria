package model.entidade;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Ingrediente {

    private int id;
    private String nome;
    private LocalDate dataCompra;
    private String lote;
    private String kg_l;
    private LocalDate fabricacao;
    private LocalDate validade;
    private String fornecedor;
    private BigDecimal valorTotal;
    private String notaFiscal;

    public Ingrediente() {
    };

    public Ingrediente(int id, String nome, LocalDate dataCompra, String lote, String kg_l, LocalDate fabricacao, 
    		LocalDate validade, String fornecedor, BigDecimal valorTotal, String notaFiscal) {
        this.id = id;
        this.nome = nome;
        this.dataCompra = dataCompra;
        this.lote = lote;
        this.kg_l = kg_l;
        this.fabricacao = fabricacao;
        this.validade = validade;
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

	public String getKg_l() {
		return kg_l;
	}

	public void setKg_l(String kg_l) {
		this.kg_l = kg_l;
	}

	public LocalDate getFabricacao() {
		return fabricacao;
	}

	public void setFabricacao(LocalDate fabricacao) {
		this.fabricacao = fabricacao;
	}

	public LocalDate getValidade() {
		return validade;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
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

