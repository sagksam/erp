/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.conversores.cadastros;

import com.erp.controle.classes.comuns.BaseBean;
import com.erp.modelo.cadastros.Conta;
import com.erp.modelo.cadastros.ContaBancaria;
import com.erp.util.JPAUtil;
import com.erp.util.Log;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

/**
 *
 * @author Paulo
 */
@ManagedBean
@SessionScoped
public class ConversorContaBean implements Serializable{
    
    private Conta contaSelecionada;
    private List<Conta> contasSelecionadas;
    private Date dataPagamento;
    private ContaBancaria contaBancaria;
    
    public void init(){    
        dataPagamento = new Date();
    }

    public Conta getContaSelecionada() {
        return contaSelecionada;
    }

    public void setContaSelecionada(Conta contaSelecionada) {
        this.contaSelecionada = contaSelecionada;
    }

    public List<Conta> getContasSelecionadas() {
        return contasSelecionadas;
    }

    public void setContasSelecionadas(List<Conta> contasSelecionadas) {
        this.contasSelecionadas = contasSelecionadas;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }
    
    //MÉTODO UTILIZADO PARA CONCLUIR AS CONTAS SELECIONADAS
    public void concluirConta(){
    
        EntityManager manager = JPAUtil.getEntityManager();
        for(Conta conta: getContasSelecionadas()){
        
            try{
                conta.setStatus("Concluído");
                conta.setDataPagamento(getDataPagamento());
                conta.setContaBancaria(getContaBancaria());
                conta.setValorPago(conta.getValorPrevisto());
                new BaseBean().salvar(conta);
            }
            catch(Exception e){
            
                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
            }
            finally{
            
                manager.close();
            }
        }
    }
    
    public boolean isContaVencida(Date dataVencimento, Date dataPagamento){
    
        return dataPagamento == null && new Date().after(dataVencimento);
    }

}
