/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.relatorios;

import com.erp.modelo.cadastros.NotaServico;
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
 * @author Paulo
 */
@ManagedBean
@ViewScoped
public class RelatorioNotaServicoBean implements Serializable{
    
    private Date dataInicio;
    private Date dataFim;
    private String status;
    private String descricao;
    private List<NotaServico> notasServicos;
    
    @PostConstruct
    public void init(){
    
        notasServicos = new ArrayList<>();
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

    public List<NotaServico> getNotasServicos() {
        return notasServicos;
    }

    public void setNotasServicos(List<NotaServico> notasServicos) {
        this.notasServicos = notasServicos;
    }
    
    //Método que configura os filtros do relatório de notas de servicos
    public void configurarFiltros(){
    
        EntityManager manager = JPAUtil.getEntityManager();
        try{
            String clausula = "SELECT n FROM NotaServico n WHERE n.ativo = :ativo ";  
            if(getDataInicio() != null)
                clausula += "AND n.dataEmissao >= :dataInicio "; 
            if(getDataFim() != null)
                clausula += "AND n.dataEmissao <= :dataFim ";             
            if(!getStatus().equals(""))
                clausula += "AND n.status = :status ";
            if(!getDescricao().equals(""))
                clausula += "AND n.descricao = :descricao ";  
            Query query = manager.createQuery(clausula, NotaServico.class);
            query.setParameter("ativo", true);
            if(getDataInicio() != null)
              query.setParameter("dataInicio", getDataInicio());
            if(getDataFim() != null)
              query.setParameter("dataFim", getDataFim());           
            if(!getStatus().equals(""))
              query.setParameter("status", getStatus());
            if(!getDescricao().equals(""))
              query.setParameter("descricao", getDescricao());  
            this.notasServicos = query.getResultList();
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
