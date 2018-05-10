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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author PauloHenrique
 */
@Entity
@Table(name="pedido")
@NamedQueries({
    @NamedQuery(name="pedidosVenda", query="SELECT p FROM Pedido p WHERE p.tipo = :tipo AND p.ativo = :ativo"),
    @NamedQuery(name="pedidosCompra", query="SELECT p FROM Pedido p WHERE p.tipo = :tipo AND p.ativo = :ativo"),
})
public class Pedido extends VendaBase implements Serializable{
    
    @OneToMany(mappedBy="pedido", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<PedidoProdutos> pedidosProdutos;
    @OneToMany(mappedBy="pedido", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<PedidoServicos> pedidosServicos;
    
    public Pedido(){
        super();
        this.setTipo("Venda");
    }
    
    public Pedido(String tipo, Filial filial, Empresa empresa, Pessoa pessoa, String descricao, Date dataEmissao, String status, String cep, String endereco, int numero, String complemento, String bairro, String cidade, String ibge, String siafi, String estado, String pais, String telefone, String celular, String site, String email, double valorProdutos, double valorServicos, double valorDesconto, double baseIPI, double valorIPI, double baseICMS, double valorICMS, double baseICMSST, double valorICMSST, double valorFrete, double valorSeguro, String prazoPagamento, int parcelas, double valorParcela, double valorTotal, ContaBancaria contaBancaria, String formaPagamento, String detalhes, List<PedidoProdutos> pedidosProdutos, List<PedidoServicos> pedidosServicos) {
        super(tipo, filial, empresa, pessoa, descricao, dataEmissao, status, cep, endereco, numero, complemento, bairro, cidade, ibge, siafi, estado, pais, telefone, celular, site, email, valorProdutos, valorServicos, valorDesconto, baseIPI, valorIPI, baseICMS, valorICMS, baseICMSST, valorICMSST, valorFrete, valorSeguro, prazoPagamento, parcelas, valorParcela, valorTotal, contaBancaria, formaPagamento, detalhes);
        this.pedidosProdutos = pedidosProdutos;
        this.pedidosServicos = pedidosServicos;
    }

    public List<PedidoProdutos> getPedidosProdutos() {
        return pedidosProdutos;
    }

    public void setPedidosProdutos(List<PedidoProdutos> pedidosProdutos) {
        this.pedidosProdutos = pedidosProdutos;
    }

    public List<PedidoServicos> getPedidosServicos() {
        return pedidosServicos;
    }

    public void setPedidosServicos(List<PedidoServicos> pedidosServicos) {
        this.pedidosServicos = pedidosServicos;
    }
    

    @Override
    public String toString() {
        return "--- Pedido[ id=" + super.getId() + " ] --- ";
    }

    
}
