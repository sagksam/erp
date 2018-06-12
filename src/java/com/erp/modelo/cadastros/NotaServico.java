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
import javax.persistence.Temporal;
import org.hibernate.annotations.NamedQuery;
/**
 *
 * @author PauloHenrique
 */
@Entity
@Table(name="notaservico")
@NamedQuery(name="notasServicos", query="SELECT n FROM NotaServico n WHERE n.ativo = :ativo")
public class NotaServico extends VendaBase implements Serializable{
    
    private int rps;
    private int notaFiscal;
    private String atividade;
    private String descricaoAtividade;
    private String recolhimento;
    private String loteAutorizacao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataAutorizacao;
    private String verificacaoAutorizacao;
    private String motivoCancelamento;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCancelamento;
    private String verificacaoCancelamento;
    @OneToMany(mappedBy="notaServico", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
    private List<NotaServicoServicos> notaServicoServicos;
    private double baseISSQN;
    private double aliquotaISSQN;
    private String recolhimentoISSQN;
    private double valorISSQN;
    private String acumuloCNPJ;
    private String recolhimentoPIS;
    private double aliquotaPIS;
    private double valorPIS;
    private double aliquotaCOFINS;
    private String recolhimentoCOFINS;
    private double valorCOFINS;
    private double aliquotaCSLL;
    private String recolhimentoCSLL;
    private double valorCSLL;
    private double aliquotaIR;
    private String recolhimentoIR;
    private double valorIR;
    private double aliquotaINSS;
    private double valorINSS;
    
    public NotaServico(){
    
        super();
        
    }

    public NotaServico(int rps, int notaFiscal, String atividade, String descricaoAtividade, String recolhimento, String loteAutorizacao, Date dataAutorizacao, String verificacaoAutorizacao, String motivoCancelamento, Date dataCancelamento, String verificacaoCancelamento, List<NotaServicoServicos> notaServicoServicos, double baseISSQN, double aliquotaISSQN, String recolhimentoISSQN, double valorISSQN, String acumuloCNPJ, String recolhimentoPIS, double aliquotaPIS, double valorPIS, double aliquotaCOFINS, String recolhimentoCOFINS, double valorCOFINS, double aliquotaCSLL, String recolhimentoCSLL, double valorCSLL, double aliquotaIR, String recolhimentoIR, double valorIR, double aliquotaINSS, double valorINSS, String tipo, Filial filial, Empresa empresa, Pessoa pessoa, String descricao, Date dataEmissao, String status, String cep, String endereco, int numero, String complemento, String bairro, String cidade, String ibge, String siafi, String estado, String pais, String telefone, String celular, String site, String email, double valorProdutos, double valorServicos, double valorDesconto, double baseIPI, double valorIPI, double baseICMS, double valorICMS, double baseICMSST, double valorICMSST, double valorFrete, double valorSeguro, String prazoPagamento, int parcelas, double valorParcela, double valorTotal, ContaBancaria contaBancaria, String formaPagamento, String detalhes) {
        super(tipo, filial, empresa, pessoa, descricao, dataEmissao, status, cep, endereco, numero, complemento, bairro, cidade, ibge, siafi, estado, pais, telefone, celular, site, email, valorProdutos, valorServicos, valorDesconto, baseIPI, valorIPI, baseICMS, valorICMS, baseICMSST, valorICMSST, valorFrete, valorSeguro, prazoPagamento, parcelas, valorParcela, valorTotal, contaBancaria, formaPagamento, detalhes);
        this.rps = rps;
        this.notaFiscal = notaFiscal;
        this.atividade = atividade;
        this.descricaoAtividade = descricaoAtividade;
        this.recolhimento = recolhimento;
        this.loteAutorizacao = loteAutorizacao;
        this.dataAutorizacao = dataAutorizacao;
        this.verificacaoAutorizacao = verificacaoAutorizacao;
        this.motivoCancelamento = motivoCancelamento;
        this.dataCancelamento = dataCancelamento;
        this.verificacaoCancelamento = verificacaoCancelamento;        
        this.notaServicoServicos = notaServicoServicos;
        this.baseISSQN = baseISSQN;
        this.aliquotaISSQN = aliquotaISSQN;
        this.recolhimentoISSQN = recolhimentoISSQN;
        this.valorISSQN = valorISSQN;
        this.acumuloCNPJ = acumuloCNPJ;
        this.recolhimentoPIS = recolhimentoPIS;
        this.aliquotaPIS = aliquotaPIS;
        this.valorPIS = valorPIS;
        this.aliquotaCOFINS = aliquotaCOFINS;
        this.recolhimentoCOFINS = recolhimentoCOFINS;
        this.valorCOFINS = valorCOFINS;
        this.aliquotaCSLL = aliquotaCSLL;
        this.recolhimentoCSLL = recolhimentoCSLL;
        this.valorCSLL = valorCSLL;
        this.aliquotaIR = aliquotaIR;
        this.recolhimentoIR = recolhimentoIR;
        this.valorIR = valorIR;
        this.aliquotaINSS = aliquotaINSS;
        this.valorINSS = valorINSS;
    }

    public int getRps() {
        return rps;
    }

    public void setRps(int rps) {
        this.rps = rps;
    }

    public int getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(int notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public String getDescricaoAtividade() {
        return descricaoAtividade;
    }

    public void setDescricaoAtividade(String descricaoAtividade) {
        this.descricaoAtividade = descricaoAtividade;
    }

    public String getRecolhimento() {
        return recolhimento;
    }

    public void setRecolhimento(String recolhimento) {
        this.recolhimento = recolhimento;
    }

    public String getLoteAutorizacao() {
        return loteAutorizacao;
    }

    public void setLoteAutorizacao(String loteAutorizacao) {
        this.loteAutorizacao = loteAutorizacao;
    }

    public Date getDataAutorizacao() {
        return dataAutorizacao;
    }

    public void setDataAutorizacao(Date dataAutorizacao) {
        this.dataAutorizacao = dataAutorizacao;
    }

    public String getVerificacaoAutorizacao() {
        return verificacaoAutorizacao;
    }

    public void setVerificacaoAutorizacao(String verificacaoAutorizacao) {
        this.verificacaoAutorizacao = verificacaoAutorizacao;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    public Date getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(Date dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public String getVerificacaoCancelamento() {
        return verificacaoCancelamento;
    }

    public void setVerificacaoCancelamento(String verificacaoCancelamento) {
        this.verificacaoCancelamento = verificacaoCancelamento;
    }
    
    public double getBaseISSQN() {
        return baseISSQN;
    }

    public void setBaseISSQN(double baseISSQN) {
        this.baseISSQN = baseISSQN;
    }

    public double getAliquotaISSQN() {
        return aliquotaISSQN;
    }

    public void setAliquotaISSQN(double aliquotaISSQN) {
        this.aliquotaISSQN = aliquotaISSQN;
    }

    public String getRecolhimentoISSQN() {
        return recolhimentoISSQN;
    }

    public void setRecolhimentoISSQN(String recolhimentoISSQN) {
        this.recolhimentoISSQN = recolhimentoISSQN;
    }

    public double getValorISSQN() {
        return valorISSQN;
    }

    public void setValorISSQN(double valorISSQN) {
        this.valorISSQN = valorISSQN;
    }

    public String getAcumuloCNPJ() {
        return acumuloCNPJ;
    }

    public void setAcumuloCNPJ(String acumuloCNPJ) {
        this.acumuloCNPJ = acumuloCNPJ;
    }

    public String getRecolhimentoPIS() {
        return recolhimentoPIS;
    }

    public void setRecolhimentoPIS(String recolhimentoPIS) {
        this.recolhimentoPIS = recolhimentoPIS;
    }

    public double getAliquotaPIS() {
        return aliquotaPIS;
    }

    public void setAliquotaPIS(double aliquotaPIS) {
        this.aliquotaPIS = aliquotaPIS;
    }

    public double getValorPIS() {
        return valorPIS;
    }

    public void setValorPIS(double valorPIS) {
        this.valorPIS = valorPIS;
    }

    public double getAliquotaCOFINS() {
        return aliquotaCOFINS;
    }

    public void setAliquotaCOFINS(double aliquotaCOFINS) {
        this.aliquotaCOFINS = aliquotaCOFINS;
    }

    public String getRecolhimentoCOFINS() {
        return recolhimentoCOFINS;
    }

    public void setRecolhimentoCOFINS(String recolhimentoCOFINS) {
        this.recolhimentoCOFINS = recolhimentoCOFINS;
    }

    public double getValorCOFINS() {
        return valorCOFINS;
    }

    public void setValorCOFINS(double valorCOFINS) {
        this.valorCOFINS = valorCOFINS;
    }

    public double getAliquotaCSLL() {
        return aliquotaCSLL;
    }

    public void setAliquotaCSLL(double aliquotaCSLL) {
        this.aliquotaCSLL = aliquotaCSLL;
    }

    public String getRecolhimentoCSLL() {
        return recolhimentoCSLL;
    }

    public void setRecolhimentoCSLL(String recolhimentoCSLL) {
        this.recolhimentoCSLL = recolhimentoCSLL;
    }

    public double getValorCSLL() {
        return valorCSLL;
    }

    public void setValorCSLL(double valorCSLL) {
        this.valorCSLL = valorCSLL;
    }

    public double getAliquotaIR() {
        return aliquotaIR;
    }

    public void setAliquotaIR(double aliquotaIR) {
        this.aliquotaIR = aliquotaIR;
    }

    public String getRecolhimentoIR() {
        return recolhimentoIR;
    }

    public void setRecolhimentoIR(String recolhimentoIR) {
        this.recolhimentoIR = recolhimentoIR;
    }

    public double getValorIR() {
        return valorIR;
    }

    public void setValorIR(double valorIR) {
        this.valorIR = valorIR;
    }

    public double getAliquotaINSS() {
        return aliquotaINSS;
    }

    public void setAliquotaINSS(double aliquotaINSS) {
        this.aliquotaINSS = aliquotaINSS;
    }

    public double getValorINSS() {
        return valorINSS;
    }

    public void setValorINSS(double valorINSS) {
        this.valorINSS = valorINSS;
    }
    
   
    public List<NotaServicoServicos> getNotaServicoServicos() {
        return notaServicoServicos;
    }

    public void setNotaServicoServicos(List<NotaServicoServicos> notaServicoServicos) {
        this.notaServicoServicos = notaServicoServicos;
    }
    
    @Override
    public String toString() {
        return "--- NotaServico[ id=" + super.getId() + " ] --- ";
    }
    
    
}
