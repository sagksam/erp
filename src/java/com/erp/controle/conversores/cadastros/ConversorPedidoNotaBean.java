/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.conversores.cadastros;

import com.erp.acesso.dados.GenericDAO;
import com.erp.controle.cadastros.NotaProdutoBean;
import com.erp.controle.cadastros.NotaServicoBean;
import com.erp.controle.classes.comuns.BaseBean;
import com.erp.modelo.cadastros.NotaProduto;
import com.erp.modelo.cadastros.NotaProdutoProdutos;
import com.erp.modelo.cadastros.NotaServico;
import com.erp.modelo.cadastros.NotaServicoServicos;
import com.erp.modelo.cadastros.Pedido;
import com.erp.modelo.cadastros.PedidoProdutos;
import com.erp.modelo.cadastros.PedidoServicos;
import com.erp.util.JPAUtil;
import com.erp.util.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Paulo
 */
@ManagedBean
@SessionScoped
public class ConversorPedidoNotaBean implements Serializable{
    
    private Pedido pedidoSelecionado;
    private List<Pedido> pedidosSelecionados;
    private NotaProduto notaProduto;
    private NotaServico notaServico;
    private String status;

    //Getters e setters
    public Pedido getPedidoSelecionado() {
        return pedidoSelecionado;
    }

    public void setPedidoSelecionado(Pedido pedidoSelecionado) {
        this.pedidoSelecionado = pedidoSelecionado;
    }

    public List<Pedido> getPedidosSelecionados() {
        return pedidosSelecionados;
    }

    public void setPedidosSelecionados(List<Pedido> pedidosSelecionados) {
        this.pedidosSelecionados = pedidosSelecionados;
    }

    public NotaProduto getNotaProduto() {
        return notaProduto;
    }

    public void setNotaProduto(NotaProduto notaProduto) {
        this.notaProduto = notaProduto;
    }

    public NotaServico getNotaServico() {
        return notaServico;
    }

    public void setNotaServico(NotaServico notaServico) {
        this.notaServico = notaServico;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //MÉTODO QUE FAZ A CONVERSÃO DE PEDIDO DE VENDA EM NOTA DE PRODUTO
    public void pedidoParaNotaProduto(){
    
        try{
            for(Pedido pedido: getPedidosSelecionados()){
            
                notaProduto = new NotaProduto();
                getNotaProduto().setFilial(pedido.getFilial());
                List<NotaProduto> notasProdutos = new NotaProdutoBean().getNotasProdutos();
                for(int i = notasProdutos.size()-1; i >= 0; i--){         
                  if(notasProdutos.get(i).getFilial().getCnpj().equals(getNotaProduto().getFilial().getCnpj())){               
                    getNotaProduto().setNotaFiscal(notasProdutos.get(i).getNotaFiscal() + 1);
                  }              
                }
                getNotaProduto().setDataEmissao(new Date());
                getNotaProduto().setHoraEmissao(new Date());
                getNotaProduto().setEmpresa(pedido.getEmpresa());
                getNotaProduto().setPessoa(pedido.getPessoa());
                getNotaProduto().setDescricao(pedido.getDescricao());
                getNotaProduto().setCep(pedido.getCep());
                getNotaProduto().setEndereco(pedido.getEndereco());
                getNotaProduto().setNumero(pedido.getNumero());
                getNotaProduto().setComplemento(pedido.getComplemento());
                getNotaProduto().setBairro(pedido.getBairro());
                getNotaProduto().setCidade(pedido.getCidade());
                getNotaProduto().setIbge(pedido.getIbge());
                getNotaProduto().setSiafi(pedido.getSiafi());
                getNotaProduto().setEstado(pedido.getEstado());
                getNotaProduto().setPais(pedido.getPais());
                getNotaProduto().setTelefone(pedido.getTelefone());
                getNotaProduto().setCelular(pedido.getCelular());
                getNotaProduto().setEmail(pedido.getEmail());
                getNotaProduto().setValorProdutos(pedido.getValorProdutos());
                getNotaProduto().setValorDesconto(pedido.getValorDesconto());
                getNotaProduto().setBaseIPI(pedido.getBaseIPI());
                getNotaProduto().setValorIPI(pedido.getValorIPI());
                getNotaProduto().setBaseICMS(pedido.getBaseICMS());
                getNotaProduto().setValorICMS(pedido.getValorICMS());
                getNotaProduto().setBaseICMSST(pedido.getBaseICMSST());
                getNotaProduto().setValorICMSST(pedido.getValorICMSST());
                getNotaProduto().setValorFrete(pedido.getValorFrete());
                getNotaProduto().setValorSeguro(pedido.getValorSeguro());
                getNotaProduto().setPrazoPagamento(pedido.getPrazoPagamento());
                getNotaProduto().setParcelas(pedido.getParcelas());
                getNotaProduto().setValorParcela(pedido.getValorParcela());
                getNotaProduto().setCreditoICMS(getNotaProduto().getFilial().getCreditoICMS());
                getNotaProduto().setValorTotal(pedido.getValorTotal());
                getNotaProduto().setContaBancaria(pedido.getContaBancaria());
                getNotaProduto().setFormaPagamento(pedido.getFormaPagamento());
                getNotaProduto().setNotaProdutoProdutos(new ArrayList<NotaProdutoProdutos>());
                for(PedidoProdutos pedidoProdutos: pedido.getPedidosProdutos()){
                
                    NotaProdutoProdutos notaProdutoProdutos = new NotaProdutoProdutos();
                    notaProdutoProdutos.setProduto(pedidoProdutos.getProduto());
                    notaProdutoProdutos.setCST(0);
                    notaProdutoProdutos.setReferencia(pedidoProdutos.getReferencia());
                    notaProdutoProdutos.setDescricaoProduto(pedidoProdutos.getDescricaoProduto());
                    notaProdutoProdutos.setUnidade(pedidoProdutos.getUnidade());
                    notaProdutoProdutos.setQuantidade(pedidoProdutos.getQuantidade());
                    notaProdutoProdutos.setValorUnitario(pedidoProdutos.getValorUnitario());
                    notaProdutoProdutos.setValorProduto(pedidoProdutos.getValorProduto());
                    notaProdutoProdutos.setDesconto(pedidoProdutos.getDesconto());
                    notaProdutoProdutos.setValorDesconto(pedidoProdutos.getValorDesconto());
                    notaProdutoProdutos.setMargemIPI(pedidoProdutos.getMargemIPI());
                    notaProdutoProdutos.setBaseIPI(pedidoProdutos.getBaseIPI());
                    notaProdutoProdutos.setAliquotaIPI(pedidoProdutos.getAliquotaIPI());
                    notaProdutoProdutos.setValorIPI(pedidoProdutos.getValorIPI());
                    notaProdutoProdutos.setMargemICMS(pedidoProdutos.getMargemICMS());
                    notaProdutoProdutos.setBaseICMS(pedidoProdutos.getBaseICMS());
                    notaProdutoProdutos.setAliquotaICMS(pedidoProdutos.getAliquotaICMS());
                    notaProdutoProdutos.setValorICMS(pedidoProdutos.getValorICMS());
                    notaProdutoProdutos.setMargemICMSST(pedidoProdutos.getMargemICMSST());
                    notaProdutoProdutos.setBaseICMSST(pedidoProdutos.getBaseICMSST());
                    notaProdutoProdutos.setAliquotaICMSST(pedidoProdutos.getAliquotaICMSST());
                    notaProdutoProdutos.setValorICMSST(pedidoProdutos.getValorICMSST());
                    notaProdutoProdutos.setValorFrete(pedidoProdutos.getValorFrete());
                    notaProdutoProdutos.setValorSeguro(pedidoProdutos.getValorSeguro());
                    notaProdutoProdutos.setOutrasDespesas(pedidoProdutos.getOutrasDespesas());
                    notaProdutoProdutos.setValorTotal(pedidoProdutos.getValorTotal());
                    notaProdutoProdutos.setNotaProduto(getNotaProduto());
                    getNotaProduto().getNotaProdutoProdutos().add(notaProdutoProdutos);
                   
                }
                new BaseBean().salvar(getNotaProduto());
                if(getNotaProduto().getId() > 0){
                    RequestContext.getCurrentInstance().execute("PF('dialog').show();");
                }
            }
        }
        catch(Exception e){
        
            new Log().salvaErroLog(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));

        }
    }
    //MÉTODO QUE FAZ A CONVERSÃO DE PEDIDO DE VENDA EM NOTA DE SERVICO
    public void pedidoParaNotaServico(){
        System.out.println(getPedidosSelecionados());
        try{
            for(Pedido pedido: getPedidosSelecionados()){
            
                notaServico = new NotaServico();
                getNotaServico().setFilial(pedido.getFilial());
                List<NotaServico> notasServicos = new NotaServicoBean().getNotasServicos();
                for(int i = notasServicos.size()-1; i >= 0; i--){            
                  if(notasServicos.get(i).getFilial().getCnpj().equals(getNotaServico().getFilial().getCnpj())){
                
                    getNotaServico().setRps(notasServicos.get(i).getRps() + 1);
                    getNotaServico().setNotaFiscal(notasServicos.get(i).getNotaFiscal() + 1);
                  }
                    
                }
                getNotaServico().setDataEmissao(new Date());
                getNotaServico().setStatus("Digitada");
                getNotaServico().setEmpresa(pedido.getEmpresa());
                getNotaServico().setPessoa(pedido.getPessoa());
                getNotaServico().setDescricao(pedido.getDescricao());
                getNotaServico().setCep(pedido.getCep());
                getNotaServico().setEndereco(pedido.getEndereco());
                getNotaServico().setNumero(pedido.getNumero());
                getNotaServico().setComplemento(pedido.getComplemento());
                getNotaServico().setBairro(pedido.getBairro());
                getNotaServico().setCidade(pedido.getCidade());
                getNotaServico().setIbge(pedido.getIbge());
                getNotaServico().setSiafi(pedido.getSiafi());
                getNotaServico().setEstado(pedido.getEstado());
                getNotaServico().setPais(pedido.getPais());
                getNotaServico().setEmail(pedido.getEmail());
                getNotaServico().setTelefone(pedido.getTelefone());
                getNotaServico().setCelular(pedido.getCelular());
                getNotaServico().setValorServicos(pedido.getValorServicos());
                getNotaServico().setValorDesconto(pedido.getValorDesconto());
                //IMPOSTOS TOTAIS
                getNotaServico().setPrazoPagamento(pedido.getPrazoPagamento());
                getNotaServico().setParcelas(pedido.getParcelas());
                getNotaServico().setValorParcela(pedido.getValorParcela());
                getNotaServico().setValorTotal(pedido.getValorTotal());
                getNotaServico().setContaBancaria(pedido.getContaBancaria());
                getNotaServico().setFormaPagamento(pedido.getFormaPagamento());
                getNotaServico().setValorTotal(pedido.getValorTotal());
                getNotaServico().setNotaServicoServicos(new ArrayList<NotaServicoServicos>());
                for(PedidoServicos pedidoServicos: pedido.getPedidosServicos()){
                
                    NotaServicoServicos notaServicoServicos = new NotaServicoServicos();
                    notaServicoServicos.setServico(pedidoServicos.getServico());
                    notaServicoServicos.setDescricaoServico(pedidoServicos.getDescricaoServico());
                    notaServicoServicos.setUnidade(pedidoServicos.getUnidade());
                    notaServicoServicos.setQuantidade(pedidoServicos.getQuantidade());
                    notaServicoServicos.setValorUnitario(pedidoServicos.getValorUnitario());
                    notaServicoServicos.setValorServico(pedidoServicos.getValorServico());
                    notaServicoServicos.setDesconto(pedidoServicos.getDesconto());
                    notaServicoServicos.setValorDesconto(pedidoServicos.getValorDesconto());
                    notaServicoServicos.setValorTotal(pedidoServicos.getValorTotal());
                    notaServicoServicos.setNotaServico(getNotaServico());
                    getNotaServico().getNotaServicoServicos().add(notaServicoServicos);
                    
                }
                new BaseBean().salvar(getNotaServico());
                
            }
            if(getNotaServico().getId() > 0){
               RequestContext.getCurrentInstance().execute("PF('dialog').show();");

            }
        }
        catch(Exception e){
        
            new Log().salvaErroLog(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));

        }
    }
    
    public void atualizarStatusPedido(){
      
        EntityManager manager = JPAUtil.getEntityManager();
        for(Pedido pedido: getPedidosSelecionados()){
        
            try{
                pedido.setStatus("FATURADO");
                new GenericDAO().update(pedido);
                
            }
            catch(Exception e){
            
                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
        
            }
            finally{
            
                manager.close();
            }
        }
    }
    
    public void atualizarStatusPedidoCompra(){
    
        EntityManager manager = JPAUtil.getEntityManager();
        for(Pedido pedido: getPedidosSelecionados()){
        
            try{
                pedido.setStatus(getStatus());
                new GenericDAO().update(pedido);
                
            }
            catch(Exception e){
            
                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
        
            }
            finally{
                manager.close();
            }
        }
    }
}
