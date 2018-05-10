/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.cadastros;

import com.erp.modelo.classes.comuns.EntidadeBase;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author PauloHenrique
 */
@Entity
@Table(name="telefone")
public class Telefone extends EntidadeBase{
    
    @ManyToOne()
    @JoinColumn(name="pessoaId")
    private Pessoa pessoa;
    @ManyToOne()
    @JoinColumn(name="empresaId")    
    private Empresa empresa;
    @Column(length=30)
    private String tipo;
    @Column(length=30)
    private String telefone;

    public Telefone() {
    }

    public Telefone(Pessoa pessoa, Empresa empresa, String tipo, String telefone) {
        this.pessoa = pessoa;
        this.empresa = empresa;
        this.tipo = tipo;
        this.telefone = telefone;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    @Override
    public String toString() {
        return "--- Telefone[ id=" + super.getId() + " ] --- ";
    }
}
