/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.cadastros;

import com.erp.modelo.classes.comuns.EntidadeBase;
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
 * @author bianca
 */
@Entity
@Table(name="inventario")
@NamedQuery(name="inventarios", query="SELECT i FROM Inventario i WHERE i.ativo = :ativo")
public class Inventario extends EntidadeBase implements Serializable{
    
    @OneToOne
    @JoinColumn(name="filialId")
    private Filial filial;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data;
    private String tipo;
    private String motivo;
    private String responsavel;
    private boolean aplicado;
    private String detalhes;
    @OneToMany(mappedBy="inventario", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<InventarioProdutos> inventarioProdutos;

    public Inventario() {
    }

    public Inventario(Filial filial, Date data, String tipo, String motivo, String responsavel, boolean aplicado, String detalhes, List<InventarioProdutos> inventarioProdutos) {
        this.filial = filial;
        this.data = data;
        this.tipo = tipo;
        this.motivo = motivo;
        this.responsavel = responsavel;
        this.aplicado = aplicado;
        this.detalhes = detalhes;
        this.inventarioProdutos = inventarioProdutos;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public boolean isAplicado() {
        return aplicado;
    }

    public void setAplicado(boolean aplicado) {
        this.aplicado = aplicado;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
    
    public List<InventarioProdutos> getInventarioProdutos() {
        return inventarioProdutos;
    }

    public void setInventarioProdutos(List<InventarioProdutos> inventarioProdutos) {
        this.inventarioProdutos = inventarioProdutos;
    }
    
    
}
