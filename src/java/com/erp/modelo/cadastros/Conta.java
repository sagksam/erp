/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.cadastros;

import com.erp.modelo.classes.comuns.EntidadeBase;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author PauloHenrique
 */
@Entity
@Table(name="conta")
@NamedQueries({
    @NamedQuery(name="contasReceber", query="SELECT c FROM Conta c WHERE c.tipo = :tipo AND c.ativo = :ativo"), 
    @NamedQuery(name="contasPagar", query="SELECT c FROM Conta c WHERE c.tipo = :tipo AND c.ativo = :ativo"),
})
public class Conta extends EntidadeBase{
    
    @OneToOne
    @JoinColumn(name="filialId")
    private Filial filial;
    private String tipo;
    @OneToOne
    @JoinColumn(name="empresaId")
    private Empresa empresa;
    @OneToOne
    @JoinColumn(name="pessoaId")
    private Pessoa pessoa;
    @Column(length=250)
    private String descricao;
    @OneToOne
    @JoinColumn(name="contaBancariaId")
    private ContaBancaria contaBancaria;
    private String parcela;
    private int pedido;
    private int notaProduto;
    private int notaServico;
    private int notaFiscal;
    @Column(length=30)
    private String formaPagamento;
    @Column(length=30)
    private String status;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataEmissao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataVencimento;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataLimite;
    private double valorPrevisto;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataPagamento;
    private double valorPago;
    @Column(columnDefinition="TEXT")
    private String detalhes;
    @Column(length=30)
    private String banco;
    
    public Conta(){
    
        this.parcela = "1 de 1";
    }

    public Conta(Filial filial, String tipo, Empresa empresa, Pessoa pessoa, String descricao, ContaBancaria contaBancaria, String parcela, int pedido, int notaProduto, int notaServico, int notaFiscal, String formaPagamento, String status, Date dataEmissao, Date dataVencimento, Date dataLimite, double valorPrevisto, Date dataPagamento, double valorPago, String detalhes, String banco) {
        this.filial = filial;
        this.tipo = tipo;
        this.empresa = empresa;
        this.pessoa = pessoa;
        this.descricao = descricao;
        this.contaBancaria = contaBancaria;
        this.parcela = parcela;
        this.pedido = pedido;
        this.notaProduto = notaProduto;
        this.notaServico = notaServico;
        this.notaFiscal = notaFiscal;
        this.formaPagamento = formaPagamento;
        this.status = status;
        this.dataEmissao = dataEmissao;
        this.dataVencimento = dataVencimento;
        this.dataLimite = dataLimite;
        this.valorPrevisto = valorPrevisto;
        this.dataPagamento = dataPagamento;
        this.valorPago = valorPago;
        this.detalhes = detalhes;
        this.banco = banco;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public int getPedido() {
        return pedido;
    }

    public void setPedido(int pedido) {
        this.pedido = pedido;
    }

    public int getNotaProduto() {
        return notaProduto;
    }

    public void setNotaProduto(int notaProduto) {
        this.notaProduto = notaProduto;
    }

    public int getNotaServico() {
        return notaServico;
    }

    public void setNotaServico(int notaServico) {
        this.notaServico = notaServico;
    }

    public int getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(int notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }

    public double getValorPrevisto() {
        return valorPrevisto;
    }

    public void setValorPrevisto(double valorPrevisto) {
        this.valorPrevisto = valorPrevisto;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }
    
 
}
