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
import com.erp.modelo.cadastros.NotaServico;
import com.erp.modelo.cadastros.NotaServicoServicos;
import com.erp.modelo.cadastros.Servico;
import com.erp.modelo.classes.comuns.Auditoria;
import com.erp.modelo.classes.comuns.Lista;
import com.erp.modelo.classes.comuns.Lista.Atividade;
import com.erp.modelo.classes.comuns.Lista.NotaServicoStatus;
import com.erp.modelo.classes.comuns.Lista.PrazoPagamento;
import com.erp.util.Conversores;
import com.erp.util.JPAUtil;
import com.erp.util.Log;
import com.erp.util.Prazos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.primefaces.context.RequestContext;

/**
 *
 * @author PauloHenrique
 */
@ManagedBean
@SessionScoped
public class NotaServicoBean extends BaseBean implements Serializable {

    private NotaServico notaServico;
    private List<NotaServico> notasServicos;
    private NotaServicoServicos notaServicoServicos;
    private Servico servico;

    //Método que inicia os campos da classe
    @PostConstruct
    public void init() {

        notaServico = new NotaServico();
        notasServicos = new ArrayList<>();
        servico = new Servico();
        notaServicoServicos = new NotaServicoServicos();
    }

    //Getters e Setters
    public NotaServico getNotaServico() {
        return notaServico;
    }

    public void setNotaServico(NotaServico notaServico) {
        this.notaServico = notaServico;
    }

    public List<NotaServico> getNotasServicos() {
        EntityManager manager = null;
        try {
            manager = JPAUtil.getEntityManager();
            Query query = manager.createNamedQuery("notasServicos")
                    .setParameter("ativo", true);
            this.notasServicos = query.getResultList();
            return this.notasServicos;
        } 
        catch (Exception e) {
            return new ArrayList<>();
        } 
        finally {

            manager.close();
        }
    }

    public void setNotasServicos(List<NotaServico> notasServicos) {
        this.notasServicos = notasServicos;
    }

    public NotaServicoServicos getNotaServicoServicos() {

        return notaServicoServicos;
    }

    public void setNotaServicoServicos(NotaServicoServicos notaServicoServicos) {
        this.notaServicoServicos = notaServicoServicos;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
    
    //Método que adiciona um servico da Nota de Servico
    public String addServico() {
        try{
        if (getNotaServico().getNotaServicoServicos() == null) {
            getNotaServico().setNotaServicoServicos(new ArrayList<NotaServicoServicos>());
        }
        if (!isModoEdicao()) {
            getNotaServicoServicos().setNotaServico(getNotaServico());
            getNotaServicoServicos().setServico(getServico());
            getNotaServicoServicos().setDescricaoServico(getServico().getDescricao());
            getNotaServico().getNotaServicoServicos().add(getNotaServicoServicos());
        }
        setModoEdicao(false);
        configurarValores();
        selecionarParcelas();
        servico = new Servico();
        notaServicoServicos = new NotaServicoServicos();
        return getCadastro();
        }
        catch(Exception e){
        
            new Log().salvaErroLog(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em conto com o adminstrador"));
        }
        
        return null;
    }

    //Método que remove um servico da Nota de Servico
    public String removeServico(NotaServicoServicos notaServicoServicos) {

        if (notaServicoServicos.getId() > 0) {
            getNotaServico().getNotaServicoServicos().remove(notaServicoServicos);
            try {
                new GenericDAO().delete(notaServicoServicos);
            } 
            catch (Exception e) {

                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em conto com o adminstrador"));
            }
        } 
        else {
            getNotaServico().getNotaServicoServicos().remove(notaServicoServicos);
        }
        configurarValores();
        selecionarParcelas();
        return getCadastro();
    }

    //Método para configurar a selecao de uma empresa na Nota de Servico
    public void selecionarEmpresa() {

        getNotaServico().setPessoa(null);
        getNotaServico().setCep(getNotaServico().getEmpresa().getCep());
        getNotaServico().setEndereco(getNotaServico().getEmpresa().getEndereco());
        getNotaServico().setNumero(getNotaServico().getEmpresa().getNumero());
        getNotaServico().setComplemento(getNotaServico().getEmpresa().getComplemento());
        getNotaServico().setCidade(getNotaServico().getEmpresa().getCidade());
        getNotaServico().setBairro(getNotaServico().getEmpresa().getBairro());
        getNotaServico().setIbge(getNotaServico().getEmpresa().getIbge());
        getNotaServico().setSiafi(getNotaServico().getEmpresa().getSiafi());
        getNotaServico().setEstado(getNotaServico().getEmpresa().getEstado());
        getNotaServico().setPais(getNotaServico().getEmpresa().getPais());
        getNotaServico().setEmail(getNotaServico().getEmpresa().getEmail());

    }

    //Método para configurar a selecao de uma pessoa na Nota de Servico
    public void selecionarPessoa() {

        getNotaServico().setEmpresa(null);
        getNotaServico().setCep(getNotaServico().getPessoa().getCep());
        getNotaServico().setEndereco(getNotaServico().getPessoa().getEndereco());
        getNotaServico().setNumero(getNotaServico().getPessoa().getNumero());
        getNotaServico().setComplemento(getNotaServico().getPessoa().getComplemento());
        getNotaServico().setBairro(getNotaServico().getPessoa().getBairro());
        getNotaServico().setCidade(getNotaServico().getPessoa().getCidade());
        getNotaServico().setIbge(getNotaServico().getPessoa().getIbge());
        getNotaServico().setSiafi(getNotaServico().getPessoa().getSiafi());
        getNotaServico().setEstado(getNotaServico().getPessoa().getEstado());
        getNotaServico().setPais(getNotaServico().getPessoa().getPais());
        getNotaServico().setEmail(getNotaServico().getPessoa().getEmail());
    }

    //Método para configurar a unidade do servico ao seleciona-lo na Nota de Servico
    public void selecionarServico() {

        getNotaServicoServicos().setUnidade(getServico().getUnidade());
    }

    //Método que configura as parcelas e o valor das parcelas na Nota de Servico
    public void selecionarParcelas() {

        for (PrazoPagamento prazo : new ListaBean().getPrazoPagamento()) {
            if (getNotaServico().getPrazoPagamento().equals(prazo.toString())) {
                getNotaServico().setParcelas(prazo.getParcelas());
                getNotaServico().setValorParcela(getNotaServico().getValorTotal() / prazo.getParcelas());
            }
        }
    }

    //Método que configura a descrição da atividade
    public void selecionarAtividade() {

        for (Atividade atividade : new ListaBean().getAtividade()) {
            if (getNotaServico().getAtividade().equals(atividade.toString())) {
                getNotaServico().setDescricaoAtividade(atividade.getDescricao());
            }

        }

    }

    //Método que configura os campos de Nota Fiscal e RPS e as aliquotas vindas da filial
    public void selecionarAliquotasFilial() {

        int RPS = 1;
        int NotaFiscal = 1;
        try {

            for (int i = getNotasServicos().size() - 1; i >= 0; i--) {
                if (getNotasServicos().get(i).getFilial().getCnpj().equals(getNotaServico().getFilial().getCnpj())) {

                    RPS = getNotasServicos().get(i).getRps() + 1;
                    NotaFiscal = getNotasServicos().get(i).getNotaFiscal() + 1;
                }

            }
        } 
        catch (Exception e) {

            new Log().salvaErroLog(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em conto com o adminstrador"));
        }
        getNotaServico().setRps(RPS);
        getNotaServico().setNotaFiscal(NotaFiscal);
        getNotaServico().setAliquotaCOFINS(getNotaServico().getFilial().getAliquotaCOFINS());
        getNotaServico().setAliquotaCSLL(getNotaServico().getFilial().getAliquotaCSLL());
        getNotaServico().setAliquotaINSS(getNotaServico().getFilial().getAliquotaINSS());
        getNotaServico().setAliquotaIR(getNotaServico().getFilial().getAliquotaIR());
        getNotaServico().setAliquotaISSQN(getNotaServico().getFilial().getAliquotaISSQN());
        getNotaServico().setAliquotaPIS(getNotaServico().getFilial().getAliquotaPIS());
    }

    //Método para configurar os valores finais que sao trazidos dos itens da Nota de Servico
    public void configurarValores() {

        double valorServicos = 0;
        double valorDesconto = 0;
        double valorTotal = 0;
        double baseISSQN;
        double valorISSQN;
        double valorPIS;
        double valorCOFINS;
        double valorCSLL;
        double valorIR;
        double valorINSS;
        try {
            for (NotaServicoServicos c : getNotaServico().getNotaServicoServicos()) {
                valorServicos += c.getValorServico();
                valorDesconto += c.getValorDesconto();
                valorTotal += c.getValorTotal();
                
            }
        } 
        catch (Exception e) {
            
            new Log().salvaErroLog(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em conto com o adminstrador"));
        }
        getNotaServico().setValorServicos(valorServicos);
        getNotaServico().setValorDesconto(valorDesconto);
        getNotaServico().setValorTotal(valorTotal);
        baseISSQN = getNotaServico().getValorServicos();
        valorISSQN = baseISSQN * (getNotaServico().getAliquotaISSQN() / 100);
        valorPIS =  getNotaServico().getValorServicos() * (getNotaServico().getAliquotaPIS() / 100);
        valorCOFINS =  getNotaServico().getValorServicos() * (getNotaServico().getAliquotaCOFINS() / 100);
        valorCSLL =  getNotaServico().getValorServicos() * (getNotaServico().getAliquotaCSLL() / 100);
        valorIR =  getNotaServico().getValorServicos() * (getNotaServico().getAliquotaIR() / 100);
        valorINSS =  getNotaServico().getValorServicos() * (getNotaServico().getAliquotaINSS() / 100);
        getNotaServico().setBaseISSQN(baseISSQN);
        getNotaServico().setValorISSQN(valorISSQN);
        getNotaServico().setValorPIS(valorPIS);
        getNotaServico().setValorCOFINS(valorCOFINS);
        getNotaServico().setValorCSLL(valorCSLL);
        getNotaServico().setValorIR(valorIR);
        getNotaServico().setValorINSS(valorINSS);
        
    }
    
    //MÉTODO QUE FAZ A EMISSÃO DA NOTA FISCAL ELETRÔNICA DE SERVICO
    public void solicitarNota(){
    
        try{
            if (getNotaServico().getStatus().equals(NotaServicoStatus.DIGITADA.toString())) {
                    long longTemp1 = (long) (Math.random() * 100000000L);
                    getNotaServico().setStatus(NotaServicoStatus.AUTORIZADA.toString());
                    getNotaServico().setLoteAutorizacao(String.valueOf(longTemp1));
                    getNotaServico().setDataAutorizacao(new Date());
                    getNotaServico().setVerificacaoAutorizacao(Conversores.randomAlphaNumeric(40));
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO ,"Mensagem: ", "Nota autorizada com sucesso!"));
                    salvar(getNotaServico());
            }
            else if (getNotaServico().getStatus().equals(NotaServicoStatus.AUTORIZADA.toString())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem: ", "Essa nota já está autorizada"));
            } 
            else if (getNotaServico().getStatus().equals(NotaServicoStatus.CANCELADA.toString())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem: ", "Essa nota foi cancelada e não pode ser autorizada"));
            }
        }
        catch(Exception e){
            new Log().salvaErroLog(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR ,"Mensagem: ", "Um erro ocorreu, entre em conto com o adminstrador"));
        }
    }
    
    //MÉTODO QUE FAZ O CANCELAMENTO DA NOTA FISCAL ELETRÔNICA DE SERVICO
    public void cancelarNota(){
        try{
            if(getNotaServico().getStatus().equals(NotaServicoStatus.AUTORIZADA.toString())){
                getNotaServico().setStatus(NotaServicoStatus.CANCELADA.toString());
                getNotaServico().setDataCancelamento(new Date());
                getNotaServico().setVerificacaoCancelamento(Conversores.randomAlphaNumeric(40));
                salvar(getNotaServico());
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
    public boolean geraConta() {

        boolean geraConta = false;
        for (Lista.NotaServicoStatus status : new ListaBean().getNotasServicosStatus()) {
            if (getNotaServico().getStatus().equals(status.toString())) {
                geraConta = status.isGeraConta();
            }
        }
        for (Conta conta : new ContaReceberBean().getContas()) {
            if (conta.getNotaServico() == getNotaServico().getId()) {
                conta.setAtivo(false);
                try {
                    new GenericDAO().update(conta);
                } catch (Exception e) {
                    new Log().salvaErroLog(e);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o administrador"));
                }                
            }
        }
        if (geraConta && getNotaServico().isAtivo()) {

            int parcelas = getNotaServico().getParcelas();
            Date[] datas = new Prazos().prazosDatas(getNotaServico().getDataEmissao(), getNotaServico().getPrazoPagamento(), parcelas);
            for (int i = 1; i <= parcelas; i++) {
                Conta conta = new Conta();
                conta.setAtivo(true);
                conta.setTipo("Receber");
                conta.setFilial(getNotaServico().getFilial());
                conta.setEmpresa(getNotaServico().getEmpresa());
                conta.setPessoa(getNotaServico().getPessoa());
                conta.setDescricao(getNotaServico().getDescricao());
                conta.setContaBancaria(getNotaServico().getContaBancaria());
                conta.setParcela(i + " de " + parcelas);
                conta.setPedido(0);
                conta.setNotaServico(getNotaServico().getId());
                conta.setNotaFiscal(getNotaServico().getNotaFiscal());
                conta.setFormaPagamento(getNotaServico().getFormaPagamento());
                conta.setStatus("Pendente");
                conta.setDataEmissao(new Date());
                conta.setDataVencimento(datas[i - 1]);
                conta.setDataLimite(datas[i - 1]);
                conta.setValorPrevisto(getNotaServico().getValorParcela());
                conta.setDataPagamento(null);               
                conta.setAuditoria(getAuditoria(conta));
                try {
                    new GenericDAO().save(conta);
                } catch (Exception e) {

                    new Log().salvaErroLog(e);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o administrador"));
                }
            }

        }
        return geraConta;
    }

    @Override
    public String novo() {

        notaServico = new NotaServico();
        servico = new Servico();
        return getCadastro();
    }

    @Override
    public String toString() {
        return "notaservico";
    }

    @Override
    public String getNomeCadastro() {

        return "Notas Serviços";
    }

    @Override
    public String getDescricaoCadastro() {

        return "Cadastre e gerencie aqui as notas de serviço de sua empresa, "
                + "emitindo notas diretamente com a prefeitura de sua cidade.";
    }

    @Override
    public String getDescricaoRelatorio() {

        return "Acompanhe aqui as notas de serviços de sua empresa "
                + "filtre também essas notas por data de emissão e status";
    }
    
    //Método que retorna os dados que serão exibidos no impresso 
    @Override
    public Map<String, Object> dadosImpressao(){
    
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("ID: ", getNotaServico().getId());
        map.put("Nota Fiscal: ", getNotaServico().getNotaFiscal());
        map.put("RPS: ", getNotaServico().getRps());        
        map.put("Filial: ", getNotaServico().getFilial());
        map.put("Descrição: ", getNotaServico().getDescricao());
        map.put("Emissão: ", Conversores.dateToString(getNotaServico().getDataEmissao()));
        map.put("Cep: ", getNotaServico().getCep());
        map.put("Endereço: ", getNotaServico().getEndereco());        
        map.put("Número: ", getNotaServico().getNumero());
        map.put("Bairro: ", getNotaServico().getBairro());
        map.put("Cidade: ", getNotaServico().getCidade());
        map.put("Estado: ", getNotaServico().getEstado());
        return map;
    }

    //Método que retorna os dados dos servicos que serão exibidos no impresso 
    @Override
    public List dadosImpressaoServicos(){
    
        List<Map> listMap = new ArrayList<>();
        for(NotaServicoServicos notaServicoServicos: getNotaServico().getNotaServicoServicos()){                  
          Map<String, Object> map = new LinkedHashMap<>();
          map.put("Serviço:", notaServicoServicos.getDescricaoServico());
          map.put("Quantidade:", notaServicoServicos.getQuantidade());
          map.put("Valor Unitário:", notaServicoServicos.getValorUnitario());
          map.put("Valor Serviço:", "R$ " + notaServicoServicos.getValorServico());
          map.put("Valor Desconto", "R$ " + notaServicoServicos.getValorDesconto());
          map.put("Valor Total:", "R$ " + notaServicoServicos.getValorTotal());
          listMap.add(map);
        }  
        return listMap;
    }
    
    //Método que retorna os dados totais que serão exibidos no impresso 
    @Override
    public Map<String, Object> dadosImpressaoTotais(){
    
        Map<String, Object> map = new LinkedHashMap<>();     
        map.put("Valor Serviço: ", "R$ " + getNotaServico().getValorServicos());
        map.put("Valor Desconto: ", "R$ " + getNotaServico().getValorDesconto());
        map.put("Valor Total: ", "R$ " + getNotaServico().getValorTotal());
        return map;
    }
}
