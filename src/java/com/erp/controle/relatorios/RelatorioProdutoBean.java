/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.relatorios;

import com.erp.modelo.cadastros.Produto;
import com.erp.util.JPAUtil;
import com.erp.util.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class RelatorioProdutoBean implements Serializable{
    
    private String categoria;
    private String operacoes;
    private String descricao;
    private List<Produto> produtos;
    
    public void init(){
    
        produtos = new ArrayList<>();
    }
    
    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the operacoes
     */
    public String getOperacoes() {
        return operacoes;
    }

    /**
     * @param operacoes the operacoes to set
     */
    public void setOperacoes(String operacoes) {
        this.operacoes = operacoes;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    //Método que configura os filtros do relatório de produto
    public void configurarFiltros(){
    
        EntityManager manager = JPAUtil.getEntityManager();
        try{
            String clausula = "SELECT p FROM Produto p WHERE p.ativo = :ativo ";  
            if(!getCategoria().equals(""))
                clausula += "AND p.categoria = :categoria ";
            if(!getOperacoes().equals(""))
                clausula += "AND p.operacoes = :operacoes ";
            if(!getDescricao().equals(""))
                clausula += "AND p.descricao = :descricao ";   
            System.out.println(clausula);
            Query query = manager.createQuery(clausula, Produto.class);
            query.setParameter("ativo", true);
            if(!getCategoria().equals(""))
              query.setParameter("categoria", getCategoria());
            if(!getOperacoes().equals(""))
              query.setParameter("operacoes", getOperacoes());
            if(!getDescricao().equals(""))
              query.setParameter("descricao", getDescricao()); 
            this.produtos = query.getResultList();
            System.out.println(this.produtos);
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
