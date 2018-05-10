/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.cadastros;

import com.erp.modelo.classes.comuns.VendaServicosBase;
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
@Table(name="notaservicoservicos")
public class NotaServicoServicos extends VendaServicosBase implements Serializable{
    
    @ManyToOne
    @JoinColumn(name="notaServicoId")
    private NotaServico notaServico;

    public NotaServicoServicos() {
        
        super();
    }

    public NotaServicoServicos(NotaServico notaServico, Servico servico, String descricaoServico, String unidade, int quantidade, double valorUnitario, double valorServico, double desconto, double valorDesconto, double valorTotal) {
        super(servico, descricaoServico, unidade, quantidade, valorUnitario, valorServico, desconto, valorDesconto, valorTotal);
        this.notaServico = notaServico;
    }

    public NotaServico getNotaServico() {
        return notaServico;
    }

    public void setNotaServico(NotaServico notaServico) {
        this.notaServico = notaServico;
    }
    
    
    
}
