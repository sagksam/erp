/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.cadastros;

import com.erp.acesso.dados.GenericDAO;
import com.erp.controle.classes.comuns.BaseBean;
import com.erp.controle.servicos.PessoaService;
import com.erp.modelo.cadastros.Endereco;
import com.erp.modelo.cadastros.Pessoa;
import com.erp.modelo.cadastros.Telefone;
import com.erp.util.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author bianca
 */
@ManagedBean
@ViewScoped
public class PessoaBean extends BaseBean implements Serializable {

    private Pessoa pessoa;
    private List<Pessoa> pessoas;
    private List<Pessoa> pessoasTransportadora;
    private List<Pessoa> pessoasVenda;
    private List<Pessoa> pessoasCompra;
    private Telefone telefone;
    private Endereco endereco;

    @ManagedProperty("#{pessoaService}")
    private PessoaService service;

    //Método que inicializa os campos da classe
    @PostConstruct
    public void init() {

        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("objetoId")){
            long id = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("objetoId").toString());
            pessoa = new GenericDAO().findById((int) id, Pessoa.class);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("objetoId");
        }
        else{
            pessoa = new Pessoa();
        }
        pessoas = getService().getPessoas();
        pessoasTransportadora = getService().getPessoasTransportadora();
        pessoasVenda = getService().getPessoasVenda();
        pessoasCompra = getService().getPessoasCompra();
        telefone = new Telefone();
        endereco = new Endereco();

    }

    //Getters e Setters
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {

        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public List<Pessoa> getPessoasTransportadora() {
        return pessoasTransportadora;
    }

    public void setPessoasTransportadora(List<Pessoa> pessoasTransportadora) {
        this.pessoasTransportadora = pessoasTransportadora;
    }

    public List<Pessoa> getPessoasVenda() {
        return pessoasVenda;
    }

    public void setPessoasVenda(List<Pessoa> pessoasVenda) {
        this.pessoasVenda = pessoasVenda;
    }

    public List<Pessoa> getPessoasCompra() {
        return pessoasCompra;
    }

    public void setPessoasCompra(List<Pessoa> pessoasCompra) {
        this.pessoasCompra = pessoasCompra;
    }
    
    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public PessoaService getService() {
        return service;
    }

    public void setService(PessoaService service) {
        this.service = service;
    }

    //Método que adiciona telefone da Pessoa
    public String addTelefone() {

        if (getPessoa().getTelefones() == null) {
            getPessoa().setTelefones(new ArrayList<Telefone>());
        }
        if (!isModoEdicao()) {
            getPessoa().getTelefones().add(getTelefone());
            getTelefone().setPessoa(getPessoa());
        }
        telefone = new Telefone();
        return getCadastro();
    }

    //Método que remove o telefone da Pessoa
    public String removeTelefone(Telefone telefone) {

        if (telefone.getId() > 0) {
            getPessoa().getTelefones().remove(telefone);
            try {
                new GenericDAO().delete(telefone);
            }
            catch (Exception e) {
                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
            }
        } 
        else {
            getPessoa().getTelefones().remove(telefone);
        }
        return getCadastro();
    }

    //Método que adiciona endereco a Pessoa
    public String addEndereco() {

        if (getPessoa().getEnderecos() == null) {
            getPessoa().setEnderecos(new ArrayList<Endereco>());
        }
        if (!isModoEdicao()) {
            getPessoa().getEnderecos().add(getEndereco());
            getEndereco().setPessoa(getPessoa());
        }
        endereco = new Endereco();
        return getCadastro();
    }

    //Método que remove endereco da Pessoa
    public String removeEndereco(Endereco endereco) {

        if (endereco.getId() > 0) {
            getPessoa().getEnderecos().remove(endereco);
            try {
                new GenericDAO().delete(endereco);
            } catch (Exception e) {
                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
            }
        } else {
            getPessoa().getEnderecos().remove(endereco);
        }
        return getCadastro();
    }
    
    @Override
    public String novo() {

        setPessoa(new Pessoa());
        getPessoa().setTelefones(new ArrayList<Telefone>());
        getPessoa().setEnderecos(new ArrayList<Endereco>());
        return getCadastro();
    }

    @Override
    public String toString() {

        return "pessoa";
    }

    @Override
    public String getNomeCadastro() {

        return "Pessoas";
    }

    @Override
    public String getDescricaoCadastro() {

        return "Cadastre e gerencie aqui as pessoas que possuem alguma "
                + "relação comercial com a sua sejam eles seus clientes, fornecedores, parceiros ou "
                + "transportadores.";
    }

    @Override
    public String getDescricaoRelatorio() {

        return "Acompanhe aqui as pessoas que possuem alguma relação comercial com a sua "
                + "filtre também essas pessoas por tipo, estado ou cidade";
    }
    
    //Método que retorna os dados que serão exibidos no impresso 
    @Override
    public Map<String, Object> dadosImpressao(){
    
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ID: ", getPessoa().getId());
        map.put("CPF: ", getPessoa().getCpf());
        map.put("Tipo: ", getPessoa().getTipo());
        map.put("Operações: ", getPessoa().getOperacoes());
        map.put("Nome: ", getPessoa().getNomeCompleto());
        map.put("CEP: ", getPessoa().getCep());
        map.put("Endereço: ", getPessoa().getEndereco());
        map.put("Número: ", getPessoa().getNumero());
        map.put("Bairro: ", getPessoa().getBairro());
        map.put("Email: ", getPessoa().getEmail());        
        return map;
    }
}
