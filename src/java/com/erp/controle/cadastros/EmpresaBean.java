/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.cadastros;

import com.erp.acesso.dados.GenericDAO;
import com.erp.controle.classes.comuns.BaseBean;
import com.erp.controle.servicos.EmpresaService;
import com.erp.modelo.cadastros.Empresa;
import com.erp.modelo.cadastros.Endereco;
import com.erp.modelo.cadastros.Telefone;
import com.erp.util.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NamedQuery;

/**
 *
 * @author bianca
 */
@ManagedBean
@ViewScoped
public class EmpresaBean extends BaseBean implements Serializable {

    private Empresa empresa = new Empresa();
    private List<Empresa> empresas;
    private List<Empresa> empresasTransportadora;
    private List<Empresa> empresasCompra;
    private List<Empresa> empresasVenda;
    private Telefone telefone;
    private Endereco endereco;

    @ManagedProperty("#{empresaService}")
    private EmpresaService service;
    
    //Método que inicializa os campos da classe
    @PostConstruct
    public void init() {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("objetoId")){
            long id = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("objetoId").toString());
            empresa = new GenericDAO().findById((int) id, Empresa.class);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("objetoId");
        }
        else{
            empresa = new Empresa();
        }  
        empresas = getService().getEmpresas();
        empresasTransportadora = getService().getEmpresasTransportadora();
        empresasVenda = getService().getEmpresasVenda();
        empresasCompra = getService().getEmpresasCompra();        
        telefone = new Telefone();
        endereco = new Endereco();
    }
    
    //Getters e Setters
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Empresa> getEmpresas() {

        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public List<Empresa> getEmpresasTransportadora() {
        return empresasTransportadora;
    }

    public void setEmpresasTransportadora(List<Empresa> empresasTransportadora) {
        this.empresasTransportadora = empresasTransportadora;
    }

    public List<Empresa> getEmpresasCompra() {
        return empresasCompra;
    }

    public void setEmpresasCompra(List<Empresa> empresasCompra) {
        this.empresasCompra = empresasCompra;
    }

    public List<Empresa> getEmpresasVenda() {
        return empresasVenda;
    }

    public void setEmpresasVenda(List<Empresa> empresasVenda) {
        this.empresasVenda = empresasVenda;
    }
    
    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public EmpresaService getService() {
        return service;
    }

    public void setService(EmpresaService service) {
        this.service = service;
    }

    //Método que adiciona um telefone a Empresa
    public void addTelefone() {

        if (getEmpresa().getTelefones() == null) {
            getEmpresa().setTelefones(new ArrayList<Telefone>());
        }
        if (!isModoEdicao()) {
            getEmpresa().getTelefones().add(getTelefone());
            getTelefone().setEmpresa(getEmpresa());
        }
        telefone = new Telefone();
    }

    //Método que remove um telefone da Empresa
    public void removeTelefone(Telefone telefone) {
       
        if (telefone.getId() > 0) {
            getEmpresa().getTelefones().remove(telefone);
            try {
                new GenericDAO().delete(telefone);
            } 
            catch (Exception e) {
            
                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR ,"Mensagem: ", "Um erro ocorreu, entre em conto com o adminstrador"));
            }
        } 
        else {
            getEmpresa().getTelefones().remove(telefone);
        }
    }

    //Método que adiciona um endereco a Empresa
    public void addEndereco() {

        if (getEmpresa().getEnderecos() == null) {
            getEmpresa().setEnderecos(new ArrayList<Endereco>());
        }
        if (!isModoEdicao()) {
            getEmpresa().getEnderecos().add(getEndereco());
            getEndereco().setEmpresa(getEmpresa());
        }
        endereco = new Endereco();
    }

    //Método que remove um endereco da Empresa
    public void removeEndereco(Endereco endereco) {
       
        if (endereco.getId() > 0) {
            getEmpresa().getEnderecos().remove(endereco);
            try {
                new GenericDAO().delete(endereco);
            } 
            catch (Exception e) {
                
                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR ,"Mensagem: ", "Um erro ocorreu, entre em contato com o administrador"));
            }
        } 
        else {
            getEmpresa().getEnderecos().remove(endereco);
        }
    }

    @Override
    public String novo() {

        setEmpresa(new Empresa());
        getEmpresa().setTelefones(new ArrayList<Telefone>());
        getEmpresa().setEnderecos(new ArrayList<Endereco>());
        return getCadastro();
    }

    @Override
    public String toString() {

        return "empresa";
    }
       
    @Override
    public String getNomeCadastro(){
    
        return "Empresas";
    }
    
    @Override
    public String getDescricaoCadastro(){
    
        return "Cadastre e gerencie aqui as empresas que possuem alguma "
                + "relação comercial com a sua sejam eles seus clientes, fornecedores, parceiros ou "
                + "transportadores.";
    }
    
    @Override
    public String getDescricaoRelatorio(){
    
        return "Acompanhe aqui as empresas que possuem alguma relação comercial com a sua "
                + "filtre também essas empresas por tipo, cidade ou estado";
    }
    
    //Método que retorna os dados que serão exibidos no impresso 
    @Override
    public Map<String, Object> dadosImpressao(){
    
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ID: ", getEmpresa().getId());
        map.put("CNPJ: ", getEmpresa().getCnpj());
        map.put("Tipo: ", getEmpresa().getTipo());
        map.put("Operações: ", getEmpresa().getOperacoes());
        map.put("Razão Social: ", getEmpresa().getRazaoSocial());
        map.put("CEP: ", getEmpresa().getCep());
        map.put("Endereço: ", getEmpresa().getEndereco());
        map.put("Número: ", getEmpresa().getNumero());
        map.put("Bairro: ", getEmpresa().getBairro());
        map.put("Email: ", getEmpresa().getEmail());        
        return map;
    }

}
