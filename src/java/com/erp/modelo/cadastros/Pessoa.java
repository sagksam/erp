/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.cadastros;

import com.erp.modelo.classes.comuns.EntidadeBase;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;


/**
 *
 * @author PauloHenrique
 */
@Entity
@Table(name="pessoa")
@NamedQueries({

    @NamedQuery(name="pessoas", query="SELECT p FROM Pessoa p WHERE p.ativo = :ativo"),
    @NamedQuery(name="pessoasTransportadora", query="SELECT p FROM Pessoa p WHERE p.ativo = :ativo AND p.tipo = :tipo"),
    @NamedQuery(name="pessoasCompra", query="SELECT p FROM Pessoa p WHERE p.ativo = :ativo AND p.tipo = :tipo"),
    @NamedQuery(name="pessoasVenda", query="SELECT p FROM Pessoa p WHERE p.ativo = :ativo AND p.tipo = :tipo"),
})
public class Pessoa extends EntidadeBase implements Serializable{
    
    @Column(length=30, unique=true)  
    private String cpf;
    @Column(length=30)
    private String tipo;
    @Column(length=30)
    private String operacoes;
    @Column(length=250)
    private String nomeCompleto;
    @Column(length=30)
    private String departamento;
    @Column(length=30)
    private String profissao;
    @Column(length=30)
    private String escolaridade;
    @Column(length=10)
    private String sexo;
    @Column(length=30)
    private String estadoCivil;
    @Temporal(DATE)
    private Date nascimento;
    @Column(length=30)
    private String rg;
    @Temporal(DATE)
    private Date admissao;
    @Temporal(DATE)
    private Date demissao;
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
    @Column(length=10)
    private String pais;
    @Column(length=30)
    private String telefone;
    @Column(length=30)
    private String celular;
    @Column(length=30)
    private String site;
    @Column(length=30)
    private String email;
    @OneToMany(mappedBy="pessoa", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Endereco> enderecos;
    @OneToMany(mappedBy="pessoa", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Telefone> telefones;
    @Column(length=30)
    private String vendedor;
    @Column(columnDefinition="TEXT")
    private String detalhes;

    public Pessoa(){}

    public Pessoa(String cpf, String tipo, String operacoes, String nomeCompleto, String departamento, String profissao, String escolaridade, String sexo, String estadoCivil, Date nascimento, String rg, Date admissao, Date demissao, boolean transporte, String cep, String endereco, int numero, String complemento, String bairro, String cidade, String ibge, String siafi, String estado, String pais, String telefone, String celular, String site, String email, List<Endereco> enderecos, List<Telefone> telefones, String vendedor, String detalhes) {
        this.cpf = cpf;
        this.tipo = tipo;
        this.operacoes = operacoes;
        this.nomeCompleto = nomeCompleto;
        this.departamento = departamento;
        this.profissao = profissao;
        this.escolaridade = escolaridade;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.nascimento = nascimento;
        this.rg = rg;
        this.admissao = admissao;
        this.demissao = demissao;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getAdmissao() {
        return admissao;
    }

    public void setAdmissao(Date admissao) {
        this.admissao = admissao;
    }

    public Date getDemissao() {
        return demissao;
    }

    public void setDemissao(Date demissao) {
        this.demissao = demissao;
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
        return "--- Pessoa[ id=" + super.getId() + " ] --- ";
    }
}
