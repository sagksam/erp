/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.conversores;

import com.erp.controle.servicos.EmpresaService;
import com.erp.modelo.cadastros.Empresa;
import com.erp.util.Log;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author bianca
 */
@FacesConverter(forClass=Empresa.class)
public class EmpresaConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
    
        if(value != null && value.trim().length() > 0) {
            try {
                
                EmpresaService service = (EmpresaService) facesContext.getExternalContext().getApplicationMap().get("empresaService");
                
                for(Empresa empresa: service.getEmpresas()){
                
                    if(empresa.getId() == Integer.parseInt(value))
                        return empresa;
                }
                return service.getEmpresas().get(0);
                
            } catch(NumberFormatException e) {
                new Log().salvaErroLog(e);
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem:", "Não é uma empresa válida."));

            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
    
        if(value != null) {
            return String.valueOf(((Empresa) value).getId());
        }
        else {
            return null;
        }
    
    } 
}
