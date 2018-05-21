/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.classes.comuns;

import com.erp.modelo.cadastros.Usuario;
import com.erp.util.JPAUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Paulo
 */
@ManagedBean
@SessionScoped
public class LoginBean extends BaseBean implements Serializable{
    
    private List<Usuario> usuarios;
    private String nomeLogin;
    private String senhaLogin;
    
    @PostConstruct
    public void init(){
    
        usuarios = new ArrayList<>();
        nomeLogin = new String();
        senhaLogin = new String();
    }

    public List<Usuario> getUsuarios() {
       
        EntityManager manager = null;
        try{
          manager = JPAUtil.getEntityManager();
          Query query = manager.createQuery("SELECT u FROM Usuario u WHERE u.ativo = :ativo", Usuario.class);
          query.setParameter("ativo", true);
          this.usuarios = query.getResultList();
          return this.usuarios;
        }
        catch(Exception e){
            return new ArrayList<>();
        }
        finally{
            manager.close();
        }

    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getNomeLogin() {
        return nomeLogin;
    }

    public void setNomeLogin(String nomeLogin) {
        this.nomeLogin = nomeLogin;
    }

    public String getSenhaLogin() {
        return senhaLogin;
    }

    public void setSenhaLogin(String senhaLogin) {
        this.senhaLogin = senhaLogin;
    }
 
    public String autenticaUsuario(){
    
        FacesContext facesContext = FacesContext.getCurrentInstance();
        for (Usuario u : getUsuarios()) {
            if (u.getNome().equals(getNomeLogin()) && u.getSenha().equals(getSenhaLogin())) {
                facesContext.getExternalContext().getSessionMap().put("usuario", u.getId());
                return "/principal/cadastros?faces-redirect=true";
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário ou senha incorretos."));
        return "";
    }
    
    //Metodo que faz o logout do usuario
    public String logout(){
    
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect-true";
    }
}
