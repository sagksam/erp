/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.relatorios;

import com.erp.modelo.cadastros.Servico;
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
public class RelatorioServicoBean implements Serializable{
    
    private String categoria;
    private String operacoes;
    private String descricao;
    private List<Servico> servicos;
    
    @PostConstruct
    public void init(){
    
        servicos = new ArrayList<>();
    }
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getOperacoes() {
        return operacoes;
    }

    public void setOperacoes(String operacoes) {
        this.operacoes = operacoes;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }
    
    //Método que configura os filtros do relatório de servicos
    public void configurarFiltros(){
    
        EntityManager manager = JPAUtil.getEntityManager();
        try{
            String clausula = "SELECT s FROM Servico s WHERE s.ativo = :ativo ";  
            if(!getCategoria().equals(""))
                clausula += "AND s.categoria = :categoria ";
            if(!getOperacoes().equals(""))
                clausula += "AND s.operacoes = :operacoes ";
            if(!getDescricao().equals(""))
                clausula += "AND s.descricao = :descricao ";            
            Query query = manager.createQuery(clausula, Servico.class);
            query.setParameter("ativo", true);
            if(!getCategoria().equals(""))
              query.setParameter("categoria", getCategoria());
            if(!getOperacoes().equals(""))
              query.setParameter("operacoes", getOperacoes());
            if(!getDescricao().equals(""))
              query.setParameter("descricao", getDescricao()); 
            this.servicos = query.getResultList();
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
