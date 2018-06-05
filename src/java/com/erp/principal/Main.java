/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.principal;

import com.erp.controle.cadastros.EstoqueBean;
import com.erp.controle.cadastros.EstoqueSaidaBean;
import com.erp.modelo.cadastros.Estoque;


/**
 *
 * @author bianca
 */
public class Main {
    
    public static void main(String[] args) throws Exception{
     
        EstoqueSaidaBean estoque = new EstoqueSaidaBean();
        for(Estoque e: estoque.getEstoques()){
        
            System.out.println(e.getId() + " " + e.getTipo());
        }
    }      
}
