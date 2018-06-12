/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.cadastros;

import com.erp.acesso.dados.GenericDAO;
import com.erp.controle.classes.comuns.BaseBean;
import com.erp.controle.classes.comuns.ListaBean;
import com.erp.modelo.cadastros.Conta;
import com.erp.modelo.cadastros.Estoque;
import com.erp.modelo.cadastros.EstoqueProdutos;
import com.erp.modelo.cadastros.Pedido;
import com.erp.modelo.cadastros.PedidoProdutos;
import com.erp.modelo.cadastros.PedidoServicos;
import com.erp.modelo.cadastros.Produto;
import com.erp.modelo.cadastros.Servico;
import com.erp.modelo.classes.comuns.Lista;
import com.erp.modelo.classes.comuns.Lista.PrazoPagamento;
import com.erp.util.Log;
import com.erp.util.Prazos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Hibernate;

/**
 *
 * @author PauloHenrique
 */
@ManagedBean
@SessionScoped
public class PedidoBean extends BaseBean implements Serializable {
    
    private Pedido pedido;
    private List<Pedido> pedidos;
    private PedidoServicos pedidoServicos;
    private Servico servico;
    private PedidoProdutos pedidoProdutos;
    private Produto produto;
    
    //Método que inicia os campos da classe
    @PostConstruct
    public void init() {

        pedido = new Pedido();
        pedidos = new ArrayList<>();
        servico = new Servico();
        pedidoServicos = new PedidoServicos();
        produto = new Produto();
        pedidoProdutos = new PedidoProdutos();
    }

    //Getters e Setters
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    public List<Pedido> getPedidos(){
        return this.pedidos;
    }
    
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public PedidoServicos getPedidoServicos() {
        return pedidoServicos;
    }

    public void setPedidoServicos(PedidoServicos pedidoServicos) {
        this.pedidoServicos = pedidoServicos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public PedidoProdutos getPedidoProdutos() {
        return pedidoProdutos;
    }

    public void setPedidoProdutos(PedidoProdutos pedidoProdutos) {
        this.pedidoProdutos = pedidoProdutos;
    }

    //Método que adiciona um produto ao Pedido
    public String addProduto() {

        if (getPedido().getPedidosProdutos() == null || getPedidoProdutos() == null) {
            getPedido().setPedidosProdutos(new ArrayList<PedidoProdutos>());
            setPedidoProdutos(new PedidoProdutos());
        }
        if (!isModoEdicao()) {
            getPedidoProdutos().setPedido(getPedido());
            getPedidoProdutos().setProduto(getProduto());
            getPedidoProdutos().setDescricaoProduto(getProduto().getDescricao());
            getPedido().getPedidosProdutos().add(getPedidoProdutos());
        }
        setModoEdicao(false);
        configurarValores();
        selecionarParcelas();
        produto = new Produto();
        pedidoProdutos = new PedidoProdutos();
        return getCadastro();
    }

    //Método que remove um produto do Pedido
    public String removeProduto(PedidoProdutos pedidoProduto) {

        if (pedidoProduto.getId() > 0) {
            getPedido().getPedidosProdutos().remove(pedidoProduto);
            try {
                System.out.println("ENTROU NO LOOP DO DELETE...");
                new GenericDAO().delete(pedidoProduto);
            } 
            catch (Exception e) {
                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
            }
        } 
        else {
            getPedido().getPedidosProdutos().remove(pedidoProduto);
        }
        configurarValores();
        selecionarParcelas();
        return getCadastro();
    }

    //Método que adiciona um servico do Pedido
    public String addServico() {

        if (getPedido().getPedidosServicos() == null) {
            getPedido().setPedidosServicos(new ArrayList<PedidoServicos>());
        }
        if (!isModoEdicao()) {
            getPedidoServicos().setPedido(getPedido());
            getPedidoServicos().setServico(getServico());
            getPedidoServicos().setDescricaoServico(getServico().getDescricao());
            getPedido().getPedidosServicos().add(getPedidoServicos());
        }
        setModoEdicao(false);
        configurarValores();
        selecionarParcelas();
        servico = new Servico();
        pedidoServicos = new PedidoServicos();
        return getCadastro();
    }

    //Método que remove um servico do Pedido
    public String removeServico(PedidoServicos pedidoServico) {

        if (pedidoServico.getId() > 0) {
            getPedido().getPedidosServicos().remove(pedidoServico);
            try {
                pedidoServico.setPedido(null);
                new GenericDAO().delete(pedidoServico);
            } 
            catch (Exception e) {

                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
            }
        } 
        else {
            getPedido().getPedidosServicos().remove(pedidoServico);
        }
        configurarValores();
        selecionarParcelas();
        return getCadastro();
    }

    //Método para configurar a selecao de uma empresa no Pedido
    public void selecionarEmpresa() {

        getPedido().setPessoa(null);
        getPedido().setCep(getPedido().getEmpresa().getCep());
        getPedido().setEndereco(getPedido().getEmpresa().getEndereco());
        getPedido().setNumero(getPedido().getEmpresa().getNumero());
        getPedido().setComplemento(getPedido().getEmpresa().getComplemento());
        getPedido().setCidade(getPedido().getEmpresa().getCidade());
        getPedido().setBairro(getPedido().getEmpresa().getBairro());
        getPedido().setIbge(getPedido().getEmpresa().getIbge());
        getPedido().setSiafi(getPedido().getEmpresa().getSiafi());
        getPedido().setEstado(getPedido().getEmpresa().getEstado());
        getPedido().setPais(getPedido().getEmpresa().getPais());
        getPedido().setEmail(getPedido().getEmpresa().getEmail());

    }

    //Método para configurar a selecao de uma pessoa no Pedido
    public void selecionarPessoa() {

        getPedido().setEmpresa(null);
        getPedido().setCep(getPedido().getPessoa().getCep());
        getPedido().setEndereco(getPedido().getPessoa().getEndereco());
        getPedido().setNumero(getPedido().getPessoa().getNumero());
        getPedido().setComplemento(getPedido().getPessoa().getComplemento());
        getPedido().setBairro(getPedido().getPessoa().getBairro());
        getPedido().setCidade(getPedido().getPessoa().getCidade());
        getPedido().setIbge(getPedido().getPessoa().getIbge());
        getPedido().setSiafi(getPedido().getPessoa().getSiafi());
        getPedido().setEstado(getPedido().getPessoa().getEstado());
        getPedido().setPais(getPedido().getPessoa().getPais());
        getPedido().setEmail(getPedido().getPessoa().getEmail());
    }

    //Método para configurar a referencia do produto ao seleciona-lo no Pedido   
    public void selecionarProduto() {

        getPedidoProdutos().setReferencia(getProduto().getReferencia());
        getPedidoProdutos().setUnidade(getProduto().getUnidade());
        getPedidoProdutos().setEstoqueAtual(getProduto().getEstoqueAtual());
    }

    //Método para configurar a unidade do servico ao seleciona-lo no Pedido
    public void selecionarServico() {

        getPedidoServicos().setUnidade(getServico().getUnidade());
    }

    //Método que configura as parcelas e o valor das parcelas no Pedido
    public void selecionarParcelas() {

        for (PrazoPagamento prazo : new ListaBean().getPrazoPagamento()) {
            if (getPedido().getPrazoPagamento().equals(prazo.toString())) {
                getPedido().setParcelas(prazo.getParcelas());
                getPedido().setValorParcela(getPedido().getValorTotal() / prazo.getParcelas());
            }
        }
    }

    //Método para configurar os valores finais que sao trazidos dos itens do Pedido
    public void configurarValores() {

        double valorProdutos = 0;
        double valorServicos = 0;
        double valorDesconto = 0;
        double baseIPI = 0;
        double valorIPI = 0;
        double baseICMS = 0;
        double valorICMS = 0;
        double baseICMSST = 0;
        double valorICMSST = 0;
        double valorFrete = 0;
        double valorSeguro = 0;
        double valorTotal = 0;

        if(getPedido().getPedidosProdutos() != null){
            try {
                for (PedidoProdutos c : getPedido().getPedidosProdutos()) {

                    valorProdutos += c.getValorProduto();
                    valorDesconto += c.getValorDesconto();
                    baseIPI += c.getBaseIPI();
                    valorIPI += c.getValorIPI();
                    baseICMS += c.getBaseICMS();
                    valorICMS += c.getValorICMS();
                    baseICMSST += c.getBaseICMSST();
                    valorICMSST += c.getValorICMSST();
                    valorFrete += c.getValorFrete();
                    valorSeguro += c.getValorSeguro();
                    valorTotal += c.getValorTotal();
                }
            } 
            catch (Exception e) {
                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
            }
        }

        if(getPedido().getPedidosServicos() != null){
            try {
                for (PedidoServicos c : getPedido().getPedidosServicos()) {
                    valorServicos += c.getValorServico();
                    valorDesconto += c.getValorDesconto();
                    valorTotal += c.getValorTotal();
                }
            } 
            catch (Exception e) {
                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
            }
        }
        getPedido().setValorProdutos(valorProdutos);
        getPedido().setValorServicos(valorServicos);
        getPedido().setValorDesconto(valorDesconto);
        getPedido().setBaseIPI(baseIPI);
        getPedido().setValorIPI(valorIPI);
        getPedido().setBaseICMS(baseICMS);
        getPedido().setValorICMS(valorICMS);
        getPedido().setBaseICMSST(baseICMSST);
        getPedido().setValorICMSST(valorICMSST);
        getPedido().setValorFrete(valorFrete);
        getPedido().setValorSeguro(valorSeguro);
        getPedido().setValorTotal(valorTotal);
    }
    
     @Override
    public boolean geraConta() {

        boolean geraConta = false;
        for (Lista.CadastroStatus status : new ListaBean().getCadastroStatus()) {
            if (getPedido().getStatus().equals(status.toString())) {
                geraConta = status.isGeraConta();
            }
        }

        for (Conta conta : (getPedido().getTipo().equals("Compra")) ? new ContaPagarBean().getContas() : new ContaReceberBean().getContas()) {
            if (conta.getPedido() == getPedido().getId()) {
                conta.setAtivo(false);
                try {
                    getDao().update(conta);
                } catch (Exception e) {
                    new Log().salvaErroLog(e);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
                }
            }
        }

        if (geraConta && getPedido().isAtivo()) {

            int parcelas = getPedido().getParcelas();
            Date[] datas = new Prazos().prazosDatas(getPedido().getDataEmissao(), getPedido().getPrazoPagamento(), parcelas);
            for (int i = 1; i <= parcelas; i++) {
                Conta conta = new Conta();
                conta.setAtivo(true);
                if(getPedido().getTipo().equals("Compra"))
                  conta.setTipo("Pagar");
                else
                  conta.setTipo("Receber");
                conta.setFilial(getPedido().getFilial());
                conta.setEmpresa(getPedido().getEmpresa());
                conta.setPessoa(getPedido().getPessoa());
                conta.setDescricao(getPedido().getDescricao());
                conta.setContaBancaria(getPedido().getContaBancaria());
                conta.setParcela(i + " de " + parcelas);
                conta.setPedido(getPedido().getId());
                conta.setNotaFiscal(0);
                conta.setFormaPagamento(getPedido().getFormaPagamento());
                conta.setStatus("Pendente");
                conta.setDataEmissao(new Date());
                conta.setDataVencimento(datas[i - 1]);
                conta.setDataLimite(datas[i - 1]);
                conta.setValorPrevisto(getPedido().getValorParcela());
                conta.setDataPagamento(null);            
                conta.setAuditoria(getAuditoria(conta));
                try {
                    getDao().save(conta);
                } catch (Exception e) {

                    new Log().salvaErroLog(e);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
                }
            }
        }
        return geraConta;
    }
    
    @Override
    public boolean geraMovimentacaoEstoque(){
    
        boolean geraMovimentacao = false;
        Produto produto; 
        for (Lista.CadastroStatus status : new ListaBean().getCadastroStatus()) {
            if (getPedido().getStatus().equals(status.toString())) {
                geraMovimentacao = status.isGeraMovimentacaoEstoque();
            }
        }
        /*DELETANDO A MOVIMENTAÇÃO DE ESTOQUE*/
        for (Estoque estoque : (getPedido().getTipo().equals("Compra")) ? new EstoqueEntradaBean().getEstoques() : new EstoqueSaidaBean().getEstoques()) {
            if (estoque.getPedido() == getPedido().getId()) {
                estoque.setAtivo(false);
                for(EstoqueProdutos estoqueProdutos: estoque.getEstoqueProdutos()){
                
                    if(estoqueProdutos.getProduto().isControleEstoque()){  
                        produto = getDao().findById(estoqueProdutos.getProduto().getId(), Produto.class);
                        if(getPedido().getTipo().equals("Compra"))
                          produto.setEstoqueAtual(produto.getEstoqueAtual() - estoqueProdutos.getQuantidade());
                        else
                          produto.setEstoqueAtual(produto.getEstoqueAtual() + estoqueProdutos.getQuantidade());  
                        try {
                            getDao().update(produto);
                        } catch (Exception e) {

                            new Log().salvaErroLog(e);
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
                        }
                    }
                }
                try {
                    getDao().update(estoque);
                } catch (Exception e) {
                    new Log().salvaErroLog(e);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
                }
            }
        }
        /*GERANDO A MOVIMENTAÇÃO*/
        if(geraMovimentacao && getPedido().isAtivo()){
            
            Estoque estoque = new Estoque();
            estoque.setAtivo(true);
            if(getPedido().getTipo().equalsIgnoreCase("Compra")){
              estoque.setTipo("Entrada");
              estoque.setMotivo(Lista.EstoqueMotivo.COMPRA.getValor());
            }  
            else{
              estoque.setTipo("Saída");
              estoque.setMotivo(Lista.EstoqueMotivo.VENDA.getValor());
            }  
            estoque.setFilial(getPedido().getFilial());
            estoque.setData(getPedido().getDataEmissao());
            estoque.setResponsavel(getPedido().getAuditoria().getAlteracaoUsuario().getNome());
            estoque.setInventario(0);
            estoque.setPedido(getPedido().getId());
            estoque.setNotaProduto(0);         
            estoque.setAuditoria(getAuditoria(estoque));
            try {
                getDao().save(estoque);
            } 
            catch (Exception e) {

                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
            }
            for(PedidoProdutos pedidoProdutos: getPedido().getPedidosProdutos()){
            
                if(pedidoProdutos.getProduto().isControleEstoque()){
                    EstoqueProdutos estoqueProdutos = new EstoqueProdutos();
                    produto = getDao().findById(pedidoProdutos.getProduto().getId(), Produto.class);
                    estoqueProdutos.setAtivo(true);
                    estoqueProdutos.setProduto(pedidoProdutos.getProduto());
                    estoqueProdutos.setDescricao(pedidoProdutos.getDescricaoProduto());
                    estoqueProdutos.setReferencia(pedidoProdutos.getReferencia());
                    estoqueProdutos.setUnidade(pedidoProdutos.getUnidade());
                    estoqueProdutos.setEstoqueAtual(pedidoProdutos.getEstoqueAtual());
                    estoqueProdutos.setQuantidade(pedidoProdutos.getQuantidade());
                    if(getPedido().getTipo().equals("Compra"))
                      estoqueProdutos.setSaldo(produto.getEstoqueAtual() + pedidoProdutos.getQuantidade()); 
                    else
                      estoqueProdutos.setSaldo(produto.getEstoqueAtual() - pedidoProdutos.getQuantidade());
                    estoqueProdutos.setValorUnitario(pedidoProdutos.getValorUnitario());
                    estoqueProdutos.setValorTotal(pedidoProdutos.getValorTotal());
                    estoqueProdutos.setEstoque(estoque);
                    try {
                        getDao().save(estoqueProdutos);
                    } catch (Exception e) {

                        new Log().salvaErroLog(e);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
                    }
                    produto.setEstoqueAtual(estoqueProdutos.getSaldo());
                    try {
                        getDao().update(produto);
                    } 
                    catch (Exception e) {

                        new Log().salvaErroLog(e);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
                    }
                }
            }
        }
        return geraMovimentacao;
    }


    @Override
    public String novo() {

        pedido = new Pedido();
        servico = new Servico();
        return getCadastro();
    }

}
