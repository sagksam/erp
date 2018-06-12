/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.cadastros;

import com.erp.modelo.classes.comuns.VendaServicosBase;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author PauloHenrique
 */
@Entity
@Table(name="pedidoServicos")
public class PedidoServicos extends VendaServicosBase implements Serializable{
    
    @ManyToOne()
    @JoinColumn(name="pedidoId")
    private Pedido pedido;

    public PedidoServicos() {
        
        super();
    }

    public PedidoServicos(Servico servico, String descricaoServico, String unidade, int quantidade, double valorUnitario, double valorServico, double desconto, double valorDesconto, double valorTotal, Pedido pedido) {
        super(servico, descricaoServico, unidade, quantidade, valorUnitario, valorServico, desconto, valorDesconto, valorTotal);
        this.pedido = pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    
    
}
