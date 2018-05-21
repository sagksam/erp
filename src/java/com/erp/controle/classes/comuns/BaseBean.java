/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.controle.classes.comuns;

import com.erp.acesso.dados.GenericDAO;
import com.erp.modelo.cadastros.Filial;
import com.erp.modelo.cadastros.Usuario;
import com.erp.modelo.classes.comuns.Auditoria;
import com.erp.modelo.classes.comuns.EntidadeBase;
import com.erp.util.GeradorPDF;
import com.erp.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;

/**
 *
 * @author bianca
 */
public class BaseBean implements Serializable {

    private GenericDAO dao;
    private Usuario usuario;
    private Auditoria auditoria;
    private final String CADASTRO = "/cadastros/" + toString() + "?faces-redirect=true";
    private final String VISUALIZACAO_CADASTRO = "/visualizacao-cadastros/" + toString() + "?faces-redirect=true";
    private final String VISUALIZACAO_RELATORIO = "/visualizacao-relatorios/" + toString() + "?faces-redirect=true";
    private final String VISUALIZACAO_FERRAMENTA = "/visualizacao-ferramentas/" + toString() + "?faces-redirect=true";

    private boolean modoEdicao;
    private BaseBean baseBean;

    //Método que retorna um DAO
    public GenericDAO getDao() {

        dao = new GenericDAO();
        return dao;
    }

    //Método que retorna uma represantação String para abrir os cadastros
    public final String getCadastro() {

        return CADASTRO;
    }

    //Método que retorna o Usuário atual
    public Usuario getUsuario() {

        int id = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        usuario = getDao().findById(id, Usuario.class);
        return usuario;
    }

    //Getters e Setters
    public Auditoria getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }

    public boolean isModoEdicao() {
        return modoEdicao;
    }

    public void setModoEdicao(boolean modoEdicao) {
        this.modoEdicao = modoEdicao;
    }

    public BaseBean getBaseBean() {
        return baseBean;
    }

    public void setBaseBean(BaseBean baseBean) {
        this.baseBean = baseBean;
    }
    
    //Método que imprimi o registro atual junto com seus itens
    public void imprimir(Map<String, Object> mapImpresso, List<Map<String, Object>> arrayImpressoProdutos, List<Map<String, Object>> arrayImpressoServicos, Map<String, Object> mapImpressoTotais, String nomeImpresso){
        try {
            XWPFDocument document = new XWPFDocument();
            CTBody body = document.getDocument().getBody();
            if (!body.isSetSectPr()) {
                body.addNewSectPr();
            }
            CTSectPr section = body.getSectPr();
            if (!section.isSetPgSz()) {
                section.addNewPgSz();
            }
            CTPageSz pageSize = section.getPgSz();
            pageSize.setOrient(STPageOrientation.LANDSCAPE);
            pageSize.setW(BigInteger.valueOf(16840));
            pageSize.setH(BigInteger.valueOf(11900));
            FileOutputStream out = new FileOutputStream(new File("D:\\erp\\modelo\\temp\\documento.docx"));
         // FileOutputStream out = new FileOutputStream(new File("/Users/bianca/Documents/documento.docx"));
            XWPFParagraph paragrafoCabecalho = document.createParagraph();
            paragrafoCabecalho.setBorderBottom(Borders.BASIC_BLACK_DASHES);
            paragrafoCabecalho.setBorderLeft(Borders.BASIC_BLACK_DASHES);
            paragrafoCabecalho.setBorderRight(Borders.BASIC_BLACK_DASHES);
            paragrafoCabecalho.setBorderTop(Borders.BASIC_BLACK_DASHES);
            XWPFRun fontCabecalhoBold = paragrafoCabecalho.createRun();
            fontCabecalhoBold.setBold(true);
            String caminhoLogo = "D:\\erp\\modelo\\arquivos\\logo.png"; // "/Users/bianca/Documents/logo.png";
            FileInputStream inputStream = new FileInputStream(caminhoLogo);
            fontCabecalhoBold.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_JPEG, caminhoLogo, Units.toEMU(50), Units.toEMU(50));
            fontCabecalhoBold.addBreak();
            Filial filial = null;
            int id = 0;
            for (String key : mapImpresso.keySet()) {
                if (key.equals("Filial: ")) {
                    filial = (Filial) mapImpresso.get(key);
                }
                if (key.equals("ID: ")) {
                    id = (int) mapImpresso.get(key);
                }
            }
            if (filial == null) {
                filial = new GenericDAO().findById(1, Filial.class);
            }
            fontCabecalhoBold.setText(filial.getNome());
            fontCabecalhoBold.addBreak();
            fontCabecalhoBold.setText(filial.getNomeFantasia());
            fontCabecalhoBold.addBreak();
            fontCabecalhoBold.setText("CNPJ: " + filial.getCnpj());
            fontCabecalhoBold.addBreak();
            fontCabecalhoBold.setText(filial.getEndereco() + ", " + filial.getNumero());
            fontCabecalhoBold.addBreak();
            fontCabecalhoBold.setText(filial.getBairro() + " - " + filial.getEstado());
            fontCabecalhoBold.addBreak();
            fontCabecalhoBold.setText(filial.getSite());
            
            paragrafoCabecalho = document.createParagraph();
            fontCabecalhoBold = paragrafoCabecalho.createRun();
            fontCabecalhoBold.setFontSize(20);
            fontCabecalhoBold.setText(nomeImpresso + " | " + id);
            
            paragrafoCabecalho = document.createParagraph();
            XWPFRun fontNormal = paragrafoCabecalho.createRun();
            for (String key : mapImpresso.keySet()) {
                fontNormal.setText(key.toUpperCase() + "  " + mapImpresso.get(key) + "   ");
                fontNormal.addBreak();
            }
            if(arrayImpressoProdutos != null){
                paragrafoCabecalho = document.createParagraph();
                fontCabecalhoBold = paragrafoCabecalho.createRun();
                fontCabecalhoBold.setFontSize(20);
                fontCabecalhoBold.setText("Produtos");
                paragrafoCabecalho = document.createParagraph();
                fontNormal = paragrafoCabecalho.createRun();
                fontNormal.setFontSize(10);
                int contador = 1;
                for (Map<String, Object> mapImpressoProdutos : arrayImpressoProdutos) {
                    for (String key : mapImpressoProdutos.keySet()) {
                        fontNormal.setText(key.toUpperCase() + "  " + mapImpressoProdutos.get(key) + "   ");
                        contador++;
                        if (contador == 8) {

                            fontNormal.addBreak();
                            contador = 1;
                        }
                    }
                    fontNormal.addBreak();
                }
            }
            if(arrayImpressoServicos != null){
                paragrafoCabecalho = document.createParagraph();
                fontCabecalhoBold = paragrafoCabecalho.createRun();
                fontCabecalhoBold.setFontSize(20);
                fontCabecalhoBold.setText("Serviços");
                paragrafoCabecalho = document.createParagraph();
                fontNormal = paragrafoCabecalho.createRun();
                fontNormal.setFontSize(10);
                int contador = 1;
                for (Map<String, Object> mapImpressoServicos : arrayImpressoServicos) {
                    for (String key : mapImpressoServicos.keySet()) {
                        fontNormal.setText(key.toUpperCase() + "  " + mapImpressoServicos.get(key) + "   ");
                        contador++;
                        if (contador == 8) {

                            fontNormal.addBreak();
                            contador = 1;
                        }
                    }
                    fontNormal.addBreak();
                }
            }
            if(mapImpressoTotais != null){
                paragrafoCabecalho = document.createParagraph();
                fontCabecalhoBold = paragrafoCabecalho.createRun();
                fontCabecalhoBold.setFontSize(20);
                fontCabecalhoBold.setText("Totais");
                paragrafoCabecalho = document.createParagraph();
                fontNormal = paragrafoCabecalho.createRun();
                for (String key : mapImpressoTotais.keySet()) {
                    fontNormal.setText(key.toUpperCase() + "  " + mapImpressoTotais.get(key) + "   ");
                    fontNormal.addBreak();
                }
            }
            XWPFStyles styles = document.createStyles();         
            document.write(out);
            out.close();
            //new GeradorPDF().converterPDF("/Users/bianca/Documents/documento.docx", "/Users/bianca/Documents/"+nomeImpresso+"-"+id+".pdf");
            new GeradorPDF().converterPDF("D:\\erp\\modelo\\temp\\documento.docx", "D:\\erp\\modelo\\temp\\documento.pdf");
        } 
        catch (InvalidFormatException | IOException e) {

              new Log().salvaErroLog(e);
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em conto com o adminstrador"));
              e.printStackTrace();
        }
    }

    //Método que exclui o registro atual
    public void excluir(EntidadeBase entidadeBase) {

        try {
            entidadeBase.setAtivo(false);
            salvar(entidadeBase);
            novo();
        } 
        catch (Exception e) {
            
            new Log().salvaErroLog(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));

        }
    }

    //Método que salva o registro no banco de dados
    public void salvar(EntidadeBase entidadeBase) {

        if (entidadeBase.getId() == 0) {
            entidadeBase.setAtivo(true);
            setAuditoria(new Auditoria());
            getAuditoria().setCriacaoUsuario(getUsuario());
            getAuditoria().setCriacaoData(new java.sql.Timestamp(new java.util.Date().getTime()));
            getAuditoria().setAlteracaoUsuario(getUsuario());
            getAuditoria().setAlteracaoData(new java.sql.Timestamp(new java.util.Date().getTime()));
            entidadeBase.setAuditoria(getAuditoria());

            try {
                getDao().save(entidadeBase);
                geraConta();
            } 
            catch (Exception e) {
                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
            }

        } 
        else {
            setAuditoria((Auditoria) getDao().findById(entidadeBase.getAuditoria().getId(), Auditoria.class));
            getAuditoria().setAlteracaoUsuario(getUsuario());
            getAuditoria().setAlteracaoData(new java.sql.Timestamp(new java.util.Date().getTime()));
            entidadeBase.setAuditoria(getAuditoria());
            try {
                getDao().update(entidadeBase);
                geraConta();
                if (!entidadeBase.isAtivo()) {
                   novo();
                }
            } 
            catch (Exception e) {

                new Log().salvaErroLog(e);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensagem: ", "Um erro ocorreu, entre em contato com o adminstrador"));
            }
        }
    }

    //Método que retorna uma representação String da auditoria
    public String exibirAuditoria(EntidadeBase entidadeBase) {

        if (entidadeBase.getId() > 0) {
            setAuditoria((Auditoria) getDao().findById(entidadeBase.getAuditoria().getId(), Auditoria.class));
            Usuario criacaoUsuario = getAuditoria().getCriacaoUsuario();
            Usuario alteracaoUsuario = getAuditoria().getAlteracaoUsuario();
            Date criacaoData = getAuditoria().getCriacaoData();
            Date alteracaoData = getAuditoria().getAlteracaoData();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return "Criado em " + dateFormat.format(criacaoData) + " por " + criacaoUsuario.getNome() + ", alterado em " + dateFormat.format(alteracaoData) + " por " + alteracaoUsuario.getNome();
        } else {

            return "Novo Registro";
        }
    }
    
    //Método que retorna o cadastro para a edição
    public String editarRegistro() {
        if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id") != null){
          long id = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("objetoId", id);
        }
        return getCadastro();
    }

    //Método que gerar um novo cadastro  
    public String novo() {

        return getCadastro();
    }

    //Método que retorna o nome do cadastro de cada registro
    public String getNomeCadastro() {
        return "";
    }

    //Método que retorna a descricao do cadastro de cada registro
    public String getDescricaoCadastro() {
        return "";
    }

    //Método que configura os filtros de cada relatório
    public void configurarFiltros() {
    }

    //Método que retorna a descricao do relatorio de cada registro
    public String getDescricaoRelatorio() {
        return "";
    }

    //Método que retorna uma representação string do registro principal de cada cadastro
    public String visulizacaoCadastro() {

        return VISUALIZACAO_CADASTRO;
    }

    //Método que retorna uma representação string do registro principal de cada relatorio
    public String visulizacaoRelatorio() {

        return VISUALIZACAO_RELATORIO;
    }

    //Método que retorna uma representação string do registro principal de cada ferramenta
    public String visulizacaoFerramenta() {

        return VISUALIZACAO_FERRAMENTA;
    }

    //Método que indica se a entidade base deve ou não gerar conta
    public boolean geraConta() {

        return false;
    }
    //Método que retorna os dados que serão exibidos no impresso 
    public Map<String, Object> dadosImpressao(){
    
        return null;
    }
    //Método que retorna os dados dos produtos que serão exibidos no impresso 
    public List dadosImpressaoProdutos(){
    
        return null;
    }
    //Método que retorna os dados dos servicos que serão exibidos no impresso 
    public List dadosImpressaoServicos(){
    
        return null;
    }
    //Método que retorna os dados totais que serão exibidos no impresso 
    public Map<String, Object> dadosImpressaoTotais(){
    
        return null;
    }

}
