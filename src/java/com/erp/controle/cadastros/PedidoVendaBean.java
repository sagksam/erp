/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.cadastros;


import com.erp.modelo.cadastros.Pedido;
import com.erp.modelo.cadastros.PedidoProdutos;
import com.erp.modelo.cadastros.PedidoServicos;
import com.erp.util.Conversores;
import com.erp.util.JPAUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author bianca
 */
@ManagedBean
@SessionScoped
public class PedidoVendaBean extends PedidoBean implements Serializable {

    @Override
    public List<Pedido> getPedidos(){
        EntityManager manager = null;
        try {
            manager = JPAUtil.getEntityManager();
            Query query = manager.createNamedQuery("pedidosVenda")
                    .setParameter("tipo", "venda")
                    .setParameter("ativo", true);
            setPedidos(query.getResultList());
            return super.getPedidos();
        } catch (Exception e) {

            return new ArrayList<>();
        } finally {

            manager.close();
        }
    }
    
    @Override
    public String toString() {

        return "pedidovenda";
    }

    @Override
    public String getNomeCadastro() {

        return "Pedidos de Venda";
    }

    @Override
    public String getDescricaoCadastro() {

        return "Cadastre e gerencie aqui os pedidos de venda de sua empresa, "
                + "gere através desses cadastros notas de serviço e também de produto.";
    }

    @Override
    public String getDescricaoRelatorio() {

        return "Acompanhe aqui os pedidos de venda de sua empresa "
                + "filtre também esses pedidos por data de emissão, status ou descrição do cliente";
    }

    //Método que retorna os dados que serão exibidos no impresso 
    @Override
    public Map<String, Object> dadosImpressao(){
    
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ID: ", getPedido().getId());
        map.put("Filial: ", getPedido().getFilial());
        map.put("Descrição: ", getPedido().getDescricao());
        map.put("Emissão: ", Conversores.dateToString(getPedido().getDataEmissao()));
        map.put("Cep: ", getPedido().getCep());
        map.put("Endereço: ", getPedido().getEndereco());        
        map.put("Número: ", getPedido().getNumero());
        map.put("Bairro: ", getPedido().getBairro());
        map.put("Cidade: ", getPedido().getCidade());
        map.put("Estado: ", getPedido().getEstado());
        return map;
    }
    
    //Método que retorna os dados dos produtos que serão exibidos no impresso 
    @Override
    public List dadosImpressaoProdutos(){
    
        List<Map> listMap = new ArrayList<>();
        for(PedidoProdutos pedidoProduto: getPedido().getPedidosProdutos()){                  
          Map<String, Object> map = new LinkedHashMap<>();
          map.put("Referência:", pedidoProduto.getReferencia());
          map.put("Produto:", pedidoProduto.getDescricaoProduto());
          map.put("Quantidade:", pedidoProduto.getQuantidade());
          map.put("Valor Unitário:", pedidoProduto.getValorUnitario());
          map.put("Valor Produto:", "R$ " + pedidoProduto.getValorProduto());
          map.put("Valor Desconto", "R$ " + pedidoProduto.getValorDesconto());
          map.put("Valor Total:", "R$ " + pedidoProduto.getValorTotal());
          listMap.add(map);
        }  
        return listMap;
    }
    
    //Método que retorna os dados dos servicos que serão exibidos no impresso 
    @Override
    public List dadosImpressaoServicos(){
    
        List<Map> listMap = new ArrayList<>();
        for(PedidoServicos pedidoServico: getPedido().getPedidosServicos()){                  
          Map<String, Object> map = new LinkedHashMap<>();
          map.put("Serviço:", pedidoServico.getDescricaoServico());
          map.put("Quantidade:", pedidoServico.getQuantidade());
          map.put("Valor Unitário:", pedidoServico.getValorUnitario());
          map.put("Valor Serviço:", "R$ " + pedidoServico.getValorServico());
          map.put("Valor Desconto", "R$ " + pedidoServico.getValorDesconto());
          map.put("Valor Total:", "R$ " + pedidoServico.getValorTotal());
          listMap.add(map);
        }  
        return listMap;
    }
    
    //Método que retorna os dados totais que serão exibidos no impresso 
    @Override
    public Map<String, Object> dadosImpressaoTotais(){
    
        Map<String, Object> map = new LinkedHashMap<>();     
        map.put("Valor Produto: ", "R$ " + getPedido().getValorProdutos());
        map.put("Valor Serviço: ", "R$ " + getPedido().getValorServicos());
        map.put("Valor Desconto: ", "R$ " + getPedido().getValorDesconto());
        map.put("Valor Total: ", "R$ " + getPedido().getValorTotal());
        return map;
    }    
}
