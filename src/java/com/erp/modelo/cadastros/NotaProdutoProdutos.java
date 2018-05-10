/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.cadastros;

import com.erp.modelo.classes.comuns.VendaProdutosBase;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author PauloHenrique
 */
@Entity
@Table(name="notaprodutoprodutos")
public class NotaProdutoProdutos extends VendaProdutosBase implements Serializable{
    
    @OneToOne
    @JoinColumn(name="pedidoId")
    private Pedido pedido;
    private String classFiscal;
    private int CST;
    private String CFOP;
    private String descricaoCFOP;
    @ManyToOne
    @JoinColumn(name="notaProdutoId")
    private NotaProduto notaProduto;
    
    public NotaProdutoProdutos() {    
        super();
    }

    public NotaProdutoProdutos(Pedido pedido, String classFiscal, int CST, String CFOP, String descricaoCFOP, NotaProduto notaProduto, Produto produto, String referencia, String descricaoProduto, String unidade, double estoqueAtual, int quantidade, double valorUnitario, double valorProduto, double desconto, double valorDesconto, double margemIPI, double baseIPI, double aliquotaIPI, double valorIPI, double margemICMS, double baseICMS, double aliquotaICMS, double valorICMS, double margemICMSST, double baseICMSST, double aliquotaICMSST, double valorICMSST, double valorFrete, double valorSeguro, double outrasDespesas, double valorTotal) {
        super(produto, referencia, descricaoProduto, unidade, estoqueAtual, quantidade, valorUnitario, valorProduto, desconto, valorDesconto, margemIPI, baseIPI, aliquotaIPI, valorIPI, margemICMS, baseICMS, aliquotaICMS, valorICMS, margemICMSST, baseICMSST, aliquotaICMSST, valorICMSST, valorFrete, valorSeguro, outrasDespesas, valorTotal);
        this.pedido = pedido;
        this.classFiscal = classFiscal;
        this.CST = CST;
        this.CFOP = CFOP;
        this.descricaoCFOP = descricaoCFOP;
        this.notaProduto = notaProduto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getClassFiscal() {
        return classFiscal;
    }

    public void setClassFiscal(String classFiscal) {
        this.classFiscal = classFiscal;
    }

    public int getCST() {
        return CST;
    }

    public void setCST(int CST) {
        this.CST = CST;
    }

    public String getCFOP() {
        return CFOP;
    }

    public void setCFOP(String CFOP) {
        this.CFOP = CFOP;
    }

    public String getDescricaoCFOP() {
        return descricaoCFOP;
    }

    public void setDescricaoCFOP(String descricaoCFOP) {
        this.descricaoCFOP = descricaoCFOP;
    }

    public NotaProduto getNotaProduto() {
        return notaProduto;
    }

    public void setNotaProduto(NotaProduto notaProduto) {
        this.notaProduto = notaProduto;
    }
    
    
}
