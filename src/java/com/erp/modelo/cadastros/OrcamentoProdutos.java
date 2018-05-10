/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.cadastros;

import com.erp.modelo.classes.comuns.VendaProdutosBase;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author PauloHenrique
 */
@Entity
@Table(name="orcamentoProdutos")
public class OrcamentoProdutos extends VendaProdutosBase implements Serializable{
    
    @ManyToOne
    @JoinColumn(name="orcamentoId")
    private Orcamento orcamento;
    
    
    public OrcamentoProdutos(){
    
        super();
    }

    public OrcamentoProdutos(Orcamento orcamento, Produto produto, String referencia, String descricaoProduto, String unidade, double estoqueAtual, int quantidade, double valorUnitario, double valorProduto, double desconto, double valorDesconto, double margemIPI, double baseIPI, double aliquotaIPI, double valorIPI, double margemICMS, double baseICMS, double aliquotaICMS, double valorICMS, double margemICMSST, double baseICMSST, double aliquotaICMSST, double valorICMSST, double valorFrete, double valorSeguro, double outrasDespesas, double valorTotal) {
        super(produto, referencia, descricaoProduto, unidade, estoqueAtual, quantidade, valorUnitario, valorProduto, desconto, valorDesconto, margemIPI, baseIPI, aliquotaIPI, valorIPI, margemICMS, baseICMS, aliquotaICMS, valorICMS, margemICMSST, baseICMSST, aliquotaICMSST, valorICMSST, valorFrete, valorSeguro, outrasDespesas, valorTotal);
        this.orcamento = orcamento;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    
    
    
}
