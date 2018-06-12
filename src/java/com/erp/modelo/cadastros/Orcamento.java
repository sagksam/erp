/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.cadastros;

import com.erp.modelo.classes.comuns.EntidadeBase;
import com.erp.modelo.classes.comuns.VendaBase;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author PauloHenrique
 */
@Entity
@Table(name="orcamento")
@NamedQuery(name="orcamentos", query="SELECT o FROM Orcamento o WHERE o.ativo = :ativo")
public class Orcamento extends VendaBase implements Serializable{
    
    @OneToMany(mappedBy="orcamento", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
    private List<OrcamentoProdutos> orcamentosProdutos;
    @OneToMany(mappedBy="orcamento", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
    private List<OrcamentoServicos> orcamentosServicos;
    
    public Orcamento(){
    
        super();
    }

    public Orcamento(List<OrcamentoProdutos> orcamentosProdutos, List<OrcamentoServicos> orcamentosServicos, String tipo, Filial filial, Empresa empresa, Pessoa pessoa, String descricao, Date dataEmissao, String status, String cep, String endereco, int numero, String complemento, String bairro, String cidade, String ibge, String siafi, String estado, String pais, String telefone, String celular, String site, String email, double valorProdutos, double valorServicos, double valorDesconto, double baseIPI, double valorIPI, double baseICMS, double valorICMS, double baseICMSST, double valorICMSST, double valorFrete, double valorSeguro, String prazoPagamento, int parcelas, double valorParcela, double valorTotal, ContaBancaria contaBancaria, String formaPagamento, String detalhes) {
        super(tipo, filial, empresa, pessoa, descricao, dataEmissao, status, cep, endereco, numero, complemento, bairro, cidade, ibge, siafi, estado, pais, telefone, celular, site, email, valorProdutos, valorServicos, valorDesconto, baseIPI, valorIPI, baseICMS, valorICMS, baseICMSST, valorICMSST, valorFrete, valorSeguro, prazoPagamento, parcelas, valorParcela, valorTotal, contaBancaria, formaPagamento, detalhes);
        this.orcamentosProdutos = orcamentosProdutos;
        this.orcamentosServicos = orcamentosServicos;
    }

    public List<OrcamentoProdutos> getOrcamentosProdutos() {
        return orcamentosProdutos;
    }

    public void setOrcamentosProdutos(List<OrcamentoProdutos> orcamentosProdutos) {
        this.orcamentosProdutos = orcamentosProdutos;
    }

    public List<OrcamentoServicos> getOrcamentosServicos() {
        return orcamentosServicos;
    }

    public void setOrcamentosServicos(List<OrcamentoServicos> orcamentosServicos) {
        this.orcamentosServicos = orcamentosServicos;
    }
 
    @Override
    public String toString() {
        return "--- Orcamento[ id=" + super.getId() + " ] --- ";
    }

}
