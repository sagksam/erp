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
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author Paulo
 */
@Entity
@Table(name="estoque")
@NamedQueries({
    @NamedQuery(name="estoqueEntrada", query="SELECT e FROM Estoque e WHERE e.tipo = :tipo AND e.ativo = :ativo"),
    @NamedQuery(name="estoqueSaida", query="SELECT e FROM Estoque e WHERE e.tipo = :tipo AND e.ativo = :ativo"),
})
public class Estoque extends EntidadeBase implements Serializable {
    
    private String tipo;
    @OneToOne
    @JoinColumn(name="filialId")
    private Filial filial;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data;
    private String motivo;
    private String responsavel;
    private int inventario;
    private int pedido;
    private int notaProduto;
    private String detalhes;
    @OneToMany(mappedBy="estoque", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<EstoqueProdutos> estoqueProdutos;
    
    public Estoque(){}

    public Estoque(String tipo, Filial filial, Date data, String motivo, String responsavel, int inventario, int pedido, int notaProduto, String detalhes, List<EstoqueProdutos> estoqueProdutos) {
        this.tipo = tipo;
        this.filial = filial;
        this.data = data;
        this.motivo = motivo;
        this.responsavel = responsavel;
        this.inventario = inventario;
        this.pedido = pedido;
        this.notaProduto = notaProduto;
        this.detalhes = detalhes;
        this.estoqueProdutos = estoqueProdutos;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
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

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public List<EstoqueProdutos> getEstoqueProdutos() {
        return estoqueProdutos;
    }

    public void setEstoqueProdutos(List<EstoqueProdutos> estoqueProdutos) {
        this.estoqueProdutos = estoqueProdutos;
    }
    
}
