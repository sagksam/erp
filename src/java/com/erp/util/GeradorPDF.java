/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;



/**
 *
 * @author Paulo
 */
public class GeradorPDF {
    
    public void converterPDF(String docCaminho, String pdfCaminho){

        try {
            System.out.println("Criando PDF...");
            InputStream doc = new FileInputStream(new File(docCaminho));
            XWPFDocument document = new XWPFDocument(doc);
            PdfOptions options = PdfOptions.create();
            OutputStream out = new FileOutputStream(new File(pdfCaminho));
            PdfConverter.getInstance().convert(document, out, options); 
            System.out.println("PDF CRIADO!");
        } 
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        } 

   
    }
}
