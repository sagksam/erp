/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.cadastros;

import com.erp.controle.classes.comuns.BaseBean;
import com.erp.modelo.cadastros.Conta;
import com.erp.util.JPAUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author PauloHenrique
 */
@ManagedBean(name = "contaBean")
@SessionScoped
public class ContaBean extends BaseBean {

    private Conta conta;
    private List<Conta> contas;
    private List<Conta> contasPedido;
    private List<Conta> contasNotaProduto;
    private List<Conta> contasNotaServico;
    //Método que inicia os campos da classe
    @PostConstruct
    public void init() {

        conta = new Conta();
        contas = new ArrayList<>();
        contasPedido = new ArrayList<>();
        contasNotaProduto = new ArrayList<>();
        contasNotaServico = new ArrayList<>();
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
    //Método para selecionar as contas relecionadas ao id do pedido
    public List<Conta> getContasPedido(int id) {
        EntityManager manager = null;
        try {
            manager = JPAUtil.getEntityManager();
            Query query = manager.createNamedQuery("contasPedidoById")
                    .setParameter("pedidoId", id)
                    .setParameter("ativo", true);
            setContasPedido(query.getResultList());
            return contasPedido;
        } 
        catch (Exception e) {

            return new ArrayList<>();
        }
        finally{
        
            manager.close();
        } 

    }

    public void setContasPedido(List<Conta> contasPedido) {
        this.contasPedido = contasPedido;
    }
    
    //Método para selecionar as contas relecionadas ao id da nota de produto
    public List<Conta> getContasNotaProduto(int id) {
        EntityManager manager = null;
        try {
            manager = JPAUtil.getEntityManager();
            Query query = manager.createNamedQuery("contasNotaProdutoById")
                    .setParameter("notaProdutoId", id)
                    .setParameter("ativo", true);
            setContasNotaProduto(query.getResultList());
            return contasNotaProduto;
        } 
        catch (Exception e) {

            return new ArrayList<>();
        }
        finally{
        
            manager.close();
        } 

    }

    public void setContasNotaProduto(List<Conta> contasNotaProduto) {
        this.contasNotaProduto = contasNotaProduto;
    }
    
     //Método para selecionar as contas relecionadas ao id da nota de servico
    public List<Conta> getContasNotaServico(int id) {
        EntityManager manager = null;
        try {
            manager = JPAUtil.getEntityManager();
            Query query = manager.createNamedQuery("contasNotaServicoById")
                    .setParameter("notaServicoId", id)
                    .setParameter("ativo", true);
            setContasNotaServico(query.getResultList());
            return contasNotaServico;
        } 
        catch (Exception e) {

            return new ArrayList<>();
        }
        finally{
        
            manager.close();
        } 

    }

    public void setContasNotaServico(List<Conta> contasNotaServico) {
        this.contasNotaServico = contasNotaServico;
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
