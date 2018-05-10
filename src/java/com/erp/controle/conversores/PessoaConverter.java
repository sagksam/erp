/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.conversores;

import com.erp.controle.servicos.PessoaService;
import com.erp.modelo.cadastros.Pessoa;
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
@FacesConverter(forClass=Pessoa.class)
public class PessoaConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
    
        if(value != null && value.trim().length() > 0) {
            try {
                
                PessoaService service = (PessoaService) facesContext.getExternalContext().getApplicationMap().get("pessoaService");
                for(Pessoa pessoa: service.getPessoas()){                
                    if(pessoa.getId() == Integer.parseInt(value))
                        return pessoa;
                }                
                return service.getPessoas().get(0);
            } catch(NumberFormatException e) {
                new Log().salvaErroLog(e);
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem:", "Não é uma pessoa válida."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
    
        if(value != null) {
            return String.valueOf(((Pessoa) value).getId());
        }
        else {
            return null;
        }
    
    } 
}
