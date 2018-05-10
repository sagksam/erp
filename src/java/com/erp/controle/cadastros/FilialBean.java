/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.cadastros;

import com.erp.acesso.dados.GenericDAO;
import com.erp.controle.classes.comuns.BaseBean;
import com.erp.controle.servicos.FilialService;
import com.erp.modelo.cadastros.Filial;
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
 * @author PauloHenrique
 */
@ManagedBean(name="filialBean")
@ViewScoped
public class FilialBean extends BaseBean implements Serializable {

    private Filial filial;
    private List<Filial> filiais;
    
    //INJECTS 
    @ManagedProperty("#{filialService}")
    private FilialService service;
    
    //Método que inicializa os campos da classe
    @PostConstruct
    public void init() {

        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("objetoId")){
            long id = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("objetoId").toString());
            filial = new GenericDAO().findById((int) id, Filial.class);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("objetoId");
        }
        else{
            filial = new Filial();
        }
        filiais = getService().getFiliais();
    }

    //Getters e Setters
    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public List<Filial> getFiliais() {

        return this.filiais;
    }

    public void setFiliais(List<Filial> filiais) {
        this.filiais = filiais;
    }

    public FilialService getService() {
        return service;
    }

    public void setService(FilialService service) {
        this.service = service;
    }

    @Override
    public String novo() {

        filial = new Filial();
        return getCadastro();
    }

    @Override
    public String toString() {

        return "filial";
    }

    @Override
    public String getNomeCadastro() {

        return "Filiais";
    }

    @Override
    public String getDescricaoCadastro() {

        return "Cadastre e gerencie aqui as filiais de sua empresa, cadastre desde dados simples "
                + "até as alíquotas de tributos federais e municipais.";
    }
    
    //Método que retorna os dados que serão exibidos no impresso 
    @Override
    public Map<String, Object> dadosImpressao(){
    
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ID: ", getFilial().getId());
        map.put("CNPJ: ", getFilial().getCnpj());
        map.put("Razão Social: ", getFilial().getRazaoSocial());
        map.put("CEP: ", getFilial().getCep());
        map.put("Endereço: ", getFilial().getEndereco());
        map.put("Número: ", getFilial().getNumero());
        map.put("Bairro: ", getFilial().getBairro());
        map.put("Email: ", getFilial().getEmail());        
        return map;
    }
}
