/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.cadastros;

import com.erp.controle.classes.comuns.BaseBean;
import com.erp.modelo.cadastros.Estoque;
import com.erp.modelo.cadastros.EstoqueProdutos;
import com.erp.modelo.cadastros.Inventario;
import com.erp.modelo.cadastros.InventarioProdutos;
import com.erp.modelo.cadastros.Produto;
import com.erp.modelo.classes.comuns.Auditoria;
import com.erp.util.Conversores;
import com.erp.util.JPAUtil;
import com.erp.util.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author bianca
 */
@ManagedBean
@SessionScoped
public class InventarioBean extends BaseBean implements Serializable{
    
    private Inventario inventario;
    private List<Inventario> inventarios;
    private Produto produto;
    private InventarioProdutos inventarioProdutos;
    private List<Produto> produtos;
    
    @PostConstruct
    public void init(){
    
        inventario = new Inventario();
        inventarios = new ArrayList<>();
        produto = new Produto();
        inventarioProdutos = new InventarioProdutos();
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public List<Inventario> getInventarios() {
        
        EntityManager manager = null;
        try {
            manager = JPAUtil.getEntityManager();
            Query query = manager.createNamedQuery("inventarios")
                    .setParameter("ativo", true);
            this.inventarios = query.getResultList();
            return this.inventarios;
        } 
        catch (Exception e) {
            return new ArrayList<>();
        } 
        finally {

            manager.close();
        }
        
    }

    public void setInventarios(List<Inventario> inventarios) {
        this.inventarios = inventarios;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public InventarioProdutos getInventarioProdutos() {
        return inventarioProdutos;
    }

    public void setInventarioProdutos(InventarioProdutos inventarioProdutos) {
        this.inventarioProdutos = inventarioProdutos;
    }

    public List<Produto> getProdutos() {
        EntityManager manager = JPAUtil.getEntityManager();
        try {
            Query query = manager.createNamedQuery("produtos")
                    .setParameter("ativo", true);
            this.produtos = query.getResultList();
        } 
        catch (Exception e) {
            new Log().salvaErroLog(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
        } finally {
            manager.close();
        }
        return produtos;
    }
    //Método que adiciona um novo produto ao inventário
    public String addProduto(){
    
        if (getInventario().getInventarioProdutos()== null) {
            getInventario().setInventarioProdutos(new ArrayList<InventarioProdutos>());
        }
        if (!isModoEdicao()) {
            getInventarioProdutos().setInventario(getInventario());
            getInventarioProdutos().setProduto(getProduto());
            getInventarioProdutos().setDescricao(getProduto().getDescricao());
            getInventario().getInventarioProdutos().add(getInventarioProdutos());
        }
        setModoEdicao(false);
        produto = new Produto();
        inventarioProdutos = new InventarioProdutos();
        return getCadastro();
    }
    
    //Método que remove um produto do inventário
    public String removeProduto(InventarioProdutos inventarioProduto){
    
        if (inventarioProduto.getId() > 0) {
            getInventario().getInventarioProdutos().remove(inventarioProduto);
            try {
                getDao().delete(inventarioProduto);
            } 
            catch (Exception e) {
                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
            }
        } 
        else {
            getInventario().getInventarioProdutos().remove(inventarioProduto);
        }
        return getCadastro();
    }
    
    //Método para configurar a referencia e a unidade do produto ao seleciona-lo no inventário   
    public void selecionarProduto(){
    
        getInventarioProdutos().setReferencia(getProduto().getReferencia());
        getInventarioProdutos().setUnidade(getProduto().getUnidade());
        
    }
    
    //Método que faz a movimentação do estoque no inventário
    @Override
    public boolean geraMovimentacaoEstoque(){
    
        boolean geraMovimentacao = getInventario().isAplicado();
        Produto produto;
        if(geraMovimentacao){
            
            Estoque estoqueEntrada = new Estoque();
            boolean entrada = false;
            Estoque estoqueSaida;
            boolean saida = false;
            Auditoria auditoria = new Auditoria();
            estoqueEntrada.setAtivo(true);
            estoqueEntrada.setTipo("Entrada");
            estoqueEntrada.setFilial(getInventario().getFilial());
            estoqueEntrada.setData(getInventario().getData());
            estoqueEntrada.setResponsavel(getInventario().getResponsavel());
            estoqueEntrada.setInventario(getInventario().getId());
            estoqueEntrada.setPedido(0);
            estoqueEntrada.setNotaProduto(0);
            auditoria.setCriacaoUsuario(new BaseBean().getUsuario());
            auditoria.setCriacaoData(new Date());
            auditoria.setAlteracaoData(new Date());
            auditoria.setAlteracaoUsuario(new BaseBean().getUsuario());
            estoqueEntrada.setAuditoria(auditoria);
            estoqueSaida = estoqueEntrada;
            estoqueSaida.setTipo("Saída");
            
            for(InventarioProdutos inventarioProduto: getInventario().getInventarioProdutos()){
            
                if(inventarioProduto.getProduto().isControleEstoque()){
                    EstoqueProdutos estoqueProdutos = new EstoqueProdutos();
                    produto = getDao().findById(inventarioProduto.getProduto().getId(), Produto.class);
                    double estoqueAtual = produto.getEstoqueAtual();
                    estoqueProdutos.setAtivo(true);
                    estoqueProdutos.setProduto(inventarioProduto.getProduto());
                    estoqueProdutos.setDescricao(inventarioProduto.getDescricao());
                    estoqueProdutos.setReferencia(inventarioProduto.getReferencia());
                    estoqueProdutos.setUnidade(inventarioProduto.getUnidade());
                    estoqueProdutos.setEstoqueAtual(estoqueAtual);
                    System.out.println("Quantidade " + inventarioProduto.getQuantidade() + " estoqueAtual " + produto.getEstoqueAtual());
                    double quantidade = 0;
                    if(inventarioProduto.getQuantidade() > estoqueAtual){
                        System.out.println("ENTRANDO....");
                        quantidade = inventarioProduto.getQuantidade() - estoqueAtual;
                        estoqueProdutos.setSaldo(quantidade + estoqueAtual); 
                        estoqueProdutos.setEstoque(estoqueEntrada);
                        System.out.println("O que é " + getDao().findById(estoqueEntrada.getId(), Estoque.class));
                        if(!entrada){
                        
                            try {
                                System.out.println("CRIANDO ENTRADA....");
                                getDao().save(estoqueEntrada);
                                entrada = true;
                            } 
                            catch (Exception e) {

                                new Log().salvaErroLog(e);
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
                            }
                        }
                    }    
                    else if(inventarioProduto.getQuantidade() < estoqueAtual){
                        System.out.println("SAINDO....");
                        quantidade = inventarioProduto.getQuantidade() + estoqueAtual;
                        estoqueProdutos.setSaldo(quantidade - estoqueAtual);
                        estoqueProdutos.setEstoque(estoqueSaida);
                        System.out.println("O que é " + getDao().findById(estoqueSaida.getId(), Estoque.class));
                        if(!saida){
                        
                            try {
                                System.out.println("CRIANDO SAIDA....");
                                getDao().save(estoqueSaida);
                                saida = true;
                            } 
                            catch (Exception e) {

                                new Log().salvaErroLog(e);
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
                            }
                        }
                    }    
                    estoqueProdutos.setQuantidade((int) quantidade);    
                    estoqueProdutos.setValorUnitario(0);
                    estoqueProdutos.setValorTotal(0);
                    
                    try {
                        getDao().save(estoqueProdutos);
                    } catch (Exception e) {
                        new Log().salvaErroLog(e);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
                    }
                    produto.setEstoqueAtual(estoqueProdutos.getSaldo());
                    try {
                        getDao().update(produto);
                    } 
                    catch (Exception e) {
                        new Log().salvaErroLog(e);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
                    }
                 
                }
                
            }
            if (getInventario().getTipo().equals("Total")) {

                for (Produto produtoTotal : getProdutos()) {
                    if(!isZeraQuantidadeProduto(produtoTotal)){
                    
                        produto = getDao().findById(produtoTotal.getId(), Produto.class);
                        produto.setEstoqueAtual(0);
                        try {
                            getDao().update(produto);
                        } catch (Exception e) {
                            new Log().salvaErroLog(e);
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
                        }
                   
                    }    
                }
            }
        }
        return geraMovimentacao;
        
    }
    //Método que verifica e retorna true caso um produto esteja na lista do inventário e false o contrário
    public boolean isZeraQuantidadeProduto(Produto produto){
    
        for (InventarioProdutos inventarioProduto : getInventario().getInventarioProdutos()) {

            if (produto.getId() == inventarioProduto.getProduto().getId()) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String novo() {

        inventario = new Inventario();
        inventarioProdutos = new InventarioProdutos();
        return getCadastro();
    }
    
    @Override
    public String toString() {

        return "inventario";
    }
       
    @Override
    public String getNomeCadastro(){
    
        return "Inventário de Produtos";
    }
    
    @Override
    public String getDescricaoCadastro(){
    
        return "Atualize o estoque de produtos de compra e venda, "
                + "efetuando a contagem dos itens da empresa. ";
    }
    
    //Método que retorna os dados que serão exibidos no impresso 
    @Override
    public Map<String, Object> dadosImpressao(){
    
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ID: ", getInventario().getId());
        map.put("Filial: ", getInventario().getFilial());
        map.put("Data: ", Conversores.dateToString(getInventario().getData()));
        map.put("Motivo: ", getInventario().getMotivo());
        map.put("Responsável: ", getInventario().getResponsavel());        
        return map;
    }
    
    //Método que retorna os dados dos produtos que serão exibidos no impresso 
    @Override
    public List dadosImpressaoProdutos(){
    
        List<Map> listMap = new ArrayList<>();
        for(InventarioProdutos inventarioProduto: getInventario().getInventarioProdutos()){                  
          Map<String, Object> map = new LinkedHashMap<>();
          map.put("Referência:", inventarioProduto.getReferencia());
          map.put("Produto:", inventarioProduto.getDescricao());
          map.put("Quantidade:", inventarioProduto.getQuantidade());
          listMap.add(map);
        }  
        return listMap;
    }
    
}
