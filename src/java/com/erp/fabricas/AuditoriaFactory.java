/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.fabricas;

import com.erp.controle.classes.comuns.BaseBean;
import com.erp.modelo.classes.comuns.Auditoria;
import java.util.Date;

/**
 *
 * @author Paulo
 */
public class AuditoriaFactory {
    
    private BaseBean baseBean;
    
    /*Fábrica de Auditoria*/
    public  Auditoria getAuditoria(){  
        baseBean = new BaseBean();
        return new Auditoria(baseBean.getUsuario(), new Date(), baseBean.getUsuario(), new Date());
    }
}
