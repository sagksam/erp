/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.conversores;

import com.erp.controle.servicos.ProdutoService;
import com.erp.modelo.cadastros.Produto;
import com.erp.util.Log;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author PauloHenrique
 */
@FacesConverter(forClass=Produto.class)
public class ProdutoConverter implements Converter{
    
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
    
        if(value != null && value.trim().length() > 0) {
            try {
                
                ProdutoService service = (ProdutoService) facesContext.getExternalContext().getApplicationMap().get("produtoService");
                for(Produto produto: service.getProdutos()){                
                    if(produto.getId() == Integer.parseInt(value))
                        return produto;
                }
                return service.getProdutos().get(0);
            } catch(NumberFormatException e) {
                new Log().salvaErroLog(e);
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem:", "Não é um produto válido."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
    
        if(value != null) {
            return String.valueOf(((Produto) value).getId());
        }
        else {
            return null;
        }
    
    } 
}
