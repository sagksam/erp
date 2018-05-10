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
import com.erp.modelo.cadastros.NotaProduto;
import com.erp.modelo.cadastros.NotaProdutoProdutos;
import com.erp.modelo.cadastros.Produto;
import com.erp.modelo.classes.comuns.Lista;
import com.erp.modelo.classes.comuns.Lista.CFOP;
import com.erp.util.JPAUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.erp.modelo.classes.comuns.Auditoria;
import com.erp.modelo.classes.comuns.Lista.NotaProdutoStatus;
import com.erp.util.Conversores;
import com.erp.util.Log;
import com.erp.util.Prazos;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
/**
 *
 * @author PauloHenrique
 */
@ManagedBean(name="notaProdutoBean")
@SessionScoped
public class NotaProdutoBean extends BaseBean implements Serializable{
    
    private NotaProduto notaProduto;
    private List<NotaProduto> notasProdutos;
    private NotaProdutoProdutos notaProdutoProdutos;
    private Produto produto;

    //Método que inicia os campos da classe
    @PostConstruct
    public void init(){
        
        this.notaProduto = new NotaProduto();
        this.notasProdutos = new ArrayList<>();
        this.notaProdutoProdutos = new NotaProdutoProdutos();
        this.produto = new Produto();
    }

    public NotaProduto getNotaProduto() {
        return notaProduto;
    }

    public void setNotaProduto(NotaProduto notaProduto) {
        this.notaProduto = notaProduto;
    }

    public List<NotaProduto> getNotasProdutos() {
        EntityManager manager = null;
        try {
            manager = JPAUtil.getEntityManager();
            Query query = manager.createNamedQuery("notasProdutos")
                    .setParameter("ativo", true);
            this.notasProdutos = query.getResultList();
            return this.notasProdutos;
        } 
        catch (Exception e) {
            return new ArrayList<>();
        } 
        finally {

            manager.close();
        }
    }

    public void setNotasProdutos(List<NotaProduto> notasProdutos) {
        this.notasProdutos = notasProdutos;
    }

    public NotaProdutoProdutos getNotaProdutoProdutos() {
        return notaProdutoProdutos;
    }

    public void setNotaProdutoProdutos(NotaProdutoProdutos notaProdutoProdutos) {
        this.notaProdutoProdutos = notaProdutoProdutos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    //Método que adiciona um produto ao Pedido
    public String addProduto(){
        
        if(getNotaProduto().getNotaProdutoProdutos() == null)
            getNotaProduto().setNotaProdutoProdutos(new ArrayList<NotaProdutoProdutos>());
        if(!isModoEdicao()){
          getNotaProdutoProdutos().setNotaProduto(getNotaProduto());
          getNotaProdutoProdutos().setProduto(getProduto());
          getNotaProdutoProdutos().setDescricaoProduto(getProduto().getDescricao());
          getNotaProduto().getNotaProdutoProdutos().add(getNotaProdutoProdutos());
        }
        setModoEdicao(false);
        configurarValores();
        selecionarParcelas();
        produto = new Produto();
        notaProdutoProdutos = new NotaProdutoProdutos(); 
        return getCadastro();     
    }
    
    //Método que remove um produto do Pedido
    public String removeProduto(NotaProdutoProdutos notaProduto){

        if (notaProduto.getId() > 0) {
            getNotaProduto().getNotaProdutoProdutos().remove(notaProduto);
            try {
                new GenericDAO().delete(notaProduto);
            } 
            catch (Exception e) {
                
                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR ,"Mensagem: ", "Um erro ocorreu, entre em contato com o administrador"));
            }
        } 
        else {
            getNotaProduto().getNotaProdutoProdutos().remove(notaProduto);
        }
        configurarValores();
        selecionarParcelas();
        return getCadastro();
    }
    
     //Método para configurar a selecao de uma empresa no Pedido
    public void selecionarEmpresa(){
    
        getNotaProduto().setPessoa(null);
        getNotaProduto().setCep(getNotaProduto().getEmpresa().getCep());
        getNotaProduto().setEndereco(getNotaProduto().getEmpresa().getEndereco());
        getNotaProduto().setNumero(getNotaProduto().getEmpresa().getNumero());
        getNotaProduto().setComplemento(getNotaProduto().getEmpresa().getComplemento());
        getNotaProduto().setCidade(getNotaProduto().getEmpresa().getCidade());
        getNotaProduto().setBairro(getNotaProduto().getEmpresa().getBairro());
        getNotaProduto().setIbge(getNotaProduto().getEmpresa().getIbge());
        getNotaProduto().setSiafi(getNotaProduto().getEmpresa().getSiafi());
        getNotaProduto().setEstado(getNotaProduto().getEmpresa().getEstado());
        getNotaProduto().setPais(getNotaProduto().getEmpresa().getPais());
        getNotaProduto().setEmail(getNotaProduto().getEmpresa().getEmail());
        
    }
    
    //Método para configurar a selecao de uma pessoa no Pedido
    public void selecionarPessoa(){
    
        getNotaProduto().setEmpresa(null);
        getNotaProduto().setCep(getNotaProduto().getPessoa().getCep());
        getNotaProduto().setEndereco(getNotaProduto().getPessoa().getEndereco());
        getNotaProduto().setNumero(getNotaProduto().getPessoa().getNumero());
        getNotaProduto().setComplemento(getNotaProduto().getPessoa().getComplemento());
        getNotaProduto().setBairro(getNotaProduto().getPessoa().getBairro());
        getNotaProduto().setCidade(getNotaProduto().getPessoa().getCidade());
        getNotaProduto().setIbge(getNotaProduto().getPessoa().getIbge());
        getNotaProduto().setSiafi(getNotaProduto().getPessoa().getSiafi());
        getNotaProduto().setEstado(getNotaProduto().getPessoa().getEstado());
        getNotaProduto().setPais(getNotaProduto().getPessoa().getPais());
        getNotaProduto().setEmail(getNotaProduto().getPessoa().getEmail());
    }
    
    //Método para configurar a referencia do produto ao seleciona-lo no Pedido   
    public void selecionarProduto(){
    
       getNotaProdutoProdutos().setReferencia(getProduto().getReferencia());
    }
    
    //Método que configura as parcelas e o valor das parcelas no Pedido
    public void selecionarParcelas(){
    
        for(Lista.PrazoPagamento prazo: new ListaBean().getPrazoPagamento())
            if(getNotaProduto().getPrazoPagamento().equals(prazo.toString())){            
                getNotaProduto().setParcelas(prazo.getParcelas()); 
                getNotaProduto().setValorParcela(getNotaProduto().getValorTotal() / prazo.getParcelas());
            }    
    }
    
    //Método que configura os campos Nota Fiscal e as aliquotas vindas da Filial
    public void selecionarAliquotasFiliais(){
    
        int NotaFiscal = 1;
        try{
            
            for(int i = getNotasProdutos().size()-1; i >= 0; i--){            
                if(getNotasProdutos().get(i).getFilial().getCnpj().equals(getNotaProduto().getFilial().getCnpj())){               
                    NotaFiscal = getNotasProdutos().get(i).getNotaFiscal() + 1;
                }
                    
            }
        }
        catch(Exception e){
        
            new Log().salvaErroLog(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR ,"Mensagem: ", "Um erro ocorreu, entre em contato com o administrador"));
        }
        getNotaProduto().setNotaFiscal(NotaFiscal);
        getNotaProduto().setCreditoICMS(getNotaProduto().getFilial().getCreditoICMS());
       
    }
    
    //Método que configura as alíquotas de acordo com a class fiscal
    public void selecionarClassFiscal(){
    
        
    }
    
    //Método que configura a descricao do CFOP de acordo com o CFOP
    public void selecionarCFOP(){
    
        for(CFOP cfop: new ListaBean().getCFOP()){
            if(getNotaProdutoProdutos().getCFOP().equals(cfop.toString())){
                getNotaProdutoProdutos().setDescricaoCFOP(cfop.getDescricao());
            }       
            
        }
    }
       
    //Método que configura a selecao da empresa transportadora
    public void selecionarEmpresaTransporte(){
    
        getNotaProduto().setPessoaTransportadora(null);
        getNotaProduto().setDescricaoTransportadora(getNotaProduto().getEmpresaTransportadora().getRazaoSocial());
    }
    
    //Método que configura a selecao da pessoa transportadora
    public void selecionarPessoaTransporte(){
    
        getNotaProduto().setEmpresaTransportadora(null);
        getNotaProduto().setDescricaoTransportadora(getNotaProduto().getPessoaTransportadora().getNomeCompleto());
    
    }
    
    //Método para configurar os valores finais que sao trazidos dos itens do Pedido
    public void configurarValores(){
    
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
        String naturezaOperacao = "";
        try{
            for (NotaProdutoProdutos c : getNotaProduto().getNotaProdutoProdutos()) {

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
                naturezaOperacao += " " + c.getDescricaoCFOP();
            }
        }
        catch(Exception e){
            new Log().salvaErroLog(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR ,"Mensagem: ", "Um erro ocorreu, entre em contato com o administrador"));
        }
        getNotaProduto().setValorProdutos(valorProdutos);
        getNotaProduto().setValorServicos(valorServicos);
        getNotaProduto().setValorDesconto(valorDesconto);
        getNotaProduto().setBaseIPI(baseIPI);
        getNotaProduto().setValorIPI(valorIPI);
        getNotaProduto().setBaseICMS(baseICMS);
        getNotaProduto().setValorICMS(valorICMS);
        getNotaProduto().setBaseICMSST(baseICMSST);
        getNotaProduto().setValorICMSST(valorICMSST);
        getNotaProduto().setValorFrete(valorFrete);
        getNotaProduto().setValorSeguro(valorSeguro);
        getNotaProduto().setValorTotal(valorTotal);
        getNotaProduto().setNaturezaOperacao(naturezaOperacao);
    }
    
    //MÉTODO QUE FAZ A EMISSÃO DA NOTA FISCAL ELETRÔNICA
    public void solicitarNota(){
    
        try{
            if (getNotaProduto().getStatus().equals(NotaProdutoStatus.DIGITADA.toString())) {
                long longTemp1 = (long) (Math.random() * 100000000000000L);
                long longTemp2 = (long) (Math.random() * 100000000000000L);
                long longTemp3 = (long) (Math.random() * 100000000000000L);
                int intTemp4 = 1 + new Random().nextInt(9) * 2;
                getNotaProduto().setStatus(NotaProdutoStatus.AUTORIZADA.toString());
                getNotaProduto().setChaveAcesso(String.valueOf(longTemp1) + String.valueOf(longTemp2) + String.valueOf(longTemp3) + String.valueOf(intTemp4));
                longTemp1 = (long) (Math.random() * 1000000000000000L);
                getNotaProduto().setProtocoloAutorizacao(String.valueOf(longTemp1));
                getNotaProduto().setDataAutorizacao(new Date());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem: ", "Nota autorizada com sucesso!"));
                salvar(getNotaProduto());
            }
            else if (getNotaProduto().getStatus().equals(NotaProdutoStatus.AUTORIZADA.toString())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem: ", "Essa nota já está autorizada"));
            } 
            else if (getNotaProduto().getStatus().equals(NotaProdutoStatus.CANCELADA.toString())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem: ", "Essa nota foi cancelada e não pode ser autorizada"));
            }
        }
        catch(Exception e){
            new Log().salvaErroLog(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR ,"Mensagem: ", "Um erro ocorreu, entre em conto com o adminstrador"));
        }
    }
    
    //MÉTODO QUE FAZ O CANCELAMENTO DA NOTA FISCAL ELETRÔNICA
    public void cancelarNota(){
        try{
            if(getNotaProduto().getStatus().equals(NotaProdutoStatus.AUTORIZADA.toString())){
                getNotaProduto().setStatus(NotaProdutoStatus.CANCELADA.toString());
                long longTemp1 = (long) (Math.random() * 1000000000000000L);
                getNotaProduto().setProtocoloCancelamento(String.valueOf(longTemp1));
                getNotaProduto().setDataCancelamento(new Date());
                salvar(getNotaProduto());
                RequestContext.getCurrentInstance().execute("PF('cancelamentoDialog').hide();");
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Mensagem: ", "A nota não está autorizada portanto não pode ser cancelada"));
            }
        }
        catch(Exception e){
        
            new Log().salvaErroLog(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR ,"Mensagem: ", "Um erro ocorreu, entre em contato com o administrador"));
        }
    }
    
    @Override
    public boolean geraConta(){
    
        boolean geraConta = false;
        double valorTotal = 0;
        for(Lista.NotaProdutoStatus status: new ListaBean().getNotasProdutosStatus()){  
            if(getNotaProduto().getStatus().equals(status.toString())){           
                geraConta = status.isGeraConta();
            }
        }
        for(Conta conta: new ContaReceberBean().getContas()){           
            if(conta.getNotaProduto()==getNotaProduto().getId()){
                conta.setAtivo(false);
                try{
                    new GenericDAO().update(conta);
                }
                catch(Exception e){                   
                    new Log().salvaErroLog(e);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR ,"Mensagem: ", "Um erro ocorreu, entre em conto com o adminstrador"));
                }
            }    
        }
        for(NotaProdutoProdutos notaProdutoProduto: getNotaProduto().getNotaProdutoProdutos()){
            for(Lista.CFOP cfop: new ListaBean().getCFOP()){           
                if(notaProdutoProduto.getCFOP().equals(cfop.toString())){                
                    if(cfop.isGeraConta()){                    
                        valorTotal += notaProdutoProduto.getValorTotal();
                    }
                }
            }
        }
        if(geraConta && valorTotal > 0){
            
            int parcelas = getNotaProduto().getParcelas();
            double valorParcela = valorTotal / parcelas;
            Date[] datas = new Prazos().prazosDatas(getNotaProduto().getDataEmissao(), getNotaProduto().getPrazoPagamento(), parcelas);            
            for(int i = 1; i <= parcelas; i++){ 
              Conta conta = new Conta();
              conta.setAtivo(true);
              conta.setTipo("Receber");
              conta.setFilial(getNotaProduto().getFilial());
              conta.setEmpresa(getNotaProduto().getEmpresa());
              conta.setPessoa(getNotaProduto().getPessoa());
              conta.setDescricao(getNotaProduto().getDescricao());
              conta.setContaBancaria(getNotaProduto().getContaBancaria());
              conta.setParcela(i + " de " + parcelas);
              conta.setPedido(0);
              conta.setNotaProduto(getNotaProduto().getId());
              conta.setNotaFiscal(getNotaProduto().getNotaFiscal());
              conta.setFormaPagamento(getNotaProduto().getFormaPagamento());
              conta.setStatus("Pendente");
              conta.setDataEmissao(new Date());
              conta.setDataVencimento(datas[i-1]);
              conta.setDataLimite(datas[i-1]);
              conta.setValorPrevisto(valorParcela);
              conta.setDataPagamento(null);
              Auditoria auditoria = new Auditoria();
              auditoria.setCriacaoUsuario(new BaseBean().getUsuario());
              auditoria.setCriacaoData(new Date());
              auditoria.setAlteracaoData(new Date());
              auditoria.setAlteracaoUsuario(new BaseBean().getUsuario());
              conta.setAuditoria(auditoria);
              try{
                new GenericDAO().save(conta);
              }
              catch(Exception e){
              
                  new Log().salvaErroLog(e);
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR ,"Mensagem: ", "Um erro ocorreu, entre em contato com o administrador"));
              }
            }
            
        }
        return geraConta;
    }
    
    @Override
    public String novo(){
    
        notaProduto = new NotaProduto();
        produto = new Produto();
        return getCadastro();
    }
    
    //Método para abrir o pedido de compra ou venda de acordo com o tipo do pedido
    @Override
    public String toString(){
      
       return "notaproduto";
    }
    
    @Override
    public String getNomeCadastro(){
    
        return "Notas Produtos";
    }
    
    @Override
    public String getDescricaoCadastro(){
    
        return "Cadastre e gerencie aqui as notas de produtos de sua empresa "
                + "sejam elas notas de saída, notas de entrada ou notas de devoluções.";
    }
    
    @Override
    public String getDescricaoRelatorio(){
    
        return "Acompanhe aqui as notas de produtos de sua empresa " 
                + "filtre também essas notas por data de emissão ou status";
    }

    //Método que retorna os dados que serão exibidos no impresso 
    @Override
    public Map<String, Object> dadosImpressao(){
    
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ID: ", getNotaProduto().getId());
        map.put("Nota Fiscal: ", getNotaProduto().getNotaFiscal());
        map.put("Filial: ", getNotaProduto().getFilial());
        map.put("Descrição: ", getNotaProduto().getDescricao());
        map.put("Emissão: ", Conversores.dateToString(getNotaProduto().getDataEmissao()));
        map.put("Cep: ", getNotaProduto().getCep());
        map.put("Endereço: ", getNotaProduto().getEndereco());        
        map.put("Número: ", getNotaProduto().getNumero());
        map.put("Bairro: ", getNotaProduto().getBairro());
        map.put("Cidade: ", getNotaProduto().getCidade());
        map.put("Estado: ", getNotaProduto().getEstado());
        return map;
    }
    
    //Método que retorna os dados dos produtos que serão exibidos no impresso 
    @Override
    public List dadosImpressaoProdutos(){
    
        List<Map> listMap = new ArrayList<>();
        for(NotaProdutoProdutos notaProdutoProduto: getNotaProduto().getNotaProdutoProdutos()){                  
          Map<String, Object> map = new LinkedHashMap<>();
          map.put("Referência:", notaProdutoProduto.getReferencia());
          map.put("Produto:", notaProdutoProduto.getDescricaoProduto());
          map.put("Quantidade:", notaProdutoProduto.getQuantidade());
          map.put("Valor Unitário:", notaProdutoProduto.getValorUnitario());
          map.put("Valor Produto:", "R$ " + notaProdutoProduto.getValorProduto());
          map.put("Valor Desconto", "R$ " + notaProdutoProduto.getValorDesconto());
          map.put("Valor Total:", "R$ " + notaProdutoProduto.getValorTotal());
          listMap.add(map);
        }  
        return listMap;
    }
    
    //Método que retorna os dados totais que serão exibidos no impresso 
    @Override
    public Map<String, Object> dadosImpressaoTotais(){
    
        Map<String, Object> map = new LinkedHashMap<>();     
        map.put("Valor Produto: ", "R$ " + getNotaProduto().getValorProdutos());
        map.put("Valor Desconto: ", "R$ " + getNotaProduto().getValorDesconto());
        map.put("Valor Total: ", "R$ " + getNotaProduto().getValorTotal());
        return map;
    }    
}
