/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.cadastros;

import com.erp.modelo.classes.comuns.EntidadeBase;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

/**
 *
 * @author bianca
 */
@Entity
@Table(name="usuario")
public class Usuario extends EntidadeBase{
    
    private String nome;
    private String email;
    private String senha;
    private boolean adminstrador;
    private boolean logado;
    
    public Usuario(){
    
        
    }

    public Usuario(String nome, String email, String senha, boolean adminstrador, boolean logado) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.adminstrador = adminstrador;
        this.logado = logado;
    }
    
     
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAdminstrador() {
        return adminstrador;
    }

    public void setAdminstrador(boolean adminstrador) {
        this.adminstrador = adminstrador;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }
    
    
}
