/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.servicos;

import com.erp.modelo.cadastros.Filial;
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
@ManagedBean(name = "filialService", eager = true)
@ApplicationScoped
public class FilialService implements Serializable {

    private List<Filial> filiais;

    @PostConstruct
    public void init() {

        EntityManager manager = JPAUtil.getEntityManager();
        try {
            Query query = manager.createNamedQuery("filiais")
                    .setParameter("ativo", true);
            this.filiais = query.getResultList();
        } catch (Exception e) {

            new Log().salvaErroLog(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
        } finally {
            manager.close();
        }
    }

    public List<Filial> getFiliais() {
        return filiais;
    }

}
