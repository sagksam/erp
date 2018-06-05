/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.cadastros;

import com.erp.modelo.cadastros.Estoque;
import com.erp.util.JPAUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Paulo
 */
@ManagedBean
@SessionScoped
public class EstoqueSaidaBean extends EstoqueBean{
    
    @Override
    public List<Estoque> getEstoques(){
    
        EntityManager manager = null;
        try {
            manager = JPAUtil.getEntityManager();
            Query query = manager.createNamedQuery("estoqueSaida")
                    .setParameter("tipo", "saida")
                    .setParameter("ativo", true);
            setEstoques(query.getResultList());
            return super.getEstoques();
        } catch (Exception e) {

            return new ArrayList<>();
        }
        finally{
        
            manager.close();
        }
    }
    
    @Override
    public String toString() {

        return "estoquesaida";
    }

    @Override
    public String getNomeCadastro() {

        return "Saída de Produtos";
    }

    @Override
    public String getDescricaoCadastro() {

        return "Atualize e gerencie o estoque de produtos, informando as saídas de materiais não previstas "
                + "como saídas de produtos sem um pedido ou uma nota fiscal.";
    }
}
