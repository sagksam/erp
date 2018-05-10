/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.cadastros;

import com.erp.acesso.dados.GenericDAO;
import com.erp.controle.classes.comuns.BaseBean;
import com.erp.controle.servicos.ServicoService;
import com.erp.modelo.cadastros.Servico;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author bianca
 */
@ManagedBean
@ViewScoped
public class ServicoBean extends BaseBean implements Serializable {

    private Servico servico;
    private List<Servico> servicos;
    private List<Servico> servicosVenda;
    private List<Servico> servicosCompra;
    


    @ManagedProperty("#{servicoService}")
    private ServicoService service;

    //Método que inicia os campos da classe
    @PostConstruct
    public void init() {

        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("objetoId")){
            long id = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("objetoId").toString());
            servico = new GenericDAO().findById((int) id, Servico.class);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("objetoId");
        }
        else{
            servico = new Servico();
        }
        servicos = getService().getServicos();
        servicosVenda = getService().getServicosVenda();
        servicosCompra = getService().getServicosCompra();
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public List<Servico> getServicos() {

        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public List<Servico> getServicosVenda() {
        return servicosVenda;
    }

    public void setServicosVenda(List<Servico> servicosVenda) {
        this.servicosVenda = servicosVenda;
    }

    public List<Servico> getServicosCompra() {
        return servicosCompra;
    }

    public void setServicosCompra(List<Servico> servicosCompra) {
        this.servicosCompra = servicosCompra;
    }
    
    public ServicoService getService() {
        return service;
    }

    public void setService(ServicoService service) {
        this.service = service;
    }

    @Override
    public String novo() {

        setServico(new Servico());
        return getCadastro();
    }

    @Override
    public String toString() {

        return "servico";
    }

    @Override
    public String getNomeCadastro() {

        return "Serviços";
    }

    @Override
    public String getDescricaoCadastro() {

        return "Cadastre e gerencie aqui os serviços executados por sua empresa "
                + "ou até mesmo de terceiros, serviços esses que serão gerenciados "
                + " em orçamentos, pedidos e notas.";
    }

    @Override
    public String getDescricaoRelatorio() {

        return "Acompanhe aqui os serviços de sua empresa "
                + "filtre também esses serviços por categoria, operação ou descrição";
    }
    
    //Método que retorna os dados que serão exibidos no impresso 
    @Override
    public Map<String, Object> dadosImpressao(){
    
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ID: ", getServico().getId());
        map.put("Categoria: ", getServico().getCategoria());
        map.put("Operações: ", getServico().getOperacoes());
        map.put("Valor Custo: ", "R$ " + getServico().getValorCusto());
        map.put("Margem Lucro: ", "% " + getServico().getMargemLucro());
        map.put("Valor Venda: ", "R$ " + getServico().getValorVenda());
        map.put("Valor Desconto: ", "R$ " + getServico().getValorDesconto());
        map.put("Valor Final: ", "R$ " + getServico().getValorFinal());
        return map;
    }
}
