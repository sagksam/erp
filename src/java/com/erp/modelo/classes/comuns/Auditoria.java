/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.classes.comuns;

import com.erp.modelo.cadastros.Usuario;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author bianca
 */
@Entity
@Table(name="auditoria")
public class Auditoria extends EntidadeBase{
    
    @OneToOne
    private Usuario criacaoUsuario;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date criacaoData;
    @OneToOne
    private Usuario alteracaoUsuario;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date alteracaoData;
    
    public Auditoria(){}
    
    public Auditoria(Usuario criacaoUsuario, Date criacaoData, Usuario alteracaoUsuario, Date alteracaoData) {
        this.criacaoUsuario = criacaoUsuario;
        this.criacaoData = criacaoData;
        this.alteracaoUsuario = alteracaoUsuario;
        this.alteracaoData = alteracaoData;
    }

    public Usuario getCriacaoUsuario() {
        return criacaoUsuario;
    }

    public void setCriacaoUsuario(Usuario criacaoUsuario) {
        this.criacaoUsuario = criacaoUsuario;
    }

    public Date getCriacaoData() {
        return criacaoData;
    }

    public void setCriacaoData(Date criacaoData) {
        this.criacaoData = criacaoData;
    }

    public Usuario getAlteracaoUsuario() {
        return alteracaoUsuario;
    }

    public void setAlteracaoUsuario(Usuario alteracaoUsuario) {
        this.alteracaoUsuario = alteracaoUsuario;
    }

    public Date getAlteracaoData() {
        return alteracaoData;
    }

    public void setAlteracaoData(Date alteracaoData) {
        this.alteracaoData = alteracaoData;
    }
       
    
}
