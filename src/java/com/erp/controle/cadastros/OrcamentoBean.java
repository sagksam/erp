/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.cadastros;

import com.erp.acesso.dados.GenericDAO;
import com.erp.controle.classes.comuns.BaseBean;
import com.erp.controle.classes.comuns.ListaBean;
import com.erp.modelo.cadastros.Orcamento;
import com.erp.modelo.cadastros.OrcamentoProdutos;
import com.erp.modelo.cadastros.OrcamentoServicos;
import com.erp.modelo.cadastros.Produto;
import com.erp.modelo.cadastros.Servico;
import com.erp.modelo.classes.comuns.Lista.PrazoPagamento;
import com.erp.util.Conversores;
import com.erp.util.JPAUtil;
import com.erp.util.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;



/**
 *
 * @author PauloHenrique
 */
@ManagedBean(name="orcamentoBean")
@SessionScoped
public class OrcamentoBean extends BaseBean implements Serializable {

    private Orcamento orcamento;
    private List<Orcamento> orcamentos;
    private OrcamentoServicos orcamentoServicos;
    private Servico servico;
    private OrcamentoProdutos orcamentoProdutos;
    private Produto produto;

    //Método que inicializa os campos da classe
    @PostConstruct
    public void init() {

        orcamento = new Orcamento();
        orcamentos = new ArrayList<>();
        orcamentoServicos = new OrcamentoServicos();
        servico = new Servico();
        orcamentoProdutos = new OrcamentoProdutos();
        produto = new Produto();
    }

    //Getters e Setters 
    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public List<Orcamento> getOrcamentos() {
        EntityManager manager = null;
        try {
            manager = JPAUtil.getEntityManager();
            Query query = manager.createNamedQuery("orcamentos")
                    .setParameter("ativo", true);
            this.orcamentos = query.getResultList();
            return orcamentos;
        } 
        catch (Exception e) {

            return new ArrayList<>();
        } 
        finally {

            manager.close();
        }
    }

    public void setOrcamentos(List<Orcamento> orcamentos) {
        this.orcamentos = orcamentos;
    }

    public OrcamentoServicos getOrcamentoServicos() {
        if (orcamentoServicos == null) {
            orcamentoServicos = new OrcamentoServicos();
        }
        return orcamentoServicos;
    }

    public void setOrcamentoServicos(OrcamentoServicos orcamentoServicos) {
        this.orcamentoServicos = orcamentoServicos;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public OrcamentoProdutos getOrcamentoProdutos() {
        if (orcamentoProdutos == null) {
            orcamentoProdutos = new OrcamentoProdutos();
        }
        return orcamentoProdutos;
    }

    public void setOrcamentoProdutos(OrcamentoProdutos orcamentoProdutos) {
        this.orcamentoProdutos = orcamentoProdutos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    //Método que adiciona um produto ao Orcamento    
    public String addProduto() {

        if (getOrcamento().getOrcamentosProdutos() == null) {
            getOrcamento().setOrcamentosProdutos(new ArrayList<OrcamentoProdutos>());
        }
        if (!isModoEdicao()) {
            getOrcamentoProdutos().setOrcamento(getOrcamento());
            getOrcamentoProdutos().setProduto(getProduto());
            getOrcamentoProdutos().setDescricaoProduto(getProduto().getDescricao());
            getOrcamento().getOrcamentosProdutos().add(getOrcamentoProdutos());
        }
        setModoEdicao(false);
        configurarValores();
        selecionarParcelas();
        produto = new Produto();
        orcamentoProdutos = new OrcamentoProdutos();
        return getCadastro();
    }

    //Método que remove um produto do Orcamento
    public String removeProduto(OrcamentoProdutos orcamentoProduto) {

        if (orcamentoProduto.getId() > 0) {
            getOrcamento().getOrcamentosProdutos().remove(orcamentoProduto);
            try {
                new GenericDAO().delete(orcamentoProduto);
            } catch (Exception e) {
                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
            }
        } 
        else {
            getOrcamento().getOrcamentosProdutos().remove(orcamentoProduto);
        }
        configurarValores();
        selecionarParcelas();
        return getCadastro();
    }

    //Método que adiciona um servico ao Orcamento
    public String addServico() {

        if (getOrcamento().getOrcamentosServicos() == null) {
            getOrcamento().setOrcamentosServicos(new ArrayList<OrcamentoServicos>());
        }
        if (!isModoEdicao()) {
            getOrcamentoServicos().setOrcamento(getOrcamento());
            getOrcamentoServicos().setServico(getServico());
            getOrcamentoServicos().setDescricaoServico(getServico().getDescricao());
            getOrcamento().getOrcamentosServicos().add(getOrcamentoServicos());
        }
        setModoEdicao(false);
        configurarValores();
        selecionarParcelas();
        servico = new Servico();
        orcamentoServicos = new OrcamentoServicos();
        return getCadastro();
    }

    //Método que remove um servico do Orcamento
    public String removeServico(OrcamentoServicos orcamentoServico) {

        if (orcamentoServico.getId() > 0) {
            getOrcamento().getOrcamentosServicos().remove(orcamentoServico);
            try {
                new GenericDAO().delete(orcamentoServico);
            } 
            catch (Exception e) {
                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
            }
        } 
        else {
            getOrcamento().getOrcamentosServicos().remove(orcamentoServico);
        }
        configurarValores();
        selecionarParcelas();
        return getCadastro();
    }

    //Método para configurar a selecao de uma empresa no Orcamento
    public void selecionarEmpresa() {

        getOrcamento().setPessoa(null);
        getOrcamento().setCep(getOrcamento().getEmpresa().getCep());
        getOrcamento().setEndereco(getOrcamento().getEmpresa().getEndereco());
        getOrcamento().setNumero(getOrcamento().getEmpresa().getNumero());
        getOrcamento().setComplemento(getOrcamento().getEmpresa().getComplemento());
        getOrcamento().setCidade(getOrcamento().getEmpresa().getCidade());
        getOrcamento().setBairro(getOrcamento().getEmpresa().getBairro());
        getOrcamento().setIbge(getOrcamento().getEmpresa().getIbge());
        getOrcamento().setSiafi(getOrcamento().getEmpresa().getSiafi());
        getOrcamento().setEstado(getOrcamento().getEmpresa().getEstado());
        getOrcamento().setPais(getOrcamento().getEmpresa().getPais());
        getOrcamento().setEmail(getOrcamento().getEmpresa().getEmail());

    }

    //Método para configurar a selecao de uma pessoa no Orcamento
    public void selecionarPessoa() {

        getOrcamento().setEmpresa(null);
        getOrcamento().setCep(getOrcamento().getPessoa().getCep());
        getOrcamento().setEndereco(getOrcamento().getPessoa().getEndereco());
        getOrcamento().setNumero(getOrcamento().getPessoa().getNumero());
        getOrcamento().setComplemento(getOrcamento().getPessoa().getComplemento());
        getOrcamento().setBairro(getOrcamento().getPessoa().getBairro());
        getOrcamento().setCidade(getOrcamento().getPessoa().getCidade());
        getOrcamento().setIbge(getOrcamento().getPessoa().getIbge());
        getOrcamento().setSiafi(getOrcamento().getPessoa().getSiafi());
        getOrcamento().setEstado(getOrcamento().getPessoa().getEstado());
        getOrcamento().setPais(getOrcamento().getPessoa().getPais());
        getOrcamento().setEmail(getOrcamento().getPessoa().getEmail());
    }

    //Método para configurar a referencia do produto ao seleciona-lo no Orcamento
    public void selecionarProduto() {

        getOrcamentoProdutos().setReferencia(getProduto().getReferencia());
        getOrcamentoProdutos().setUnidade(getProduto().getUnidade());
    }

    //Método para configurar a unidade do servico ao seleciona-lo no Orcamento
    public void selecionarServico() {

        getOrcamentoServicos().setUnidade(getServico().getUnidade());
    }

    //Método que configura as parcelas e o valor das parcelas no Orcamento
    public void selecionarParcelas() {

        for (PrazoPagamento prazo : new ListaBean().getPrazoPagamento()) {
            if (getOrcamento().getPrazoPagamento().equals(prazo.toString())) {
                getOrcamento().setParcelas(prazo.getParcelas());
                getOrcamento().setValorParcela(getOrcamento().getValorTotal() / prazo.getParcelas());
            }
        }
    }

    //Método para configurar os valores finais que sao trazidos dos itens do Orcamento
    public void configurarValores() {

        double valorProdutos = 0;
        double valorServicos = 0;
        double valorDesconto = 0;
        double baseIPI = 0;
        double valorIPI = 0;
        double baseICMS = 0;
        double valorICMS = 0;
        double baseICMSST = 0;
        double valorICMSST = 0;
        double valorFrete = 0;
        double valorSeguro = 0;
        double valorTotal = 0;

        if(getOrcamento().getOrcamentosProdutos() != null){
            try {
                for (OrcamentoProdutos c : getOrcamento().getOrcamentosProdutos()) {

                    valorProdutos += c.getValorProduto();
                    valorDesconto += c.getValorDesconto();
                    baseIPI += c.getBaseIPI();
                    valorIPI += c.getValorIPI();
                    baseICMS += c.getBaseICMS();
                    valorICMS += c.getValorICMS();
                    baseICMSST += c.getBaseICMSST();
                    valorICMSST += c.getValorICMSST();
                    valorFrete += c.getValorFrete();
                    valorSeguro += c.getValorSeguro();
                    valorTotal += c.getValorTotal();
                }
            } 
            catch (Exception e) {
                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
            }
        }

        if(getOrcamento().getOrcamentosServicos() != null){
            try {
                for (OrcamentoServicos c : getOrcamento().getOrcamentosServicos()) {
                    valorServicos += c.getValorServico();
                    valorDesconto += c.getValorDesconto();
                    valorTotal += c.getValorTotal();
                }
            } 
            catch (Exception e) {
                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
            }
        }
        
        getOrcamento().setValorProdutos(valorProdutos);
        getOrcamento().setValorServicos(valorServicos);
        getOrcamento().setValorDesconto(valorDesconto);
        getOrcamento().setBaseIPI(baseIPI);
        getOrcamento().setValorIPI(valorIPI);
        getOrcamento().setBaseICMS(baseICMS);
        getOrcamento().setValorICMS(valorICMS);
        getOrcamento().setBaseICMSST(baseICMSST);
        getOrcamento().setValorICMSST(valorICMSST);
        getOrcamento().setValorFrete(valorFrete);
        getOrcamento().setValorSeguro(valorSeguro);
        getOrcamento().setValorTotal(valorTotal);
    }

    @Override
    public String novo() {

        orcamento = new Orcamento();
        servico = new Servico();
        return getCadastro();
    }

    @Override
    public String toString() {

        return "orcamento";
    }

    @Override
    public String getNomeCadastro() {

        return "Orçamentos de Venda";
    }

    @Override
    public String getDescricaoCadastro() {

        return "Cadastre e gerencie aqui os orçamentos de sua empresa, gere também "
                + "pedidos e notas através desses orçamentos.";
    }

    @Override
    public String getDescricaoRelatorio() {

        return "Acompanhe aqui os orçamentos de sua empresa "
                + "filtre também esses orçamentos por data de emissão, status ou descrição do cliente";
    }
    
    //Método que retorna os dados que serão exibidos no impresso 
    @Override
    public Map<String, Object> dadosImpressao(){
    
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ID: ", getOrcamento().getId());
        map.put("Filial: ", getOrcamento().getFilial());
        map.put("Descrição: ", getOrcamento().getDescricao());
        map.put("Emissão: ", Conversores.dateToString(getOrcamento().getDataEmissao()));
        map.put("Cep: ", getOrcamento().getCep());
        map.put("Endereço: ", getOrcamento().getEndereco());        
        map.put("Número: ", getOrcamento().getNumero());
        map.put("Bairro: ", getOrcamento().getBairro());
        map.put("Cidade: ", getOrcamento().getCidade());
        map.put("Estado: ", getOrcamento().getEstado());
        return map;
    }
    
    //Método que retorna os dados dos produtos que serão exibidos no impresso 
    @Override
    public List dadosImpressaoProdutos(){
    
        List<Map> listMap = new ArrayList<>();
        for(OrcamentoProdutos orcamentoProduto: getOrcamento().getOrcamentosProdutos()){                  
          Map<String, Object> map = new LinkedHashMap<>();
          map.put("Referência:", orcamentoProduto.getReferencia());
          map.put("Produto:", orcamentoProduto.getDescricaoProduto());
          map.put("Quantidade:", orcamentoProduto.getQuantidade());
          map.put("Valor Unitário:", orcamentoProduto.getValorUnitario());
          map.put("Valor Produto:", "R$ " + orcamentoProduto.getValorProduto());
          map.put("Valor Desconto", "R$ " + orcamentoProduto.getValorDesconto());
          map.put("Valor Total:", "R$ " + orcamentoProduto.getValorTotal());
          listMap.add(map);
        }  
        return listMap;
    }
    
    //Método que retorna os dados dos servicos que serão exibidos no impresso 
    @Override
    public List dadosImpressaoServicos(){
    
        List<Map> listMap = new ArrayList<>();
        for(OrcamentoServicos orcamentoServico: getOrcamento().getOrcamentosServicos()){                  
          Map<String, Object> map = new LinkedHashMap<>();
          map.put("Serviço:", orcamentoServico.getDescricaoServico());
          map.put("Quantidade:", orcamentoServico.getQuantidade());
          map.put("Valor Unitário:", orcamentoServico.getValorUnitario());
          map.put("Valor Serviço:", "R$ " + orcamentoServico.getValorServico());
          map.put("Valor Desconto", "R$ " + orcamentoServico.getValorDesconto());
          map.put("Valor Total:", "R$ " + orcamentoServico.getValorTotal());
          listMap.add(map);
        }  
        return listMap;
    }
    
    //Método que retorna os dados totais que serão exibidos no impresso 
    @Override
    public Map<String, Object> dadosImpressaoTotais(){
    
        Map<String, Object> map = new LinkedHashMap<>();     
        map.put("Valor Produto: ", "R$ " + getOrcamento().getValorProdutos());
        map.put("Valor Serviço: ", "R$ " + getOrcamento().getValorServicos());
        map.put("Valor Desconto: ", "R$ " + getOrcamento().getValorDesconto());
        map.put("Valor Total: ", "R$ " + getOrcamento().getValorTotal());
        return map;
    }
}
