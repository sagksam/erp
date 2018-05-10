/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.relatorios;

import com.erp.modelo.cadastros.Pedido;
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
public class RelatorioPedidoBean implements Serializable{
    
    private Date dataInicio;
    private Date dataFim;
    private String status;
    private String descricao;
    private List<Pedido> pedidosVenda;
    private List<Pedido> pedidosCompra;
    
    @PostConstruct
    public void init(){
        pedidosVenda = new ArrayList<>();
        pedidosCompra = new ArrayList<>();
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

    public List<Pedido> getPedidosVenda() {
        return pedidosVenda;
    }

    public void setPedidosVenda(List<Pedido> pedidosVenda) {
        this.pedidosVenda = pedidosVenda;
    }

    public List<Pedido> getPedidosCompra() {
        return pedidosCompra;
    }

    public void setPedidosCompra(List<Pedido> pedidosCompra) {
        this.pedidosCompra = pedidosCompra;
    }

    //Método que configura os filtros do relatório de pedidos
    public void configurarFiltros(String tipo){
    
        EntityManager manager = JPAUtil.getEntityManager();
        try{
            String clausula = "SELECT p FROM Pedido p WHERE p.ativo = :ativo AND p.tipo = :tipo ";  
            if(getDataInicio() != null)
                clausula += "AND p.dataEmissao >= :dataInicio "; 
            if(getDataFim() != null)
                clausula += "AND p.dataEmissao <= :dataFim ";             
            if(!getStatus().equals(""))
                clausula += "AND p.status = :status ";
            if(!getDescricao().equals(""))
                clausula += "AND p.descricao = :descricao ";  
            Query query = manager.createQuery(clausula, Pedido.class);
            query.setParameter("ativo", true);
            query.setParameter("tipo", tipo);
            if(getDataInicio() != null)
              query.setParameter("dataInicio", getDataInicio());
            if(getDataFim() != null)
              query.setParameter("dataFim", getDataFim());           
            if(!getStatus().equals(""))
              query.setParameter("status", getStatus());
            if(!getDescricao().equals(""))
              query.setParameter("descricao", getDescricao());  
            if(tipo.equals("Venda"))
                this.pedidosVenda = query.getResultList();
            else
                this.pedidosCompra = query.getResultList();
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
