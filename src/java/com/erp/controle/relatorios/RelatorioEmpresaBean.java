/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.relatorios;

import com.erp.controle.classes.comuns.BaseBean;
import com.erp.modelo.cadastros.Empresa;
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
public class RelatorioEmpresaBean extends BaseBean implements Serializable{
    
    private String tipo;
    private String cidade;
    private String estado;
    private List<Empresa> empresas;
    
    @PostConstruct
    public void init(){
    
        empresas = new ArrayList<>();
    }
        
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
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
    
    //Método que configura os filtros do relatório de empresas
    @Override
    public void configurarFiltros(){
    
        EntityManager manager = JPAUtil.getEntityManager();
        try{
            String clausula = "SELECT e FROM Empresa e WHERE e.ativo = :ativo ";  
            if(!getTipo().equals(""))
                clausula += "AND e.tipo = :tipo ";
            if(!getCidade().equals(""))
                clausula += "AND e.cidade = :cidade ";
            if(!getEstado().equals(""))
                clausula += "AND e.estado = :estado ";            
            Query query = manager.createQuery(clausula, Empresa.class);
            query.setParameter("ativo", true);
            if(!getTipo().equals(""))
              query.setParameter("tipo", getTipo());
            if(!getCidade().equals(""))
              query.setParameter("cidade", getCidade());
            if(!getEstado().equals(""))
              query.setParameter("estado", getEstado());            
            this.empresas = query.getResultList();
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
