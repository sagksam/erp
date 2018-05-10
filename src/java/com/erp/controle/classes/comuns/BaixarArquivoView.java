/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.classes.comuns;

import com.erp.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Paulo
 */
@ManagedBean
public class BaixarArquivoView {
    
    private StreamedContent arquivo;
    
    public BaixarArquivoView(){
        try{
          InputStream stream = new FileInputStream("D:\\erp\\modelo\\temp\\documento.pdf");
          arquivo = new DefaultStreamedContent(stream, "application/pdf", "impresso.pdf");

        }
        catch(FileNotFoundException e){        
            new Log().salvaErroLog(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
        }
        finally{

            File docx = new File("D:\\erp\\modelo\\temp\\documento.docx");
            File pdf = new File("D:\\erp\\modelo\\temp\\documento.pdf");
            docx.delete();
            pdf.delete();
        }
    }

    public StreamedContent getArquivo() {
        return arquivo;
    }
    
    
}
