/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.servicos;

import com.erp.modelo.cadastros.Pessoa;
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
@ManagedBean(name="pessoaService", eager=true)
@ApplicationScoped
public class PessoaService implements Serializable{
    
    private List<Pessoa> pessoas;  
    private List<Pessoa> pessoasTransportadora;
    private List<Pessoa> pessoasVenda;
    private List<Pessoa> pessoasCompra;

    
    @PostConstruct
    public void init(){

        EntityManager manager = JPAUtil.getEntityManager();
        try {
            Query query = manager.createNamedQuery("pessoas")
                    .setParameter("ativo", true);
            this.pessoas = query.getResultList();

            query = manager.createNamedQuery("pessoasTransportadora")
                    .setParameter("ativo", true)
                    .setParameter("tipo", "Transportador");
            this.pessoasTransportadora = query.getResultList();

            query = manager.createNamedQuery("pessoasVenda")
                    .setParameter("ativo", true)
                    .setParameter("tipo", "Cliente");
            this.pessoasVenda = query.getResultList();

            query = manager.createNamedQuery("pessoasCompra")
                    .setParameter("ativo", true)
                    .setParameter("tipo", "Fornecedor");
            this.pessoasCompra = query.getResultList();

        } catch (Exception e) {

            new Log().salvaErroLog(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
        } finally {
            manager.close();
        }
    }
    
    
    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public List<Pessoa> getPessoasTransportadora() {
        return pessoasTransportadora;
    }

    public List<Pessoa> getPessoasVenda() {
        return pessoasVenda;
    }

    public List<Pessoa> getPessoasCompra() {
        return pessoasCompra;
    }
    
    
    
    
    
}
