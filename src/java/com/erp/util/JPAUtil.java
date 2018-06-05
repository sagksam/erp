/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author PauloHenrique
 */
public class JPAUtil {
    
    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("default");
    
    public static EntityManager getEntityManager(){
        
        return FACTORY.createEntityManager();
    }
    
}
