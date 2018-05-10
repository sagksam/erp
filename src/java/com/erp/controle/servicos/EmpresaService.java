/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.servicos;

import com.erp.modelo.cadastros.Empresa;
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
@ManagedBean(name="empresaService", eager=true)
@ApplicationScoped
public class EmpresaService implements Serializable{
    
    private List<Empresa> empresas; 
    private List<Empresa> empresasTransportadora;
    private List<Empresa> empresasCompra;
    private List<Empresa> empresasVenda;
            
    @PostConstruct
    public void init(){
        
        EntityManager manager = JPAUtil.getEntityManager();
        try {
            Query query = manager.createNamedQuery("empresas")
                    .setParameter("ativo", true);
            this.empresas = query.getResultList();

            query = manager.createNamedQuery("empresasTransportadora")
                    .setParameter("ativo", true)
                    .setParameter("tipo", "Transportador");
            this.empresasTransportadora = query.getResultList();

            query = manager.createNamedQuery("empresasVenda")
                    .setParameter("ativo", true)
                    .setParameter("tipo", "Cliente");
            this.empresasVenda = query.getResultList();

            query = manager.createNamedQuery("empresasCompra")
                    .setParameter("ativo", true)
                    .setParameter("tipo", "Fornecedor");
            this.empresasCompra = query.getResultList();

        } catch (Exception e) {

            new Log().salvaErroLog(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
        } finally {
            manager.close();
        }
    }
    
    
    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public List<Empresa> getEmpresasTransportadora() {
        return empresasTransportadora;
    }

    public List<Empresa> getEmpresasCompra() {
        return empresasCompra;
    }

    public List<Empresa> getEmpresasVenda() {
        return empresasVenda;
    }
    
    
    
}
