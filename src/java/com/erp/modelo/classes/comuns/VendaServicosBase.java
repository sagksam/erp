/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.classes.comuns;

import com.erp.modelo.cadastros.Servico;
import java.io.Serializable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author PauloHenrique
 */
@MappedSuperclass
public class VendaServicosBase extends EntidadeBase implements Serializable{
    
    @ManyToOne
    @JoinColumn(name="servicoId")
    private Servico servico;
    private String descricaoServico;
    private String unidade;
    private int quantidade;
    private double valorUnitario;
    private double valorServico;
    private double desconto;
    private double valorDesconto;
    private double valorTotal;

   public VendaServicosBase(){}
    
    public VendaServicosBase(Servico servico, String descricaoServico, String unidade, int quantidade, double valorUnitario, double valorServico, double desconto, double valorDesconto, double valorTotal) {
        this.servico = servico;
        this.descricaoServico = descricaoServico;
        this.unidade = unidade;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorServico = valorServico;
        this.desconto = desconto;
        this.valorDesconto = valorDesconto;
        this.valorTotal = valorTotal;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
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

    public double getValorServico() {
        return valorServico;
    }

    public void setValorServico(double valorServico) {
        this.valorServico = valorServico;
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

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    
}
