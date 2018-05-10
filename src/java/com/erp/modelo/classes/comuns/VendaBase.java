/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.classes.comuns;

import com.erp.modelo.cadastros.ContaBancaria;
import com.erp.modelo.cadastros.Empresa;
import com.erp.modelo.cadastros.Filial;
import com.erp.modelo.cadastros.NotaProduto;
import com.erp.modelo.cadastros.NotaServico;
import com.erp.modelo.cadastros.Pessoa;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author PauloHenrique
 */
@MappedSuperclass
public class VendaBase extends EntidadeBase implements Serializable{
    
    @Column(length=30)
    private String tipo;
    @OneToOne()
    @JoinColumn(name="filialId")
    private Filial filial;
    @OneToOne
    @JoinColumn(name="empresaId")
    private Empresa empresa;
    @OneToOne
    @JoinColumn(name="pessoaId")
    private Pessoa pessoa;
    @Column(length=250)
    private String descricao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataEmissao;
    @Column(length=30)
    private String status;
    @Column(length=30)
    private String cep;
    @Column(length=250)
    private String endereco;
    private int numero;
    @Column(length=250)
    private String complemento;
    @Column(length=30)
    private String bairro;
    @Column(length=30)
    private String cidade;
    @Column(length=10)
    private String ibge;
    @Column(length=10)
    private String siafi;
    @Column(length=10)
    private String estado;
    @Column(length=30)
    private String pais;
    @Column(length=30)
    private String telefone;
    @Column(length=30)
    private String celular;
    @Column(length=30)
    private String site;
    @Column(length=30)
    private String email;
    private double valorProdutos;
    private double valorServicos;
    private double valorDesconto;
    private double baseIPI;
    private double valorIPI;
    private double baseICMS;
    private double valorICMS;
    private double baseICMSST;
    private double valorICMSST;
    private double valorFrete;
    private double valorSeguro;   
    @Column(length=30)
    private String prazoPagamento;
    private int parcelas;
    private double valorParcela;
    private double valorTotal;
    @OneToOne
    @JoinColumn(name="contaBancariaId")
    private ContaBancaria contaBancaria;
    @Column(length=30)
    private String formaPagamento;
    @Column(columnDefinition="TEXT")
    private String detalhes;
    
    public VendaBase(){
    
        this.parcelas = 1;
        this.dataEmissao = new Date();
        if(this instanceof NotaProduto || this instanceof NotaServico)
            this.status = Lista.NotaProdutoStatus.DIGITADA.toString();
    }

    public VendaBase(String tipo, Filial filial, Empresa empresa, Pessoa pessoa, String descricao, Date dataEmissao, String status, String cep, String endereco, int numero, String complemento, String bairro, String cidade, String ibge, String siafi, String estado, String pais, String telefone, String celular, String site, String email, double valorProdutos, double valorServicos, double valorDesconto, double baseIPI, double valorIPI, double baseICMS, double valorICMS, double baseICMSST, double valorICMSST, double valorFrete, double valorSeguro, String prazoPagamento, int parcelas, double valorParcela, double valorTotal, ContaBancaria contaBancaria, String formaPagamento, String detalhes) {
        this.tipo = tipo;
        this.filial = filial;
        this.empresa = empresa;
        this.pessoa = pessoa;
        this.descricao = descricao;
        this.dataEmissao = dataEmissao;
        this.status = status;
        this.cep = cep;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.ibge = ibge;
        this.siafi = siafi;
        this.estado = estado;
        this.pais = pais;
        this.telefone = telefone;
        this.celular = celular;
        this.site = site;
        this.email = email;
        this.valorProdutos = valorProdutos;
        this.valorServicos = valorServicos;
        this.valorDesconto = valorDesconto;
        this.baseIPI = baseIPI;
        this.valorIPI = valorIPI;
        this.baseICMS = baseICMS;
        this.valorICMS = valorICMS;
        this.baseICMSST = baseICMSST;
        this.valorICMSST = valorICMSST;
        this.valorFrete = valorFrete;
        this.valorSeguro = valorSeguro;
        this.prazoPagamento = prazoPagamento;
        this.parcelas = parcelas;
        this.valorParcela = valorParcela;
        this.valorTotal = valorTotal;
        this.contaBancaria = contaBancaria;
        this.formaPagamento = formaPagamento;
        this.detalhes = detalhes;
    }    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
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

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getSiafi() {
        return siafi;
    }

    public void setSiafi(String siafi) {
        this.siafi = siafi;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getValorProdutos() {
        return valorProdutos;
    }

    public void setValorProdutos(double valorProdutos) {
        this.valorProdutos = valorProdutos;
    }

    public double getValorServicos() {
        return valorServicos;
    }

    public void setValorServicos(double valorServicos) {
        this.valorServicos = valorServicos;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public double getBaseIPI() {
        return baseIPI;
    }

    public void setBaseIPI(double baseIPI) {
        this.baseIPI = baseIPI;
    }

    public double getValorIPI() {
        return valorIPI;
    }

    public void setValorIPI(double valorIPI) {
        this.valorIPI = valorIPI;
    }

    public double getBaseICMS() {
        return baseICMS;
    }

    public void setBaseICMS(double baseICMS) {
        this.baseICMS = baseICMS;
    }

    public double getValorICMS() {
        return valorICMS;
    }

    public void setValorICMS(double valorICMS) {
        this.valorICMS = valorICMS;
    }

    public double getBaseICMSST() {
        return baseICMSST;
    }

    public void setBaseICMSST(double baseICMSST) {
        this.baseICMSST = baseICMSST;
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

    public String getPrazoPagamento() {
        return prazoPagamento;
    }

    public void setPrazoPagamento(String prazoPagamento) {
        this.prazoPagamento = prazoPagamento;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
    
    
}
