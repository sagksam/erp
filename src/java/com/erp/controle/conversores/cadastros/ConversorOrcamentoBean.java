/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.conversores.cadastros;

import com.erp.acesso.dados.GenericDAO;
import com.erp.controle.classes.comuns.BaseBean;
import com.erp.modelo.cadastros.Orcamento;
import com.erp.modelo.cadastros.OrcamentoProdutos;
import com.erp.modelo.cadastros.OrcamentoServicos;
import com.erp.modelo.cadastros.Pedido;
import com.erp.modelo.cadastros.PedidoProdutos;
import com.erp.modelo.cadastros.PedidoServicos;
import com.erp.util.JPAUtil;
import com.erp.util.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
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
public class ConversorOrcamentoBean implements Serializable{
    
    private Orcamento orcamentoSelecionado;
    private List<Orcamento> orcamentosSelecionados;
    private Pedido pedido;
    
    //Getters e setters
    public Orcamento getOrcamentoSelecionado() {
        return orcamentoSelecionado;
    }

    public void setOrcamentoSelecionado(Orcamento orcamentoSelecionado) {
        this.orcamentoSelecionado = orcamentoSelecionado;
    }

    public List<Orcamento> getOrcamentosSelecionados() {
        return orcamentosSelecionados;
    }

    public void setOrcamentosSelecionados(List<Orcamento> orcamentosSelecionados) {
        this.orcamentosSelecionados = orcamentosSelecionados;
    }    

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    //MÉTODO QUE FAZ A CONVERSÃO DE ORÇAMENTO PARA PEDIDO DE VENDA
    public void orcamentoParaPedido(){
    
        if(getOrcamentosSelecionados().isEmpty()){
        
            RequestContext.getCurrentInstance().execute("PF('dialog2').show();");
        }
        else{
            try {
                for (Orcamento orcamento : getOrcamentosSelecionados()) {

                    pedido = new Pedido();
                    getPedido().setTipo("Venda");
                    getPedido().setFilial(orcamento.getFilial());
                    getPedido().setEmpresa(orcamento.getEmpresa());
                    getPedido().setPessoa(orcamento.getPessoa());
                    getPedido().setDescricao(orcamento.getDescricao());
                    getPedido().setDataEmissao(new Date());
                    getPedido().setStatus("Pendente");
                    getPedido().setCep(orcamento.getCep());
                    getPedido().setEndereco(orcamento.getEndereco());
                    getPedido().setNumero(orcamento.getNumero());
                    getPedido().setComplemento(orcamento.getComplemento());
                    getPedido().setBairro(orcamento.getBairro());
                    getPedido().setCidade(orcamento.getCidade());
                    getPedido().setIbge(orcamento.getIbge());
                    getPedido().setSiafi(orcamento.getSiafi());
                    getPedido().setEstado(orcamento.getEstado());
                    getPedido().setPais(orcamento.getPais());
                    getPedido().setTelefone(orcamento.getTelefone());
                    getPedido().setCelular(orcamento.getCelular());
                    getPedido().setEmail(orcamento.getEmail());
                    getPedido().setValorProdutos(orcamento.getValorProdutos());
                    getPedido().setValorServicos(orcamento.getValorServicos());
                    getPedido().setValorDesconto(orcamento.getValorDesconto());
                    getPedido().setBaseIPI(orcamento.getBaseIPI());
                    getPedido().setValorIPI(orcamento.getValorIPI());
                    getPedido().setBaseICMS(orcamento.getBaseICMS());
                    getPedido().setValorICMS(orcamento.getValorICMS());
                    getPedido().setBaseICMSST(orcamento.getBaseICMSST());
                    getPedido().setValorICMSST(orcamento.getValorICMSST());
                    getPedido().setValorFrete(orcamento.getValorFrete());
                    getPedido().setValorSeguro(orcamento.getValorSeguro());
                    getPedido().setPrazoPagamento(orcamento.getPrazoPagamento());
                    getPedido().setParcelas(orcamento.getParcelas());
                    getPedido().setValorParcela(orcamento.getValorParcela());
                    getPedido().setValorTotal(orcamento.getValorTotal());
                    getPedido().setContaBancaria(orcamento.getContaBancaria());
                    getPedido().setFormaPagamento(orcamento.getFormaPagamento());
                    getPedido().setPedidosProdutos(new ArrayList<PedidoProdutos>());
                    getPedido().setPedidosServicos(new ArrayList<PedidoServicos>());
                    for (OrcamentoProdutos orcamentoProdutos : orcamento.getOrcamentosProdutos()) {

                        PedidoProdutos pedidoProdutos = new PedidoProdutos();
                        pedidoProdutos.setProduto(orcamentoProdutos.getProduto());
                        pedidoProdutos.setReferencia(orcamentoProdutos.getReferencia());
                        pedidoProdutos.setDescricaoProduto(orcamentoProdutos.getDescricaoProduto());
                        pedidoProdutos.setUnidade(orcamentoProdutos.getUnidade());
                        pedidoProdutos.setQuantidade(orcamentoProdutos.getQuantidade());
                        pedidoProdutos.setValorUnitario(orcamentoProdutos.getValorUnitario());
                        pedidoProdutos.setValorProduto(orcamentoProdutos.getValorProduto());
                        pedidoProdutos.setDesconto(orcamentoProdutos.getDesconto());
                        pedidoProdutos.setValorDesconto(orcamentoProdutos.getValorDesconto());
                        pedidoProdutos.setMargemIPI(orcamentoProdutos.getMargemIPI());
                        pedidoProdutos.setBaseIPI(orcamentoProdutos.getBaseIPI());
                        pedidoProdutos.setAliquotaIPI(orcamentoProdutos.getAliquotaIPI());
                        pedidoProdutos.setValorIPI(orcamentoProdutos.getValorIPI());
                        pedidoProdutos.setMargemICMS(orcamentoProdutos.getMargemICMS());
                        pedidoProdutos.setBaseICMS(orcamentoProdutos.getBaseICMS());
                        pedidoProdutos.setAliquotaICMS(orcamentoProdutos.getAliquotaICMS());
                        pedidoProdutos.setValorICMS(orcamentoProdutos.getValorICMS());
                        pedidoProdutos.setMargemICMSST(orcamentoProdutos.getMargemICMSST());
                        pedidoProdutos.setBaseICMSST(orcamentoProdutos.getBaseICMSST());
                        pedidoProdutos.setAliquotaICMSST(orcamentoProdutos.getAliquotaICMSST());
                        pedidoProdutos.setValorICMSST(orcamentoProdutos.getValorICMSST());
                        pedidoProdutos.setValorFrete(orcamentoProdutos.getValorFrete());
                        pedidoProdutos.setValorSeguro(orcamentoProdutos.getValorSeguro());
                        pedidoProdutos.setOutrasDespesas(orcamentoProdutos.getOutrasDespesas());
                        pedidoProdutos.setValorTotal(orcamentoProdutos.getValorTotal());
                        pedidoProdutos.setPedido(getPedido());
                        getPedido().getPedidosProdutos().add(pedidoProdutos);

                    }
                    for (OrcamentoServicos orcamentoServicos : orcamento.getOrcamentosServicos()) {

                        PedidoServicos pedidoServicos = new PedidoServicos();
                        pedidoServicos.setServico(orcamentoServicos.getServico());
                        pedidoServicos.setDescricaoServico(orcamentoServicos.getDescricaoServico());
                        pedidoServicos.setUnidade(orcamentoServicos.getUnidade());
                        pedidoServicos.setQuantidade(orcamentoServicos.getQuantidade());
                        pedidoServicos.setValorUnitario(orcamentoServicos.getValorUnitario());
                        pedidoServicos.setValorServico(orcamentoServicos.getValorServico());
                        pedidoServicos.setDesconto(orcamentoServicos.getDesconto());
                        pedidoServicos.setValorDesconto(orcamentoServicos.getValorDesconto());
                        pedidoServicos.setValorTotal(orcamentoServicos.getValorTotal());
                        pedidoServicos.setPedido(getPedido());
                        getPedido().getPedidosServicos().add(pedidoServicos);

                    }
                    new BaseBean().salvar(getPedido());
                }
                if (getPedido().getId() > 0) {
                    RequestContext.getCurrentInstance().execute("PF('dialog').show();");

                }

            } 
            catch (Exception e) {

                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));

            }
        }
    }
    
    public void atualizarOrcamento(){
      
        EntityManager manager = JPAUtil.getEntityManager();
        for(Orcamento orcamento: getOrcamentosSelecionados()){
        
            try{
                orcamento.setStatus("CONCLUIDO");
                new GenericDAO().update(orcamento);
                
            }
            catch(Exception e){
            
              new Log().salvaErroLog(e);
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));

            }
            finally {
                manager.close();
            }
        }
    }
}
