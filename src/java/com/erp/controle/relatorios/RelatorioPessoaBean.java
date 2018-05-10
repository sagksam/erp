/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.relatorios;

import com.erp.modelo.cadastros.Pessoa;
import com.erp.util.JPAUtil;
import com.erp.util.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author bianca
 */
@ManagedBean
@ViewScoped
public class RelatorioPessoaBean implements Serializable{
    
    private String tipo;
    private String cidade;
    private String estado;
    private List<Pessoa> pessoas;
    
    @PostConstruct
    public void init(){
    
        pessoas = new ArrayList<>();
    }
        
    //Getters e setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }
    
    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }
    //Método que configura os filtros do relatório de pessoas
    public void configurarFiltros(){
    
        EntityManager manager = JPAUtil.getEntityManager();
        try{
            String clausula = "SELECT p FROM Pessoa p WHERE p.ativo = :ativo ";  
            if(!getTipo().equals(""))
                clausula += "AND p.tipo = :tipo ";
            if(!getCidade().equals(""))
                clausula += "AND p.cidade = :cidade ";
            if(!getEstado().equals(""))
                clausula += "AND p.estado = :estado ";            
            Query query = manager.createQuery(clausula, Pessoa.class);
            query.setParameter("ativo", true);
            if(!getTipo().equals(""))
              query.setParameter("tipo", getTipo());
            if(!getCidade().equals(""))
              query.setParameter("cidade", getCidade());
            if(!getEstado().equals(""))
              query.setParameter("estado", getEstado());   
            this.pessoas = query.getResultList();
        }
        catch(Exception e){
        
            new Log().salvaErroLog(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
        }
        finally{
        
            manager.close();
        }
    }


}
