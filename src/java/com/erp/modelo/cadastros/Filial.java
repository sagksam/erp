/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.cadastros;

import com.erp.modelo.classes.comuns.EntidadeBase;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author PauloHenrique
 */
@Entity
@Table(name="filial")
@NamedQuery(name="filiais", query="SELECT e FROM Filial e WHERE e.ativo = :ativo")
public class Filial extends EntidadeBase {
    
    @Column(nullable=false, unique=true, length=30)
    private String cnpj;
    @Column(length=250)
    private String nome;
    @Column(length=250)
    private String razaoSocial;
    @Column(length=250)
    private String nomeFantasia;
    @Column(length=30)
    private String inscricaoEstadual;
    @Column(length=30)
    private String inscricaoMunicipal;
    @Column(length=30)
    private String CNAE;
    @Column(length=30)
    private String numeroSerieCertificado;
    @Column(length=30)
    private String status;
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
    private boolean simples;
    private double creditoICMS;
    private double aliquotaISSQN;
    private double aliquotaPIS;
    private double aliquotaCOFINS;
    private double aliquotaCSLL;
    private double aliquotaIR;
    private double aliquotaINSS;
    @Lob
    private byte[] logo;
    
    public Filial(){}

    public Filial(String cnpj, String nome, String razaoSocial, String nomeFantasia, String inscricaoEstadual, String inscricaoMunicipal, String CNAE, String numeroSerieCertificado, String status, String cep, String endereco, int numero, String complemento, String bairro, String cidade, String ibge, String siafi, String estado, String pais, String telefone, String celular, String site, String email, boolean simples, double creditoICMS, double aliquotaISSQN, double aliquotaPIS, double aliquotaCOFINS, double aliquotaCSLL, double aliquotaIR, double aliquotaINSS, byte[] logo) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.inscricaoEstadual = inscricaoEstadual;
        this.inscricaoMunicipal = inscricaoMunicipal;
        this.CNAE = CNAE;
        this.numeroSerieCertificado = numeroSerieCertificado;
        this.status = status;
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
        this.simples = simples;
        this.creditoICMS = creditoICMS;
        this.aliquotaISSQN = aliquotaISSQN;
        this.aliquotaPIS = aliquotaPIS;
        this.aliquotaCOFINS = aliquotaCOFINS;
        this.aliquotaCSLL = aliquotaCSLL;
        this.aliquotaIR = aliquotaIR;
        this.aliquotaINSS = aliquotaINSS;
        this.logo = logo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public String getCNAE() {
        return CNAE;
    }

    public void setCNAE(String CNAE) {
        this.CNAE = CNAE;
    }

    public String getNumeroSerieCertificado() {
        return numeroSerieCertificado;
    }

    public void setNumeroSerieCertificado(String numeroSerieCertificado) {
        this.numeroSerieCertificado = numeroSerieCertificado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public boolean isSimples() {
        return simples;
    }

    public void setSimples(boolean simples) {
        this.simples = simples;
    }

    public double getCreditoICMS() {
        return creditoICMS;
    }

    public void setCreditoICMS(double creditoICMS) {
        this.creditoICMS = creditoICMS;
    }

    public double getAliquotaISSQN() {
        return aliquotaISSQN;
    }

    public void setAliquotaISSQN(double aliquotaISSQN) {
        this.aliquotaISSQN = aliquotaISSQN;
    }

    public double getAliquotaPIS() {
        return aliquotaPIS;
    }

    public void setAliquotaPIS(double aliquotaPIS) {
        this.aliquotaPIS = aliquotaPIS;
    }

    public double getAliquotaCOFINS() {
        return aliquotaCOFINS;
    }

    public void setAliquotaCOFINS(double aliquotaCOFINS) {
        this.aliquotaCOFINS = aliquotaCOFINS;
    }

    public double getAliquotaCSLL() {
        return aliquotaCSLL;
    }

    public void setAliquotaCSLL(double aliquotaCSLL) {
        this.aliquotaCSLL = aliquotaCSLL;
    }

    public double getAliquotaIR() {
        return aliquotaIR;
    }

    public void setAliquotaIR(double aliquotaIR) {
        this.aliquotaIR = aliquotaIR;
    }

    public double getAliquotaINSS() {
        return aliquotaINSS;
    }

    public void setAliquotaINSS(double aliquotaINSS) {
        this.aliquotaINSS = aliquotaINSS;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }    
    
    @Override
    public String toString() {
        return "--- Filial[ id=" + super.getId() + " ] --- ";
    }
    
}
