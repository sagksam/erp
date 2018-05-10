/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.cadastros;

import com.erp.modelo.classes.comuns.EntidadeBase;
import javax.persistence.CascadeType;
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
@Table(name="endereco")
public class Endereco extends EntidadeBase{
    
    @ManyToOne()
    @JoinColumn(name="pessoaId")
    private Pessoa pessoa;
    @ManyToOne()
    @JoinColumn(name="empresaId")    
    private Empresa empresa;
    @Column(length=30)
    private String tipo;
    @Column(length=30)
    private String cep;
    @Column(length=250)
    private String endereco;
    private int numero;
    @Column(length=250)
    private String complemento;
    @Column(length=30)
    private String bairro;
    @Column(length=30)
    private String cidade;
    @Column(length=10)
    private String ibge;
    @Column(length=10)
    private String siafi;
    @Column(length=10)
    private String estado;
    @Column(length=30)
    private String pais;

    public Endereco() {
    }

    public Endereco(Pessoa pessoa, Empresa empresa, String tipo, String cep, String endereco, int numero, String complemento, String bairro, String cidade, String ibge, String siafi, String estado, String pais) {
        this.pessoa = pessoa;
        this.empresa = empresa;
        this.tipo = tipo;
        this.cep = cep;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.ibge = ibge;
        this.siafi = siafi;
        this.estado = estado;
        this.pais = pais;
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getSiafi() {
        return siafi;
    }

    public void setSiafi(String siafi) {
        this.siafi = siafi;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    @Override
    public String toString() {
        return "--- Endereco[ id=" + super.getId() + " ] --- ";
    }
}
