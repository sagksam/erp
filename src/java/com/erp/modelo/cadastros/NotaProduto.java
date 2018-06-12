/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.cadastros;

import com.erp.modelo.classes.comuns.VendaBase;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author PauloHenrique
 */
@Entity
@Table(name="notaproduto")
@NamedQuery(name="notasProdutos", query="SELECT n FROM NotaProduto n WHERE n.ativo = :ativo")
public class NotaProduto extends VendaBase implements Serializable{
    
    private int notaFiscal;
    private String naturezaOperacao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataSaidaEntrada;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date horaEmissao;
    private String finalidade;
    private String chaveAcesso;
    private String protocoloAutorizacao;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataAutorizacao;
    private String motivoCancelamento;
    private String protocoloCancelamento;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataCancelamento;
    private String cnpj;
    private String cpf;
    private String pedido;
    private double creditoICMS;
    @OneToOne
    @JoinColumn(name="empresaTransportadoraId")
    private Empresa empresaTransportadora;
    @OneToOne
    @JoinColumn(name="pessoaTransportadoraId")
    private Pessoa pessoaTransportadora;
    private String descricaoTransportadora;
    private String freteContaTransportadora;
    private int quantidadeTransportadora;
    private String especieTransportadora;
    private double pesoBrutoTransportadora;
    private double pesoLiquidoTransportadora;
    @OneToMany(mappedBy="notaProduto", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
    private List<NotaProdutoProdutos> notaProdutoProdutos;

    public NotaProduto() {
    
        super();
        this.horaEmissao = new Date();
        this.dataSaidaEntrada = new Date();
        this.naturezaOperacao = " ";
    }
    public NotaProduto(int notaFiscal, String naturezaOperacao, Date dataSaidaEntrada, Date horaEmissao, String finalidade, String chaveAcesso, String protocoloAutorizacao, Date dataAutorizacao, String motivoCancelamento, String protocoloCancelamento, Date dataCancelamento, String cnpj, String cpf, String pedido, double creditoICMS, Empresa empresaTransportadora, Pessoa pessoaTransportadora, String descricaoTransportadora, String freteContaTransportadora, int quantidadeTransportadora, String especieTransportadora, double pesoBrutoTransportadora, double pesoLiquidoTransportadora, List<NotaProdutoProdutos> notaProdutoProdutos) {
        this.notaFiscal = notaFiscal;
        this.naturezaOperacao = naturezaOperacao;
        this.dataSaidaEntrada = dataSaidaEntrada;
        this.horaEmissao = horaEmissao;
        this.finalidade = finalidade;
        this.chaveAcesso = chaveAcesso;
        this.protocoloAutorizacao = protocoloAutorizacao;
        this.dataAutorizacao = dataAutorizacao;
        this.motivoCancelamento = motivoCancelamento;
        this.protocoloCancelamento = protocoloCancelamento;
        this.dataCancelamento = dataCancelamento;
        this.cnpj = cnpj;
        this.cpf = cpf;
        this.pedido = pedido;
        this.creditoICMS = creditoICMS;
        this.empresaTransportadora = empresaTransportadora;
        this.pessoaTransportadora = pessoaTransportadora;
        this.descricaoTransportadora = descricaoTransportadora;
        this.freteContaTransportadora = freteContaTransportadora;
        this.quantidadeTransportadora = quantidadeTransportadora;
        this.especieTransportadora = especieTransportadora;
        this.pesoBrutoTransportadora = pesoBrutoTransportadora;
        this.pesoLiquidoTransportadora = pesoLiquidoTransportadora;
        this.notaProdutoProdutos = notaProdutoProdutos;
    }

    public int getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(int notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public String getNaturezaOperacao() {
        return naturezaOperacao;
    }

    public void setNaturezaOperacao(String naturezaOperacao) {
        this.naturezaOperacao = naturezaOperacao;
    }

    public Date getDataSaidaEntrada() {
        return dataSaidaEntrada;
    }

    public void setDataSaidaEntrada(Date dataSaidaEntrada) {
        this.dataSaidaEntrada = dataSaidaEntrada;
    }

    public Date getHoraEmissao() {
        return horaEmissao;
    }

    public void setHoraEmissao(Date horaEmissao) {
        this.horaEmissao = horaEmissao;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

    public String getProtocoloAutorizacao() {
        return protocoloAutorizacao;
    }

    public void setProtocoloAutorizacao(String protocoloAutorizacao) {
        this.protocoloAutorizacao = protocoloAutorizacao;
    }

    public Date getDataAutorizacao() {
        return dataAutorizacao;
    }

    public void setDataAutorizacao(Date dataAutorizacao) {
        this.dataAutorizacao = dataAutorizacao;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    public String getProtocoloCancelamento() {
        return protocoloCancelamento;
    }

    public void setProtocoloCancelamento(String protocoloCancelamento) {
        this.protocoloCancelamento = protocoloCancelamento;
    }

    public Date getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(Date dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public double getCreditoICMS() {
        return creditoICMS;
    }

    public void setCreditoICMS(double creditoICMS) {
        this.creditoICMS = creditoICMS;
    }

    public Empresa getEmpresaTransportadora() {
        return empresaTransportadora;
    }

    public void setEmpresaTransportadora(Empresa empresaTransportadora) {
        this.empresaTransportadora = empresaTransportadora;
    }

    public Pessoa getPessoaTransportadora() {
        return pessoaTransportadora;
    }

    public void setPessoaTransportadora(Pessoa pessoaTransportadora) {
        this.pessoaTransportadora = pessoaTransportadora;
    }

    public String getDescricaoTransportadora() {
        return descricaoTransportadora;
    }

    public void setDescricaoTransportadora(String descricaoTransportadora) {
        this.descricaoTransportadora = descricaoTransportadora;
    }

    public String getFreteContaTransportadora() {
        return freteContaTransportadora;
    }

    public void setFreteContaTransportadora(String freteContaTransportadora) {
        this.freteContaTransportadora = freteContaTransportadora;
    }

    public int getQuantidadeTransportadora() {
        return quantidadeTransportadora;
    }

    public void setQuantidadeTransportadora(int quantidadeTransportadora) {
        this.quantidadeTransportadora = quantidadeTransportadora;
    }

    public String getEspecieTransportadora() {
        return especieTransportadora;
    }

    public void setEspecieTransportadora(String especieTransportadora) {
        this.especieTransportadora = especieTransportadora;
    }

    public double getPesoBrutoTransportadora() {
        return pesoBrutoTransportadora;
    }

    public void setPesoBrutoTransportadora(double pesoBrutoTransportadora) {
        this.pesoBrutoTransportadora = pesoBrutoTransportadora;
    }

    public double getPesoLiquidoTransportadora() {
        return pesoLiquidoTransportadora;
    }

    public void setPesoLiquidoTransportadora(double pesoLiquidoTransportadora) {
        this.pesoLiquidoTransportadora = pesoLiquidoTransportadora;
    }

    public List<NotaProdutoProdutos> getNotaProdutoProdutos() {
        return notaProdutoProdutos;
    }

    public void setNotaProdutoProdutos(List<NotaProdutoProdutos> notaProdutoProdutos) {
        this.notaProdutoProdutos = notaProdutoProdutos;
    }
    
    @Override
    public String toString() {
        return "--- NotaProduto[ id=" + super.getId() + " ] --- ";
    }
    
}
