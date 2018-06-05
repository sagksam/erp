/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.cadastros;

import com.erp.acesso.dados.GenericDAO;
import com.erp.controle.classes.comuns.BaseBean;
import com.erp.modelo.cadastros.Estoque;
import com.erp.modelo.cadastros.EstoqueProdutos;
import com.erp.modelo.cadastros.Produto;
import com.erp.modelo.classes.comuns.Auditoria;
import com.erp.util.Conversores;
import com.erp.util.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Paulo
 */
@ManagedBean
@SessionScoped
public class EstoqueBean extends BaseBean implements Serializable{
    
    private Estoque estoque;
    private List<Estoque> estoques;
    private EstoqueProdutos estoqueProdutos;
    private Produto produto;
    
    @PostConstruct
    public void init(){
    
        estoque = new Estoque();
        estoques = new ArrayList<>();
        estoqueProdutos = new EstoqueProdutos();
        produto = new Produto();
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public List<Estoque> getEstoques() {
        return estoques;
    }

    public void setEstoques(List<Estoque> estoques) {
        this.estoques = estoques;
    }
    
    public EstoqueProdutos getEstoqueProdutos() {
        return estoqueProdutos;
    }

    public void setEstoqueProdutos(EstoqueProdutos estoqueProdutos) {
        this.estoqueProdutos = estoqueProdutos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    //Método que adiciona um produto ao Estoque
    public String addProduto() {

        if (getEstoque().getEstoqueProdutos() == null) {
            getEstoque().setEstoqueProdutos(new ArrayList<EstoqueProdutos>());
        }
        if (!isModoEdicao()) {
            getEstoqueProdutos().setEstoque(getEstoque());
            getEstoqueProdutos().setProduto(getProduto());
            getEstoqueProdutos().setDescricao(getProduto().getDescricao());
            getEstoque().getEstoqueProdutos().add(getEstoqueProdutos());
        }
        setModoEdicao(false);
        produto = new Produto();
        estoqueProdutos = new EstoqueProdutos();
        return getCadastro();
    }
    
    //Método que remove um produto do Estoque
    public String removeProduto(EstoqueProdutos estoqueProduto) {

        if (estoqueProduto.getId() > 0) {
            getEstoque().getEstoqueProdutos().remove(estoqueProduto);
            try {
                getDao().delete(estoqueProduto);
            } 
            catch (Exception e) {
                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
            }
        } 
        else {
            getEstoque().getEstoqueProdutos().remove(estoqueProduto);
        }
        return getCadastro();
    }
    
    //Método para configurar a referencia do produto ao seleciona-lo no Estoque   
    public void selecionarProduto() {

        getEstoqueProdutos().setReferencia(getProduto().getReferencia());
        getEstoqueProdutos().setUnidade(getProduto().getUnidade());
        getEstoqueProdutos().setEstoqueAtual(getProduto().getEstoqueAtual());
    }
    
    //Método que produz a movimentação do estoque em suas subclasses
    @Override
    public boolean geraMovimentacaoEstoque(){
    
        Produto produto;
        /*DELETANDO A MOVIMENTACAO*/
            if (!getEstoque().isAtivo()) {
                for(EstoqueProdutos estoqueProdutos: getEstoque().getEstoqueProdutos()){
                
                    if(estoqueProdutos.getProduto().isControleEstoque()){  
                        produto = getDao().findById(estoqueProdutos.getProduto().getId(), Produto.class);
                        if(getEstoque().getTipo().equals("Entrada"))
                          produto.setEstoqueAtual(produto.getEstoqueAtual() - estoqueProdutos.getQuantidade());
                        else
                          produto.setEstoqueAtual(produto.getEstoqueAtual() + estoqueProdutos.getQuantidade());  
                        try {
                            getDao().update(produto);
                        } catch (Exception e) {

                            new Log().salvaErroLog(e);
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
                        }
                    }
                }
                try {
                    getDao().update(getEstoque());
                } catch (Exception e) {
                    new Log().salvaErroLog(e);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
                }
            }
        /*GERANDO A MOVIMENTAÇÃO*/
        if(getEstoque().isAtivo()){
            for(EstoqueProdutos estoqueProdutos: getEstoque().getEstoqueProdutos()){             
                    if(estoqueProdutos.getProduto().isControleEstoque()){
                        produto = getDao().findById(estoqueProdutos.getProduto().getId(), Produto.class);
                        if(getEstoque().getTipo().equals("Entrada"))
                          produto.setEstoqueAtual(produto.getEstoqueAtual() + estoqueProdutos.getQuantidade());
                        else
                          produto.setEstoqueAtual(produto.getEstoqueAtual() - estoqueProdutos.getQuantidade());  
                        try {
                            getDao().update(produto);
                        } catch (Exception e) {

                            new Log().salvaErroLog(e);
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
                        }
                    }
                }
                try {
                    getDao().update(getEstoque());
                } 
                catch (Exception e) {
                    new Log().salvaErroLog(e);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
                }
            }
           return true;
    }

     @Override
    public String novo() {

        estoque = new Estoque();
        estoqueProdutos = new EstoqueProdutos();
        return getCadastro();
    }
    
    //Método que retorna os dados que serão exibidos no impresso 
    @Override
    public Map<String, Object> dadosImpressao(){
    
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ID: ", getEstoque().getId());
        map.put("Filial: ", getEstoque().getFilial());
        map.put("Data: ", Conversores.dateToString(getEstoque().getData()));
        map.put("Motivo: ", getEstoque().getMotivo());
        map.put("Responsável: ", getEstoque().getResponsavel());        
        map.put("Inventário: ", getEstoque().getInventario());
        map.put("Pedido: ", getEstoque().getPedido());
        map.put("Nota de Produto: ", getEstoque().getNotaProduto());
        return map;
    }
    
    //Método que retorna os dados dos produtos que serão exibidos no impresso 
    @Override
    public List dadosImpressaoProdutos(){
    
        List<Map> listMap = new ArrayList<>();
        for(EstoqueProdutos estoqueProduto: getEstoque().getEstoqueProdutos()){                  
          Map<String, Object> map = new LinkedHashMap<>();
          map.put("Referência:", estoqueProduto.getReferencia());
          map.put("Produto:", estoqueProduto.getDescricao());
          map.put("Quantidade:", estoqueProduto.getQuantidade());
          map.put("Saldo:", estoqueProduto.getSaldo());
          map.put("Valor Unitário:", "R$ " + estoqueProduto.getValorUnitario());
          map.put("Valor Total:", "R$ " + estoqueProduto.getValorTotal());
          listMap.add(map);
        }  
        return listMap;
    }

}
