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
 * @author bianca
 */
@Entity
@Table(name="inventarioProdutos")
public class InventarioProdutos extends EntidadeBase implements Serializable{
    
    @ManyToOne
    @JoinColumn(name="produtoId")
    private Produto produto;
    private String referencia;
    private String descricao;
    private String unidade;
    private int quantidade;
    @ManyToOne
    @JoinColumn(name="inventarioId")
    private Inventario inventario;

    public InventarioProdutos() {
    }

    public InventarioProdutos(Produto produto, String referencia, String descricao, String unidade, int quantidade, Inventario inventario) {
        this.produto = produto;
        this.referencia = referencia;
        this.descricao = descricao;
        this.unidade = unidade;
        this.quantidade = quantidade;
        this.inventario = inventario;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
    
    
    
}
