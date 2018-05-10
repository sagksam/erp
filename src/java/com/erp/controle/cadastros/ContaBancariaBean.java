/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.cadastros;

import com.erp.acesso.dados.GenericDAO;
import com.erp.controle.classes.comuns.BaseBean;
import com.erp.controle.servicos.ContaBancariaService;
import com.erp.modelo.cadastros.ContaBancaria;;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author PauloHenrique
 */
@ManagedBean
@SessionScoped
public class ContaBancariaBean extends BaseBean implements Serializable {

    private ContaBancaria contaBancaria;
    private List<ContaBancaria> contasBancarias;

    @ManagedProperty("#{contaBancariaService}")
    private ContaBancariaService service;

    //Método que inicializa os campos da classe
    @PostConstruct
    public void init() {

        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("objetoId")) {
            long id = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("objetoId").toString());
            contaBancaria = new GenericDAO().findById((int) id, ContaBancaria.class);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("objetoId");
        } 
        else {
            contaBancaria = new ContaBancaria();
        }
        contasBancarias = getService().getContasBancarias();
    }

    //Getters e Setters
    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public List<ContaBancaria> getContasBancarias() {
        return contasBancarias;
    }

    public void setContasBancarias(List<ContaBancaria> contasBancarias) {
        this.contasBancarias = contasBancarias;
    }

    public ContaBancariaService getService() {
        return service;
    }

    public void setService(ContaBancariaService service) {
        this.service = service;
    }
    
    @Override
    public String novo() {

        setContaBancaria(new ContaBancaria());
        return getCadastro();
    }

    @Override
    public String toString() {

        return "contabancaria";
    }

    @Override
    public String getNomeCadastro() {

        return "Contas Bancárias";
    }

    @Override
    public String getDescricaoCadastro() {

        return "Cadastre as contas bancárias de sua empresa, seja elas"
                + "\ncontas poupança ou contas corrente.";
    }
    
    //Método que retorna os dados que serão exibidos no impresso 
    @Override
    public Map<String, Object> dadosImpressao(){
    
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ID: ", getContaBancaria().getId());
        map.put("Descrição: ", getContaBancaria().getDescricao());
        map.put("Banco: ", getContaBancaria().getBanco());
        map.put("Agência: ", getContaBancaria().getAgencia());
        map.put("Digíto: ", getContaBancaria().getDigito());
        map.put("Tipo Conta: ", getContaBancaria().getTipoConta());
        map.put("Status: ", getContaBancaria().getStatus());
        map.put("Filial: ", getContaBancaria().getFilial());
        return map;
    }
}
