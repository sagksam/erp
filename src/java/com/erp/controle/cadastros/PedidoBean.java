/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.cadastros;

import com.erp.acesso.dados.GenericDAO;
import com.erp.controle.classes.comuns.BaseBean;
import com.erp.controle.classes.comuns.ListaBean;
import com.erp.modelo.cadastros.Pedido;
import com.erp.modelo.cadastros.PedidoProdutos;
import com.erp.modelo.cadastros.PedidoServicos;
import com.erp.modelo.cadastros.Produto;
import com.erp.modelo.cadastros.Servico;
import com.erp.modelo.classes.comuns.Lista.PrazoPagamento;
import com.erp.util.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author PauloHenrique
 */
@ManagedBean
@SessionScoped
public class PedidoBean extends BaseBean implements Serializable {
    
    private Pedido pedido;
    private List<Pedido> pedidos;
    private PedidoServicos pedidoServicos;
    private Servico servico;
    private PedidoProdutos pedidoProdutos;
    private Produto produto;

    //Método que inicia os campos da classe
    @PostConstruct
    public void init() {

        pedido = new Pedido();
        pedidos = new ArrayList<>();
        servico = new Servico();
        pedidoServicos = new PedidoServicos();
        produto = new Produto();
        pedidoProdutos = new PedidoProdutos();
    }

    //Getters e Setters
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    public List<Pedido> getPedidos(){
        return this.pedidos;
    }
    
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public PedidoServicos getPedidoServicos() {
        return pedidoServicos;
    }

    public void setPedidoServicos(PedidoServicos pedidoServicos) {
        this.pedidoServicos = pedidoServicos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public PedidoProdutos getPedidoProdutos() {
        return pedidoProdutos;
    }

    public void setPedidoProdutos(PedidoProdutos pedidoProdutos) {
        this.pedidoProdutos = pedidoProdutos;
    }

    //Método que adiciona um produto ao Pedido
    public String addProduto() {

        if (getPedido().getPedidosProdutos() == null) {
            getPedido().setPedidosProdutos(new ArrayList<PedidoProdutos>());
        }
        if (!isModoEdicao()) {
            getPedidoProdutos().setPedido(getPedido());
            getPedidoProdutos().setProduto(getProduto());
            getPedidoProdutos().setDescricaoProduto(getProduto().getDescricao());
            getPedido().getPedidosProdutos().add(getPedidoProdutos());
        }
        setModoEdicao(false);
        configurarValores();
        selecionarParcelas();
        produto = new Produto();
        pedidoProdutos = new PedidoProdutos();
        return getCadastro();
    }

    //Método que remove um produto do Pedido
    public String removeProduto(PedidoProdutos pedidoProduto) {

        if (pedidoProduto.getId() > 0) {
            getPedido().getPedidosProdutos().remove(pedidoProduto);
            try {
                new GenericDAO().delete(pedidoProduto);
            } 
            catch (Exception e) {
                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
            }
        } 
        else {
            getPedido().getPedidosProdutos().remove(pedidoProduto);
        }
        configurarValores();
        selecionarParcelas();
        return getCadastro();
    }

    //Método que adiciona um servico do Pedido
    public String addServico() {

        if (getPedido().getPedidosServicos() == null) {
            getPedido().setPedidosServicos(new ArrayList<PedidoServicos>());
        }
        if (!isModoEdicao()) {
            getPedidoServicos().setPedido(getPedido());
            getPedidoServicos().setServico(getServico());
            getPedidoServicos().setDescricaoServico(getServico().getDescricao());
            getPedido().getPedidosServicos().add(getPedidoServicos());
        }
        setModoEdicao(false);
        configurarValores();
        selecionarParcelas();
        servico = new Servico();
        pedidoServicos = new PedidoServicos();
        return getCadastro();
    }

    //Método que remove um servico do Pedido
    public String removeServico(PedidoServicos pedidoServico) {

        if (pedidoServico.getId() > 0) {
            getPedido().getPedidosServicos().remove(pedidoServico);
            try {
                new GenericDAO().delete(pedidoServico);
            } 
            catch (Exception e) {

                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
            }
        } 
        else {
            getPedido().getPedidosServicos().remove(pedidoServico);
        }
        configurarValores();
        selecionarParcelas();
        return getCadastro();
    }

    //Método para configurar a selecao de uma empresa no Pedido
    public void selecionarEmpresa() {

        getPedido().setPessoa(null);
        getPedido().setCep(getPedido().getEmpresa().getCep());
        getPedido().setEndereco(getPedido().getEmpresa().getEndereco());
        getPedido().setNumero(getPedido().getEmpresa().getNumero());
        getPedido().setComplemento(getPedido().getEmpresa().getComplemento());
        getPedido().setCidade(getPedido().getEmpresa().getCidade());
        getPedido().setBairro(getPedido().getEmpresa().getBairro());
        getPedido().setIbge(getPedido().getEmpresa().getIbge());
        getPedido().setSiafi(getPedido().getEmpresa().getSiafi());
        getPedido().setEstado(getPedido().getEmpresa().getEstado());
        getPedido().setPais(getPedido().getEmpresa().getPais());
        getPedido().setEmail(getPedido().getEmpresa().getEmail());

    }

    //Método para configurar a selecao de uma pessoa no Pedido
    public void selecionarPessoa() {

        getPedido().setEmpresa(null);
        getPedido().setCep(getPedido().getPessoa().getCep());
        getPedido().setEndereco(getPedido().getPessoa().getEndereco());
        getPedido().setNumero(getPedido().getPessoa().getNumero());
        getPedido().setComplemento(getPedido().getPessoa().getComplemento());
        getPedido().setBairro(getPedido().getPessoa().getBairro());
        getPedido().setCidade(getPedido().getPessoa().getCidade());
        getPedido().setIbge(getPedido().getPessoa().getIbge());
        getPedido().setSiafi(getPedido().getPessoa().getSiafi());
        getPedido().setEstado(getPedido().getPessoa().getEstado());
        getPedido().setPais(getPedido().getPessoa().getPais());
        getPedido().setEmail(getPedido().getPessoa().getEmail());
    }

    //Método para configurar a referencia do produto ao seleciona-lo no Pedido   
    public void selecionarProduto() {

        getPedidoProdutos().setReferencia(getProduto().getReferencia());
    }

    //Método para configurar a unidade do servico ao seleciona-lo no Pedido
    public void selecionarServico() {

        getPedidoServicos().setUnidade(getServico().getUnidade());
    }

    //Método que configura as parcelas e o valor das parcelas no Pedido
    public void selecionarParcelas() {

        for (PrazoPagamento prazo : new ListaBean().getPrazoPagamento()) {
            if (getPedido().getPrazoPagamento().equals(prazo.toString())) {
                getPedido().setParcelas(prazo.getParcelas());
                getPedido().setValorParcela(getPedido().getValorTotal() / prazo.getParcelas());
            }
        }
    }

    //Método para configurar os valores finais que sao trazidos dos itens do Pedido
    public void configurarValores() {

        double valorProdutos = 0;
        double valorServicos = 0;
        double valorDesconto = 0;
        double baseIPI = 0;
        double valorIPI = 0;
        double baseICMS = 0;
        double valorICMS = 0;
        double baseICMSST = 0;
        double valorICMSST = 0;
        double valorFrete = 0;
        double valorSeguro = 0;
        double valorTotal = 0;

        try {
            for (PedidoProdutos c : getPedido().getPedidosProdutos()) {

                valorProdutos += c.getValorProduto();
                valorDesconto += c.getValorDesconto();
                baseIPI += c.getBaseIPI();
                valorIPI += c.getValorIPI();
                baseICMS += c.getBaseICMS();
                valorICMS += c.getValorICMS();
                baseICMSST += c.getBaseICMSST();
                valorICMSST += c.getValorICMSST();
                valorFrete += c.getValorFrete();
                valorSeguro += c.getValorSeguro();
                valorTotal += c.getValorTotal();
            }
        } 
        catch (Exception e) {
            new Log().salvaErroLog(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
        }

        try {
            for (PedidoServicos c : getPedido().getPedidosServicos()) {
                valorServicos += c.getValorServico();
                valorDesconto += c.getValorDesconto();
                valorTotal += c.getValorTotal();
            }
        } 
        catch (Exception e) {
            new Log().salvaErroLog(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
        }
        getPedido().setValorProdutos(valorProdutos);
        getPedido().setValorServicos(valorServicos);
        getPedido().setValorDesconto(valorDesconto);
        getPedido().setBaseIPI(baseIPI);
        getPedido().setValorIPI(valorIPI);
        getPedido().setBaseICMS(baseICMS);
        getPedido().setValorICMS(valorICMS);
        getPedido().setBaseICMSST(baseICMSST);
        getPedido().setValorICMSST(valorICMSST);
        getPedido().setValorFrete(valorFrete);
        getPedido().setValorSeguro(valorSeguro);
        getPedido().setValorTotal(valorTotal);
    }

    @Override
    public String novo() {

        pedido = new Pedido();
        servico = new Servico();
        return getCadastro();
    }

}
