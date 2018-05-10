/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.cadastros;

import com.erp.acesso.dados.GenericDAO;
import com.erp.controle.classes.comuns.BaseBean;
import com.erp.controle.servicos.ProdutoService;
import com.erp.modelo.cadastros.Produto;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author PauloHenrique
 */
@ManagedBean
@ViewScoped
public class ProdutoBean extends BaseBean implements Serializable {

    private Produto produto;
    private List<Produto> produtos;
    private List<Produto> produtosVenda;
    private List<Produto> produtosCompra;


    @ManagedProperty("#{produtoService}")
    private ProdutoService service;

    //Método que inicia os campos da classe
    @PostConstruct
    public void init() {

        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("objetoId")){
            long id = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("objetoId").toString());
            produto = new GenericDAO().findById((int) id, Produto.class);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("objetoId");
        }
        else{
            produto = new Produto();
        }
        produtos = getService().getProdutos();
        produtosVenda = getService().getProdutosVenda();
        produtosCompra = getService().getProdutosCompra();

    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Produto> getProdutosVenda() {
        return produtosVenda;
    }

    public void setProdutosVenda(List<Produto> produtosVenda) {
        this.produtosVenda = produtosVenda;
    }

    public List<Produto> getProdutosCompra() {
        return produtosCompra;
    }

    public void setProdutosCompra(List<Produto> produtosCompra) {
        this.produtosCompra = produtosCompra;
    }

    public ProdutoService getService() {
        return service;
    }

    public void setService(ProdutoService service) {
        this.service = service;
    }
    
    @Override
    public String novo() {

        setProduto(new Produto());
        return getCadastro();
    }

    @Override
    public String toString() {

        return "produto";
    }

    @Override
    public String getNomeCadastro() {

        return "Produtos";
    }

    @Override
    public String getDescricaoCadastro() {

        return "Cadastre e gerencie aqui os produtos de sua empresa "
                + "sejam eles produtos de compra, produtos de venda ou "
                + "produtos de produção.";
    }

    @Override
    public String getDescricaoRelatorio() {

        return "Acompanhe aqui os produtos de sua empresa "
                + "filtre também esses produtos por categoria, operação ou descrição";
    }
    
    //Método que retorna os dados que serão exibidos no impresso 
    @Override
    public Map<String, Object> dadosImpressao(){
    
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ID: ", getProduto().getId());
        map.put("Categoria: ", getProduto().getCategoria());
        map.put("Referencia: ", getProduto().getReferencia());
        map.put("Operações: ", getProduto().getOperacoes());
        map.put("Valor Custo: ", "R$ " + getProduto().getValorCusto());
        map.put("Margem Lucro: ", "% " + getProduto().getMargemLucro());
        map.put("Valor Venda: ", "R$ " + getProduto().getValorVenda());
        map.put("Valor Desconto: ", "R$ " + getProduto().getValorDesconto());
        map.put("Valor Final: ", "R$ " + getProduto().getValorFinal()); 
        return map;
    }

}
