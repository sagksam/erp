/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.cadastros;

import com.erp.controle.classes.comuns.BaseBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author PauloHenrique
 */
@ManagedBean
@SessionScoped
public class PrincipalBean extends BaseBean implements Serializable{
    
    private BaseBean baseBean;
    private List<BaseBean> basesBeanCadastros;
    private List<BaseBean> basesBeanRelatorios;
    private List<BaseBean> basesBeanFerramentas;
    
    @PostConstruct
    public void init(){
    
        baseBean = new BaseBean();
        basesBeanCadastros = new ArrayList<>();
        basesBeanRelatorios = new ArrayList<>();
        basesBeanFerramentas = new ArrayList<>();
    }

    @Override
    public BaseBean getBaseBean() {
        return baseBean;
    }

    @Override
    public void setBaseBean(BaseBean baseBean) {
        this.baseBean = baseBean;
    }

    public List<BaseBean> getBasesBeanCadastros() {
        
        basesBeanCadastros = new ArrayList<>();
        basesBeanCadastros.add(new FilialBean());
        basesBeanCadastros.add(new EmpresaBean());
        basesBeanCadastros.add(new PessoaBean());
        basesBeanCadastros.add(new ProdutoBean());
        basesBeanCadastros.add(new ServicoBean());
        basesBeanCadastros.add(new OrcamentoBean());
        basesBeanCadastros.add(new PedidoVendaBean());
        basesBeanCadastros.add(new PedidoCompraBean());
        basesBeanCadastros.add(new NotaProdutoBean());
        basesBeanCadastros.add(new NotaServicoBean());
        basesBeanCadastros.add(new ContaReceberBean());
        basesBeanCadastros.add(new ContaPagarBean()); 
        return basesBeanCadastros;
    }

    public void setBasesBean(List<BaseBean> basesBeanCadastros) {
        this.basesBeanCadastros = basesBeanCadastros;
    }

    public List<BaseBean> getBasesBeanRelatorios() {
        basesBeanRelatorios.add(new EmpresaBean());
        basesBeanRelatorios.add(new PessoaBean());
        basesBeanRelatorios.add(new ProdutoBean());
        basesBeanRelatorios.add(new ServicoBean());
        basesBeanRelatorios.add(new OrcamentoBean());
        basesBeanRelatorios.add(new PedidoVendaBean());
        basesBeanRelatorios.add(new PedidoCompraBean());
        basesBeanRelatorios.add(new NotaProdutoBean());
        basesBeanRelatorios.add(new NotaServicoBean());
        basesBeanRelatorios.add(new ContaReceberBean());
        basesBeanRelatorios.add(new ContaPagarBean());         
        return basesBeanRelatorios;
    }

    public void setBasesBeanRelatorios(List<BaseBean> basesBeanRelatorios) {
        this.basesBeanRelatorios = basesBeanRelatorios;
    }

    public List<BaseBean> getBasesBeanFerramentas() {
        basesBeanFerramentas.add(new ContaBancariaBean());
        return basesBeanFerramentas;
    }

    public void setBasesBeanFerramentas(List<BaseBean> basesBeanFerramentas) {
        this.basesBeanFerramentas = basesBeanFerramentas;
    }

}
