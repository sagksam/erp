/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.relatorios;

import com.erp.controle.classes.comuns.BaseBean;
import com.erp.modelo.cadastros.Conta;
import com.erp.modelo.cadastros.Filial;
import com.erp.util.JPAUtil;
import com.erp.util.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class RelatorioContaBean extends BaseBean implements Serializable{
    
    private Date dataInicio;
    private Date dataFim;
    private String status;
    private Filial filial;
    private List<Conta> contasPagar;
    private List<Conta> contasReceber;
    
    public void init(){
    
        this.contasPagar = new ArrayList<>();
        this.contasReceber = new ArrayList<>();
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

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public List<Conta> getContasPagar() {
        return contasPagar;
    }

    public void setContasPagar(List<Conta> contasPagar) {
        this.contasPagar = contasPagar;
    }

    public List<Conta> getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(List<Conta> contasReceber) {
        this.contasReceber = contasReceber;
    }
    
    //Método que configura os filtros do relatório de pedidos
    public void configurarFiltros(String tipo){
    
        EntityManager manager = JPAUtil.getEntityManager();
        try{
            String clausula = "SELECT c FROM Conta c WHERE c.ativo = :ativo AND c.tipo = :tipo ";  
            if(getDataInicio() != null)
                clausula += "AND c.dataEmissao >= :dataInicio "; 
            if(getDataFim() != null)
                clausula += "AND c.dataEmissao <= :dataFim ";             
            if(!getStatus().equals(""))
                clausula += "AND c.status = :status ";
            if(getFilial() != null)
                clausula += "AND c.filial = :filial ";  
            Query query = manager.createQuery(clausula, Conta.class);
            query.setParameter("ativo", true);
            query.setParameter("tipo", tipo);
            if(getDataInicio() != null)
              query.setParameter("dataInicio", getDataInicio());
            if(getDataFim() != null)
              query.setParameter("dataFim", getDataFim());           
            if(!getStatus().equals(""))
              query.setParameter("status", getStatus());
            if(getFilial() != null)
              query.setParameter("filial", getFilial());  
            if(tipo.equals("Receber"))
                this.contasReceber = query.getResultList();
            else
                this.contasPagar = query.getResultList();
        }
        catch(Exception e){
        
            new Log().salvaErroLog(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
        }
        finally{
        
            manager.close();
        }

    }
    
    @Override
    public String getDescricaoRelatorio(){
    
        if(this.getNomeCadastro().equals("Contas a Receber"))
            return getContasReceber().size() + " registros";
        else
            return getContasPagar().size() + " registros";
    }

}
