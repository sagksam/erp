/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.cadastros;

import com.erp.modelo.classes.comuns.EntidadeBase;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author PauloHenrique
 */
@Entity
@Table(name="contaBancaria")
@NamedQuery(name="contasBancarias", query="SELECT c FROM ContaBancaria c WHERE c.ativo = :ativo")
public class ContaBancaria extends EntidadeBase{
    
    @OneToOne
    @JoinColumn(name="filialId")
    private Filial filial;
    @Column(length=250)
    private String descricao;
    @Column(length=30)
    private String banco;
    @Column(length=30)
    private String agencia;
    @Column(length=30)
    private String digito;
    @Column(length=30)
    private String conta;
    @Column(length=30)
    private String tipoConta;
    @Column(length=30)
    private String status;
    
    public ContaBancaria(){}

    public ContaBancaria(Filial filial, String descricao, String banco, String agencia, String digito, String conta, String tipoConta, String status) {
        this.filial = filial;
        this.descricao = descricao;
        this.banco = banco;
        this.agencia = agencia;
        this.digito = digito;
        this.conta = conta;
        this.tipoConta = tipoConta;
        this.status = status;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getDigito() {
        return digito;
    }

    public void setDigito(String digito) {
        this.digito = digito;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map configurarImpresso(){
    
        Map impresso = new HashMap<>();
        
        impresso.put("Filial: ", getFilial().getNome());
        impresso.put("Descrição: ", getDescricao());
        impresso.put("Banco: ", getBanco());
        return impresso;
    }
    
}
