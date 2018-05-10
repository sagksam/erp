/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.util;

import com.erp.controle.classes.comuns.ListaBean;
import com.erp.modelo.classes.comuns.Lista;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Paulo
 */
public class Prazos {
    
    private List<Date> feriadosNacionais;

    //Método que retorna uma lista com os feriados nacionais
    public ArrayList<Date> getFeriadosNacionais() {
        Date[] tempFeriados = new Date[9];
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.JANUARY, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        tempFeriados[0] = calendar.getTime();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.APRIL, 21, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        tempFeriados[1] = calendar.getTime();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.MAY, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        tempFeriados[2] = calendar.getTime();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.MAY, 31, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        tempFeriados[3] = calendar.getTime();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.SEPTEMBER, 7, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        tempFeriados[4] = calendar.getTime();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.OCTOBER, 12, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        tempFeriados[5] = calendar.getTime();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.NOVEMBER, 2, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        tempFeriados[6] = calendar.getTime();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.NOVEMBER, 15, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        tempFeriados[7] = calendar.getTime();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.DECEMBER, 25, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        tempFeriados[8] = calendar.getTime();
        return new ArrayList<Date>(Arrays.asList(tempFeriados));
    }

    //Método que define as datas para o pagamento de cada conta
    public Date[] prazosDatas(Date dataEmissao, String prazoPagamento, int parcelas){
    
        String prazo = "";
        List<Integer> quantidades = new ArrayList<>();
        Date[] datas = new Date[parcelas];
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataEmissao);
        
        for(Lista.PrazoPagamento pr: new ListaBean().getPrazoPagamento()){       
            if(prazoPagamento.equals(pr.toString())){            
                prazo = pr.getPrazo();
                if(prazo.equals("Diário")){               
                    for(Integer c: pr.getQuantidades())
                        quantidades.add(c);
                }
            }
        }
        for(int i = 0; i < parcelas; i++){
        
            switch(prazo){
            
                case "Diário":
                    calendar.add(Calendar.DATE, quantidades.get(i));
                    datas[i] = organizaDatas(calendar.getTime());
                    break;
                case "Mensal":
                    calendar.add(Calendar.DATE, 30);
                    datas[i] = organizaDatas(calendar.getTime());
                    break;
                case "Semanal":
                    calendar.add(Calendar.DATE, 7);
                    datas[i] = organizaDatas(calendar.getTime());
                    break;
                case "Quinzenal":
                    calendar.add(Calendar.DATE, 15);
                    datas[i] = organizaDatas(calendar.getTime());
                    break;
            }
        }
        return datas;
    }
    
    //Método que organiza as datas de pagamento caso uma data seja sábado, domingo ou um feriado
    public Date organizaDatas(Date data){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
        if(!getFeriadosNacionais().contains(data) && diaSemana != 7 && diaSemana != 1){
            return data;
        }
        
        while(getFeriadosNacionais().contains(data) || diaSemana == 7 || diaSemana == 1){
            if(getFeriadosNacionais().contains(data)){
            
                calendar.add(Calendar.DATE, 1);
                data = calendar.getTime();
                calendar.setTime(data);
                diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
            }
            if(diaSemana == 7){
            
                calendar.add(Calendar.DATE, 2);
                data = calendar.getTime();
                calendar.setTime(data);
                diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
            }            
            if(diaSemana == 1){
            
                calendar.add(Calendar.DATE, 1);
                data = calendar.getTime();
                calendar.setTime(data);
                diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
            }
        }
        return data;
    }
}
