/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.cadastros;

import com.erp.modelo.cadastros.Conta;
import com.erp.util.Conversores;
import com.erp.util.JPAUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author bianca
 */
@ManagedBean
@SessionScoped
public class ContaPagarBean extends ContaBean implements Serializable {

    @Override
    public List<Conta> getContas() {
        EntityManager manager = null;
        try {
            manager = JPAUtil.getEntityManager();
            Query query = manager.createNamedQuery("contasPagar")
                    .setParameter("tipo", "pagar")
                    .setParameter("ativo", true);
            setContas(query.getResultList());
            return super.getContas();
        } 
        catch (Exception e) {

            return new ArrayList<>();
        }
        finally{
        
            manager.close();
        }
    }

    @Override
    public String toString(){
    
        return "contapagar";
    }
    
    @Override
    public String getNomeCadastro(){
    
        return "Contas a Pagar";
    }
    
    @Override
    public String getDescricaoCadastro(){
    
        return "Gerencie aqui as contas a pagar de sua empresa seja "
                + "cadastrando ou as gerando através de pedidos de compra.";
    }
    
    @Override
    public String getDescricaoRelatorio(){
    
        return "Acompanhe aqui as contas a pagar de sua empresa " 
                + "filtre também essas contas data de emissão filial ou status";
    }
    
    //Método que retorna os dados que serão exibidos no impresso 
    @Override
    public Map<String, Object> dadosImpressao(){
    
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ID: ", getConta().getId());
        map.put("Descrição: ", getConta().getDescricao());
        map.put("Parcela", getConta().getParcela());
        map.put("Pedido: ", getConta().getPedido());
        map.put("Nota Fiscal: ", getConta().getNotaFiscal());
        return map;
    }
    //Método que retorna os dados totais que serão exibidos no impresso 
    @Override
    public Map<String, Object> dadosImpressaoTotais(){
    
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("Forma Pagamento: ", getConta().getFormaPagamento());
        map.put("Status: ", getConta().getStatus());
        map.put("Emissão: ", Conversores.dateToString(getConta().getDataEmissao()));
        map.put("Vencimento: ", Conversores.dateToString(getConta().getDataVencimento()));
        map.put("Valor Previsto", "R$ " + getConta().getValorPrevisto());
        return map;
    }
    
}
