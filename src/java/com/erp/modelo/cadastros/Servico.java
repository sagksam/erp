/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.cadastros;

import com.erp.modelo.classes.comuns.EntidadeBase;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author PauloHenrique
 */
@Entity
@Table(name="servico")
@NamedQueries({

    @NamedQuery(name="servicos", query="SELECT e FROM Servico e WHERE e.ativo = :ativo"),
    @NamedQuery(name="servicosVenda", query="SELECT e FROM Servico e WHERE e.ativo = :ativo AND e.operacoes = :operacoes"),
    @NamedQuery(name="servicosCompra", query="SELECT e FROM Servico e WHERE e.ativo = :ativo AND e.operacoes = :operacoes"),
})
public class Servico extends EntidadeBase{
     
    @Column(length=30)
    private String categoria;
    @Column(length=30)
    private String operacoes;
    @Column(length=250)
    private String descricao;
    @Column(length=10)
    private String unidade;
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
    private double aliquotaISSQN;
    private double aliquotaIBPT;
    @OneToMany(mappedBy="servico", cascade=CascadeType.ALL)
    private List<OrcamentoServicos> orcamentosServicos;
    
    public Servico() {
    }

    public Servico(String categoria, String operacoes, String descricao, String unidade, String status, String detalhes, String moeda, double valorCusto, double margemLucro, double valorVenda, double desconto, double valorDesconto, double valorFinal, double aliquotaISSQN, double aliquotaIBPT, List<OrcamentoServicos> orcamentosServicos) {
        this.categoria = categoria;
        this.operacoes = operacoes;
        this.descricao = descricao;
        this.unidade = unidade;
        this.status = status;
        this.detalhes = detalhes;
        this.moeda = moeda;
        this.valorCusto = valorCusto;
        this.margemLucro = margemLucro;
        this.valorVenda = valorVenda;
        this.desconto = desconto;
        this.valorDesconto = valorDesconto;
        this.valorFinal = valorFinal;
        this.aliquotaISSQN = aliquotaISSQN;
        this.aliquotaIBPT = aliquotaIBPT;
        this.orcamentosServicos = orcamentosServicos;

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

    public double getAliquotaISSQN() {
        return aliquotaISSQN;
    }

    public void setAliquotaISSQN(double aliquotaISSQN) {
        this.aliquotaISSQN = aliquotaISSQN;
    }

    public double getAliquotaIBPT() {
        return aliquotaIBPT;
    }

    public void setAliquotaIBPT(double aliquotaIBPT) {
        this.aliquotaIBPT = aliquotaIBPT;
    }

    public List<OrcamentoServicos> getOrcamentosServicos() {
        return orcamentosServicos;
    }

    public void setOrcamentosServicos(List<OrcamentoServicos> orcamentosServicos) {
        this.orcamentosServicos = orcamentosServicos;
    }

    @Override
    public String toString() {
        return "--- Servico[ id=" + super.getId() + " ] --- ";
    }
    
}
