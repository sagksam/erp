/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.cadastros;

import com.erp.controle.classes.comuns.BaseBean;
import com.erp.modelo.cadastros.Conta;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author PauloHenrique
 */
@ManagedBean(name = "contaBean")
@SessionScoped
public class ContaBean extends BaseBean {

    private Conta conta;
    private List<Conta> contas;

    //Método que inicia os campos da classe
    @PostConstruct
    public void init() {

        conta = new Conta();
        contas = new ArrayList<>();
    }

    //Getters e Setters
    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public List<Conta> getContas() {

        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    //Método para configurar a selecao de uma empresa no Orcamento
    public void selecionarEmpresa() {

        getConta().setPessoa(null);
    }

    //Método para configurar a selecao de uma pessoa no Orcamento
    public void selecionarPessoa() {

        getConta().setEmpresa(null);
    }

    @Override
    public String novo() {

        conta = new Conta();
        return getCadastro();
    }

}
