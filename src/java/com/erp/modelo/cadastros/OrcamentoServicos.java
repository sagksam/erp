/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.cadastros;

import com.erp.modelo.classes.comuns.EntidadeBase;
import com.erp.modelo.classes.comuns.VendaServicosBase;
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
@Table(name="orcamentoServicos")
public class OrcamentoServicos extends VendaServicosBase implements Serializable{
    
    @ManyToOne
    @JoinColumn(name="orcamentoId")
    private Orcamento orcamento;
   
    public OrcamentoServicos(){
    
        super();
    }

    public OrcamentoServicos(Orcamento orcamento, Servico servico, String descricaoServico, String unidade, int quantidade, double valorUnitario, double valorServico, double desconto, double valorDesconto, double valorTotal) {
        super(servico, descricaoServico, unidade, quantidade, valorUnitario, valorServico, desconto, valorDesconto, valorTotal);
        this.orcamento = orcamento;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }


}
