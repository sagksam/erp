/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Writer;

/**
 *
 * @author bianca
 */
public class Log {
    
    //Metodo que salva em um arquivo txt a captura de uma excecao gerada
    public String salvaErroLog(Exception e){
    
        FileWriter log = null;
        Writer writer = null;  
        PrintWriter pWriter = null;
        try {
          // log = new FileWriter("/Users/bianca/Documents/log.txt", true);
           log = new FileWriter("D:\\erp\\modelo\\log\\log.txt", true);
           writer = new BufferedWriter(log);
           pWriter = new PrintWriter(log, true);
           e.printStackTrace(pWriter);
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }       
        return "Um erro ocorreu, entre em conto com o adminstrador";
    }
}