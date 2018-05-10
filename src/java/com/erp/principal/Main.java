/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.principal;

import com.erp.acesso.dados.GenericDAO;
import com.erp.controle.cadastros.EmpresaBean;
import com.erp.controle.classes.comuns.BaseBean;
import com.erp.controle.classes.comuns.ListaBean;
import com.erp.modelo.cadastros.Empresa;
import com.erp.modelo.cadastros.NotaProdutoProdutos;
import com.erp.modelo.cadastros.Produto;
import com.erp.modelo.cadastros.Usuario;
import com.erp.modelo.classes.comuns.Auditoria;
import com.erp.modelo.classes.comuns.Lista;
import com.erp.util.Conversores;
import com.erp.util.JPAUtil;
import com.erp.util.Log;
import com.erp.util.Prazos;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.Query;



/**
 *
 * @author bianca
 */
public class Main {
    
    public static void main(String[] args) throws Exception{
     
        long temp = (long) (Math.random() * 1000000000000000L);
        long longTemp2 = (long) (Math.random() * 100000000000000L);
        long longTemp3 = (long) (Math.random() * 100000000000000L);
        int intTemp4 = 1 + new Random().nextInt(9) * 2;
        System.out.println(String.valueOf(temp));
    }      
}
