/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.servicos;

import com.erp.modelo.cadastros.Servico;
import com.erp.util.JPAUtil;
import com.erp.util.Log;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author PauloHenrique
 */
@ManagedBean(name="servicoService", eager=true)
@ApplicationScoped
public class ServicoService implements Serializable{
    
    private List<Servico> servicos;  
    private List<Servico> servicosVenda;
    private List<Servico> servicosCompra;  


    
    @PostConstruct
    public void init(){

        EntityManager manager = JPAUtil.getEntityManager();
        try {
            Query query = manager.createNamedQuery("servicos")
                    .setParameter("ativo", true);
            this.servicos = query.getResultList();

            query = manager.createNamedQuery("servicosVenda")
                    .setParameter("ativo", true)
                    .setParameter("operacoes", "Venda");
            this.servicosVenda = query.getResultList();

            query = manager.createNamedQuery("servicosCompra")
                    .setParameter("ativo", true)
                    .setParameter("operacoes", "Compra");
            this.servicosCompra = query.getResultList();
        } catch (Exception e) {

            new Log().salvaErroLog(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
        } finally {

            manager.close();
        }
    }
    
    
    public List<Servico> getServicos() {
        return servicos;
    }

    public List<Servico> getServicosVenda() {
        return servicosVenda;
    }

    public List<Servico> getServicosCompra() {
        return servicosCompra;
    }
    
    
    
    
}
