/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.classes.comuns;

import com.erp.modelo.cadastros.Produto;
import java.io.Serializable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author PauloHenrique
 */
@MappedSuperclass
public class VendaProdutosBase extends EntidadeBase implements Serializable{
    
    @ManyToOne
    @JoinColumn(name="produtoId")
    private Produto produto;
    private String referencia;
    private String descricaoProduto;
    private String unidade;
    private double estoqueAtual;
    private int quantidade;
    private double valorUnitario;
    private double valorProduto;
    private double desconto;
    private double valorDesconto;
    private double margemIPI;
    private double baseIPI;
    private double aliquotaIPI;
    private double valorIPI;
    private double margemICMS;
    private double baseICMS;
    private double aliquotaICMS;
    private double valorICMS;
    private double margemICMSST;
    private double baseICMSST;
    private double aliquotaICMSST;
    private double valorICMSST;
    private double valorFrete;
    private double valorSeguro;
    private double outrasDespesas;
    private double valorTotal;
    
    public VendaProdutosBase(){
    
        this.baseIPI = 100;
        this.baseICMS = 100;
        this.baseICMSST = 100;
    }

    public VendaProdutosBase(Produto produto, String referencia, String descricaoProduto, String unidade, double estoqueAtual, int quantidade, double valorUnitario, double valorProduto, double desconto, double valorDesconto, double margemIPI, double baseIPI, double aliquotaIPI, double valorIPI, double margemICMS, double baseICMS, double aliquotaICMS, double valorICMS, double margemICMSST, double baseICMSST, double aliquotaICMSST, double valorICMSST, double valorFrete, double valorSeguro, double outrasDespesas, double valorTotal) {
        this.produto = produto;
        this.referencia = referencia;
        this.descricaoProduto = descricaoProduto;
        this.unidade = unidade;
        this.estoqueAtual = estoqueAtual;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorProduto = valorProduto;
        this.desconto = desconto;
        this.valorDesconto = valorDesconto;
        this.margemIPI = margemIPI;
        this.baseIPI = baseIPI;
        this.aliquotaIPI = aliquotaIPI;
        this.valorIPI = valorIPI;
        this.margemICMS = margemICMS;
        this.baseICMS = baseICMS;
        this.aliquotaICMS = aliquotaICMS;
        this.valorICMS = valorICMS;
        this.margemICMSST = margemICMSST;
        this.baseICMSST = baseICMSST;
        this.aliquotaICMSST = aliquotaICMSST;
        this.valorICMSST = valorICMSST;
        this.valorFrete = valorFrete;
        this.valorSeguro = valorSeguro;
        this.outrasDespesas = outrasDespesas;
        this.valorTotal = valorTotal;
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

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
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

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public double getMargemIPI() {
        return margemIPI;
    }

    public void setMargemIPI(double margemIPI) {
        this.margemIPI = margemIPI;
    }

    public double getBaseIPI() {
        return baseIPI;
    }

    public void setBaseIPI(double baseIPI) {
        this.baseIPI = baseIPI;
    }

    public double getAliquotaIPI() {
        return aliquotaIPI;
    }

    public void setAliquotaIPI(double aliquotaIPI) {
        this.aliquotaIPI = aliquotaIPI;
    }

    public double getValorIPI() {
        return valorIPI;
    }

    public void setValorIPI(double valorIPI) {
        this.valorIPI = valorIPI;
    }

    public double getMargemICMS() {
        return margemICMS;
    }

    public void setMargemICMS(double margemICMS) {
        this.margemICMS = margemICMS;
    }

    public double getBaseICMS() {
        return baseICMS;
    }

    public void setBaseICMS(double baseICMS) {
        this.baseICMS = baseICMS;
    }

    public double getAliquotaICMS() {
        return aliquotaICMS;
    }

    public void setAliquotaICMS(double aliquotaICMS) {
        this.aliquotaICMS = aliquotaICMS;
    }

    public double getValorICMS() {
        return valorICMS;
    }

    public void setValorICMS(double valorICMS) {
        this.valorICMS = valorICMS;
    }

    public double getMargemICMSST() {
        return margemICMSST;
    }

    public void setMargemICMSST(double margemICMSST) {
        this.margemICMSST = margemICMSST;
    }

    public double getBaseICMSST() {
        return baseICMSST;
    }

    public void setBaseICMSST(double baseICMSST) {
        this.baseICMSST = baseICMSST;
    }

    public double getAliquotaICMSST() {
        return aliquotaICMSST;
    }

    public void setAliquotaICMSST(double aliquotaICMSST) {
        this.aliquotaICMSST = aliquotaICMSST;
    }

    public double getValorICMSST() {
        return valorICMSST;
    }

    public void setValorICMSST(double valorICMSST) {
        this.valorICMSST = valorICMSST;
    }

    public double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public double getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public double getOutrasDespesas() {
        return outrasDespesas;
    }

    public void setOutrasDespesas(double outrasDespesas) {
        this.outrasDespesas = outrasDespesas;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    
}
