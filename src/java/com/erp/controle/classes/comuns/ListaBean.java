/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.classes.comuns;

import com.erp.modelo.classes.comuns.Lista.Atividade;
import com.erp.modelo.classes.comuns.Lista.CFOP;
import com.erp.modelo.classes.comuns.Lista.CadastroStatus;
import com.erp.modelo.classes.comuns.Lista.Categoria;
import com.erp.modelo.classes.comuns.Lista.ClassFiscal;
import com.erp.modelo.classes.comuns.Lista.ContaStatus;
import com.erp.modelo.classes.comuns.Lista.Departamento;
import com.erp.modelo.classes.comuns.Lista.Escolaridade;
import com.erp.modelo.classes.comuns.Lista.Estado;
import com.erp.modelo.classes.comuns.Lista.FormaPagamento;
import com.erp.modelo.classes.comuns.Lista.Moeda;
import com.erp.modelo.classes.comuns.Lista.NotaProdutoStatus;
import com.erp.modelo.classes.comuns.Lista.NotaServicoStatus;
import com.erp.modelo.classes.comuns.Lista.Operacao;
import com.erp.modelo.classes.comuns.Lista.PadraoBanco;
import com.erp.modelo.classes.comuns.Lista.PadraoConta;
import com.erp.modelo.classes.comuns.Lista.PrazoPagamento;
import com.erp.modelo.classes.comuns.Lista.ProdutoOperacao;
import com.erp.modelo.classes.comuns.Lista.ProdutoStatus;
import com.erp.modelo.classes.comuns.Lista.Sexo;
import com.erp.modelo.classes.comuns.Lista.Tipo;
import com.erp.modelo.classes.comuns.Lista.Vendedor;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author PauloHenrique
 */
@ManagedBean
@ApplicationScoped
public class ListaBean {
    
    //Método que retorna o enum Sexo
    public Sexo[] getSexos(){
    
        return Sexo.values();
    }

    //Método que retorna o enum Tipo
    public Tipo[] getTipos(){
    
        return Tipo.values();
    }
    //Método que retorna o valor para as tabelas do enum Tipo
    public String getTipos(String valor){
    
        switch(valor){
        
            case "FUNCIONARIO":
                return "Funcionário";
            case "CLIENTE":
                return "Cliente";
            case "FORNECEDOR":
                return "Fornecedor";
            case "TRANSPORTADOR":
                return "Transportador";
        }
        return valor;
    }
    //Método que retorna o enum Operacao
    public Operacao[] getOperacoes(){
    
        return Operacao.values();
    }
    //Método que retorna o valor para as tabelas do enum Operacao
    public String getOperacoes(String valor){
       
        switch(valor){
            
            case "PAGAMENTO":
                 return "Pagamento"; 
            case "RECEBIMENTO":
                 return "Recebimento";  
            case "PAGAMENTOERECEBIMENTO":
                return "Pagamento e Recebimento";               
        }
        return valor;
    }
    //Método que retorna o enum Departamento
    public Departamento[] getDepartamentos(){
    
        return Departamento.values();
    }
    //Método que retorna o enum Escolaride
    public Escolaridade[] getEscolaridades(){
    
        return Escolaridade.values();
    }
    //Método que retorna o enum Estado
     public Estado[] getEstados(){
    
        return Estado.values();
    }
    //Método que retorna o enum Vendedor 
    public Vendedor[] getVendedores(){
    
        return Vendedor.values();
    } 
    //Método que retorna o enum Catetoria
    public Categoria[] getCategorias(){
    
        return Categoria.values();
    }
    //Método que retorna o valor para as tabelas do enum Categoria
    public String getCategorias(String valor){
    
         switch(valor){
            
            case "BLOCO":
                 return "Bloco"; 
            case "PECA":
                 return "Peça";  
            case "PLACA":
                return "Placa";               
        }
        return valor;
    }
    //Método que retorna o enum ProdutoOperacao
    public ProdutoOperacao[] getProdutoOperacoes(){
    
        return ProdutoOperacao.values();
    }
    //Método que retorna o valor para as tabelas do enum Operacao
    public String getProdutoOperacoes(String valor){
       
        switch(valor){
            
            case "COMPRA":
                 return "Compra"; 
            case "VENDA":
                 return "Venda";  
            case "COMPRAEVENDA":
                return "Compra e Venda";               
        }
        return valor;
    }
    //Método que retorna o enum ProdutoStatus
    public ProdutoStatus[] getProdutoStatus(){
       
        return ProdutoStatus.values();
    }
    //Método que retorna o enum Moeda
    public Moeda[] getMoeda(){
       
        return Moeda.values();
    }   
    //Método que retorna o enum ClassFiscal
    public ClassFiscal[] getClassFiscal(){
       
        return ClassFiscal.values();
    }
    //Método que retorna o enum ClassFiscal
    public CFOP[] getCFOP(){
       
        return CFOP.values();
    }
    //Método que retorna o enum CadastroStatus
    public CadastroStatus[] getCadastroStatus(){
    
        return CadastroStatus.values();
    }   
    //Método que retorna o valor para as tabelas do enum CadastroStatus
    public String getCadastroStatus(String valor){
    
        switch(valor){
        
            case "PENDENTE":
                return "Pendente";
            case "ENTREGUE":
                return "Entregue";
            case "CONCLUIDO":
                return "Concluído";
            case "FATURADO":
                return "Faturado";    
            case "CANCELADO":
                return "Cancelado";                   
        }
        return valor;
    }
    
    //Método que retorna o enum PadraoBanco
    public PadraoBanco[] getPadraoBanco(){
    
        return PadraoBanco.values();
    }
    //Método que retorna o enum PadraoConta
    public PadraoConta[] getPadraoConta(){
    
        return PadraoConta.values();
    }
    //Método que retorna o enum PrazoPagamento
    public PrazoPagamento[] getPrazoPagamento(){
    
        return PrazoPagamento.values();
    }
    //Método que retorna o enum FormaPagamento
    public FormaPagamento[] getFormaPagamento(){
    
        return FormaPagamento.values();
    }
    //Método que retorna o enum Atividade
    public Atividade[] getAtividade(){
    
        return Atividade.values();
    }
    //Método que retorna o enum ContaStatus
    public ContaStatus[] getContasStatus(){
    
        return ContaStatus.values();
    }
    //Método que retorna o enum NotaProdutoStatus
    public NotaProdutoStatus[] getNotasProdutosStatus(){
    
        return NotaProdutoStatus.values();
    }
    //Método que retorna o valor para as tabelas do enum NotasProdutosStatus
    public String getNotasProdutosStatus(String valor){
    
        switch(valor){
        
            case "DIGITADA":
                return "Digitada";
            case "AUTORIZADA":
                return "Autorizada";
            case "CANCELADA":
                return "Cancelada";          
        }
        return valor;
    }
    //Método que retorna o enum NotaServicoStatus
    public NotaServicoStatus[] getNotasServicosStatus(){
    
        return NotaServicoStatus.values();
    }
     //Método que retorna o valor para as tabelas do enum NotasServicosStatus
    public String getNotasServicosStatus(String valor){
    
        switch(valor){
        
            case "DIGITADA":
                return "Digitada";
            case "AUTORIZANDO":
                return "Autorizando";
            case "AUTORIZADA":
                return "Autorizada";
            case "RECUSADA":
                return "Recusado";
            case "CANCELANDO":
                return "Cancelando";
            case "CANCELADA":
                return "Cancelada";          
        }
        return valor;
    }

}