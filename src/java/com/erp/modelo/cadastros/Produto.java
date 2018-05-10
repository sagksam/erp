/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.cadastros;

import com.erp.modelo.classes.comuns.EntidadeBase;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author PauloHenrique
 */
@Entity
@Table(name="produto")
@NamedQueries({
  @NamedQuery(name="produtos", query="SELECT p FROM Produto p WHERE p.ativo = :ativo"),
  @NamedQuery(name="produtosVenda", query="SELECT p FROM Produto p WHERE p.ativo = :ativo AND p.operacoes = :operacoes"),
  @NamedQuery(name="produtosCompra", query="SELECT p FROM Produto p WHERE p.ativo = :ativo AND p.operacoes = :operacoes"),
})
public class Produto extends EntidadeBase{
    
    @Column(length=30)
    private String categoria;
    @Column(length=30)
    private String operacoes;
    @Column(length=30)
    private String referencia;
    @Column(length=250)
    private String descricao;
    @Column(length=30)
    private String marca;
    @Column(length=30)
    private String modelo;
    @Column(length=30)
    private String status;
    @Column(columnDefinition="TEXT")
    private String detalhes;
    private String moeda;
    private double valorCusto;
    private double margemLucro;
    private double valorVenda;
    private double desconto;
    private double valorDesconto;
    private double valorFinal;
    @Column(length=30)
    private String classificacaoFiscal;
    @Column(length=10)
    private String cst;
    @Column(length=10)
    private String ibpt;
    private boolean controleEstoque;
    private double estoqueAtual;
 
    
    public Produto() {
    }

    public Produto(String categoria, String operacoes, String referencia, String descricao, String marca, String modelo, String status, String detalhes, String moeda, double valorCusto, double margemLucro, double valorVenda, double desconto, double valorDesconto, double valorFinal, String classificacaoFiscal, String cst, String ibpt, boolean controleEstoque, double estoqueAtual) {
        this.categoria = categoria;
        this.operacoes = operacoes;
        this.referencia = referencia;
        this.descricao = descricao;
        this.marca = marca;
        this.modelo = modelo;
        this.status = status;
        this.detalhes = detalhes;
        this.moeda = moeda;
        this.valorCusto = valorCusto;
        this.margemLucro = margemLucro;
        this.valorVenda = valorVenda;
        this.desconto = desconto;
        this.valorDesconto = valorDesconto;
        this.valorFinal = valorFinal;
        this.classificacaoFiscal = classificacaoFiscal;
        this.cst = cst;
        this.ibpt = ibpt;
        this.controleEstoque = controleEstoque;
        this.estoqueAtual = estoqueAtual;
    }
  

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getOperacoes() {
        return operacoes;
    }

    public void setOperacoes(String operacoes) {
        this.operacoes = operacoes;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public double getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(double valorCusto) {
        this.valorCusto = valorCusto;
    }

    public double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
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

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public String getClassificacaoFiscal() {
        return classificacaoFiscal;
    }

    public void setClassificacaoFiscal(String classificacaoFiscal) {
        this.classificacaoFiscal = classificacaoFiscal;
    }

    public String getCst() {
        return cst;
    }

    public void setCst(String cst) {
        this.cst = cst;
    }

    public String getIbpt() {
        return ibpt;
    }

    public void setIbpt(String ibpt) {
        this.ibpt = ibpt;
    }

    public boolean isControleEstoque() {
        return controleEstoque;
    }

    public void setControleEstoque(boolean controleEstoque) {
        this.controleEstoque = controleEstoque;
    }

    public double getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(double estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }   
    
    @Override
    public String toString() {
        return "--- Produto[ id=" + super.getId() + " ] --- ";
    }
    
    
    
}
