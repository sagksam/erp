/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.relatorios;

import com.erp.modelo.cadastros.Empresa;
import com.erp.modelo.cadastros.Orcamento;
import com.erp.util.JPAUtil;
import com.erp.util.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class RelatorioOrcamentoBean implements Serializable{
    
    private Date dataInicio;
    private Date dataFim;
    private String status;
    private String descricao;
    private List<Orcamento> orcamentos;
    private Orcamento orcamento;
    
    @PostConstruct
    public void init(){
        orcamentos = new ArrayList<>();
        orcamento = new Orcamento();
    }
    
        public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Orcamento> getOrcamentos() {
        return orcamentos;
    }

    public void setOrcamentos(List<Orcamento> orcamentos) {
        this.orcamentos = orcamentos;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }
    
    //Método que configura os filtros do relatório de empresas
    public void configurarFiltros(){
    
        EntityManager manager = JPAUtil.getEntityManager();
        try{
            String clausula = "SELECT o FROM Orcamento o WHERE o.ativo = :ativo ";  
            if(getDataInicio() != null)
                clausula += "AND o.dataEmissao >= :dataInicio "; 
            if(getDataFim() != null)
                clausula += "AND o.dataEmissao <= :dataFim ";             
            if(!getStatus().equals(""))
                clausula += "AND o.status = :status ";
            if(!getDescricao().equals(""))
                clausula += "AND o.descricao = :descricao ";   
            Query query = manager.createQuery(clausula, Orcamento.class);
            query.setParameter("ativo", true);
            if(getDataInicio() != null)
              query.setParameter("dataInicio", getDataInicio());
            if(getDataFim() != null)
              query.setParameter("dataFim", getDataFim());           
            if(!getStatus().equals(""))
              query.setParameter("status", getStatus());
            if(!getDescricao().equals(""))
              query.setParameter("descricao", getDescricao());            
            this.orcamentos = query.getResultList();
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
