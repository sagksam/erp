/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe que possui diversos métodos para conversões de tipo 
 * @author bianca
 */
public class Conversores {
    
    public static String dateToString(Date date){
    
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateToStr = dateFormat.format(date);
        return dateToStr;        
    }
    
    public static String randomAlphaNumeric(int count) {
      StringBuilder builder = new StringBuilder();
      String alphaNumericString = "abcdefghijklmnopqrstuvwxyz0123456789";
      while (count-- != 0) {
      int character = (int)(Math.random()*alphaNumericString.length());
        builder.append(alphaNumericString.charAt(character));
      }
      return builder.toString();
    }
}
