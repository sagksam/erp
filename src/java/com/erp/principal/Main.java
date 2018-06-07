/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.principal;

import com.erp.controle.cadastros.EstoqueBean;
import com.erp.controle.cadastros.EstoqueSaidaBean;
import com.erp.controle.cadastros.InventarioBean;
import com.erp.controle.cadastros.ProdutoBean;
import com.erp.controle.relatorios.RelatorioProdutoBean;
import com.erp.modelo.cadastros.Estoque;
import com.erp.modelo.cadastros.Inventario;
import com.erp.modelo.cadastros.Produto;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author bianca
 */
public class Main {
    
    public static void main(String[] args) throws Exception{
     
        RelatorioProdutoBean bean = new RelatorioProdutoBean();
        bean.configurarFiltros();
        System.out.println(bean.getProdutos().size());
        /*for(Produto produtoTotal: new ProdutoBean().getProdutos()){
        
            System.out.println(produtoTotal.getDescricao());
        } */
    }      
}
