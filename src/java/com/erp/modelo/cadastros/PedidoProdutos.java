/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.cadastros;

import com.erp.modelo.classes.comuns.VendaProdutosBase;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author PauloHenrique
 */
@Entity
@Table(name="pedidoProdutos")
public class PedidoProdutos extends VendaProdutosBase implements Serializable{
    
    @ManyToOne
    @JoinColumn(name="pedidoId")
    private Pedido pedido;
    
    public PedidoProdutos(){
    
        super();
    }

    public PedidoProdutos(Produto produto, String referencia, String descricaoProduto, String unidade, double estoqueAtual, int quantidade, double valorUnitario, double valorProduto, double desconto, double valorDesconto, double margemIPI, double baseIPI, double aliquotaIPI, double valorIPI, double margemICMS, double baseICMS, double aliquotaICMS, double valorICMS, double margemICMSST, double baseICMSST, double aliquotaICMSST, double valorICMSST, double valorFrete, double valorSeguro, double outrasDespesas, double valorTotal, Pedido pedido) {
        super(produto, referencia, descricaoProduto, unidade, estoqueAtual, quantidade, valorUnitario, valorProduto, desconto, valorDesconto, margemIPI, baseIPI, aliquotaIPI, valorIPI, margemICMS, baseICMS, aliquotaICMS, valorICMS, margemICMSST, baseICMSST, aliquotaICMSST, valorICMSST, valorFrete, valorSeguro, outrasDespesas, valorTotal);
        this.pedido = pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    
    
}
