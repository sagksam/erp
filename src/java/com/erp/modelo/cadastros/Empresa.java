/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.cadastros;

import com.erp.modelo.classes.comuns.EntidadeBase;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author PauloHenrique
 */
@Entity
@Table(name="empresa")
@NamedQueries({
  @NamedQuery(name="empresas", query="SELECT e FROM Empresa e WHERE e.ativo = :ativo"),    
  @NamedQuery(name="empresasTransportadora", query="SELECT e FROM Empresa e WHERE e.ativo = :ativo AND e.tipo = :tipo"),
  @NamedQuery(name="empresasVenda", query="SELECT e FROM Empresa e WHERE e.ativo = :ativo AND e.tipo = :tipo"),
  @NamedQuery(name="empresasCompra", query="SELECT e FROM Empresa e WHERE e.ativo = :ativo AND e.tipo = :tipo"),
})
public class Empresa extends EntidadeBase{
    
    @Column(nullable=false, unique=true, length=30)
    private String cnpj;
    @Column(length=10)
    private String tipo;
    private String operacoes;
    @Column(length=250)
    private String razaoSocial;
    @Column(length=250)
    private String nomeFantasia;
    @Column(length=30)
    private String inscricaoEstadual;
    @Column(length=30)
    private String inscricaoMuncipal;
    private boolean transporte;
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
    @Column(length=30)
    private String telefone;
    @Column(length=30)
    private String celular;
    @Column(length=30)
    private String site;
    @Column(length=30)
    private String email;
    @OneToMany(mappedBy="empresa", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Endereco> enderecos;
    @OneToMany(mappedBy="empresa", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Telefone> telefones;
    @Column(length=30)
    private String vendedor;
    @Column(columnDefinition="TEXT")
    private String detalhes;

    public Empresa(){}

    public Empresa(String cnpj, String tipo, String operacoes, String razaoSocial, String nomeFantasia, String inscricaoEstadual, String inscricaoMuncipal, boolean transporte, String cep, String endereco, int numero, String complemento, String bairro, String cidade, String ibge, String siafi, String estado, String pais, String telefone, String celular, String site, String email, List<Endereco> enderecos, List<Telefone> telefones, String vendedor, String detalhes) {
        this.cnpj = cnpj;
        this.tipo = tipo;
        this.operacoes = operacoes;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.inscricaoEstadual = inscricaoEstadual;
        this.inscricaoMuncipal = inscricaoMuncipal;
        this.transporte = transporte;
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
        this.telefone = telefone;
        this.celular = celular;
        this.site = site;
        this.email = email;
        this.enderecos = enderecos;
        this.telefones = telefones;
        this.vendedor = vendedor;
        this.detalhes = detalhes;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOperacoes() {
        return operacoes;
    }

    public void setOperacoes(String operacoes) {
        this.operacoes = operacoes;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getInscricaoMuncipal() {
        return inscricaoMuncipal;
    }

    public void setInscricaoMuncipal(String inscricaoMuncipal) {
        this.inscricaoMuncipal = inscricaoMuncipal;
    }

    public boolean isTransporte() {
        return transporte;
    }

    public void setTransporte(boolean transporte) {
        this.transporte = transporte;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
    
    @Override
    public String toString() {
        return "--- Empresa[ id=" + super.getId() + " ] --- ";
    }
}