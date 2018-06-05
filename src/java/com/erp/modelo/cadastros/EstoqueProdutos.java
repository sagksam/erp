/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.cadastros;

import com.erp.modelo.classes.comuns.EntidadeBase;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Paulo
 */
@Entity
@Table(name="estoqueProdutos")
public class EstoqueProdutos extends EntidadeBase implements Serializable{
    
    @ManyToOne
    @JoinColumn(name="produtoId")
    private Produto produto;
    private String referencia;
    private String descricao;
    private String unidade;
    private double estoqueAtual;
    private int quantidade;
    private double saldo;
    private double valorUnitario;
    private double valorTotal;
    @ManyToOne
    @JoinColumn(name="estoqueId")
    private Estoque estoque;
    
    public EstoqueProdutos(){}

    public EstoqueProdutos(Produto produto, String referencia, String descricao, String unidade, double estoqueAtual, int quantidade, double saldo, double valorUnitario, double valorTotal, Estoque estoque) {
        this.produto = produto;
        this.referencia = referencia;
        this.descricao = descricao;
        this.unidade = unidade;
        this.estoqueAtual = estoqueAtual;
        this.quantidade = quantidade;
        this.saldo = saldo;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.estoque = estoque;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public double getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(double estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
    
    
}
