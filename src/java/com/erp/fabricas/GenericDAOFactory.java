/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.fabricas;

import com.erp.acesso.dados.GenericDAO;

/**
 *
 * @author Paulo
 */
public class GenericDAOFactory {
    
    /*Fábrica de DAO*/
    public GenericDAO getDAO(){   
        return new GenericDAO();
    }
}
