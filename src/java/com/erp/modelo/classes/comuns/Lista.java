/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.modelo.classes.comuns;


/**
 *
 * @author PauloHenrique
 */
public class Lista {
    
    //Os enums abaixo correspondem as listas do sistema//
    public enum Sexo{
    
        MASCULINO("Masculino"),
        FEMININO("Feminino");
        
        private String valor;
        
        private Sexo(String valor){
        
            this.valor = valor;
        }
        
        public String getValor(){
         
            return valor;
        }
    }
    
    public enum Tipo{
    
        FUNCIONARIO("Funcionário"),
        CLIENTE("Cliente"),
        FORNECEDOR("Fornecedor"),
        TRANSPORTADOR("Transportador");
        
        private String valor;
        
        private Tipo(String valor){
        
            this.valor = valor;
        }
        
        public String getValor(){
         
            return valor;
        }
    }
    
    public enum Operacao{
    
        PAGAMENTO("Pagamento"),
        RECEBIMENTO("Recebimento"),
        PAGAMENTOERECBIMENTO("Pagamento e Recebimento");
        
        private String valor;
        
        private Operacao(String valor){
        
            this.valor = valor;
        }
        
        public String getValor(){
         
            return valor;
        }
    }
    
    public enum Departamento{
    
        ADMINSTRATIVO("Administrativo"),
        FINANCEIRO("Financeiro");
        
        private String valor;
        
        private Departamento(String valor){
        
            this.valor = valor;
        }
        
        public String getValor(){
         
            return valor;
        }
    }
    
    public enum Escolaridade{
    
        SUPERIORCOMPLETO("Superior Completo"),
        SUPERIORINCOMPLETO("Superior Incompleto");
        
        private String valor;
        
        private Escolaridade(String valor){
        
            this.valor = valor;
        }
        
        public String getValor(){
         
            return valor;
        }
    }
    
    public enum Estado{
    
        AC("AC"),
        AL("AL"),
        AP("AP"),
        AM("AM"),
        BA("BA"),
        CE("BA"),
        DF("DF"),
        ES("ES"),
        GO("GO"),
        MA("MA"),
        MT("MT"),
        MS("MS"),
        MG("MG"),
        PA("PA"),
        PB("PB"),
        PR("PR"),
        PE("PE"),
        PI("PI"),
        RJ("RJ"),
        RN("RN"),
        RS("RS"),
        RO("RO"),
        RR("RR"),
        SC("SC"),
        SP("SP"),
        SE("SE"),
        TO("TO");
        
        private String valor;
        
        private Estado(String valor){
        
            this.valor = valor;
        }
        
        public String getValor(){
         
            return valor;
        }
    }
    
    public enum Vendedor {
    
        JOAO("João"),
        MARIA("Maria"),
        JOSE("José");
        
        private String valor;
        
        private Vendedor(String valor){
        
            this.valor = valor;
        }
        
        public String getValor(){
        
            return valor;
        }
    }
    
    public enum Categoria{
    
        BLOCO("Bloco"),
        PECA("Peça"),
        PLACA("Placa"),
        CONSERTO("Conserto");
        
        private String valor;
        
        private Categoria(String valor){
        
            this.valor = valor;
        }
        
        public String getValor(){
        
            return valor;
        }
    }
    
     public enum ProdutoOperacao{
    
        COMPRA("Compra"),
        VENDA("Venda"),
        COMPRAEVENDA("Compra e Venda");
        
        private String valor;
        
        private ProdutoOperacao(String valor){
        
            this.valor = valor;
        }
        
        public String getValor(){
         
            return valor;
        }
    }
     
    public enum ProdutoStatus{
    
        ATIVO("Ativo"),
        INATIVO("Inativo");
        
        private String valor;
        
        private ProdutoStatus(String valor){
        
            this.valor = valor;
        }
        
        public String getValor(){
        
            return valor;
        }
    }
    
    public enum ContaStatus{
    
        PENDENTE("Pendente"),
        CONCLUIDO("Concluído");
        
        private String valor;
        
        private ContaStatus(String valor){
        
            this.valor = valor;
        }
        
        public String getValor(){
        
            return valor;
        }
    }
    
    public enum Moeda{
    
        R$("R$"),
        US$("US$");
        
        private String valor;
        
        private Moeda(String valor){
        
            this.valor = valor;
        }
        
        public String getValor(){
        
            return valor;
        }
    }
    public enum ClassFiscal{
    
        A("1234"),
        B("6789");
        
        private String valor;
        
        private ClassFiscal(String valor){
        
            this.valor = valor;
        }
        
        public String getValor(){
        
            return valor;
        }
    }
    
    public enum CFOP{
    
        IMPORTACAO("3102", "- Importação", true),
        VENDADENTRODOESTADO("5101", "- Venda Dentro Do Estado", true),
        DEVOLUCAO("0000", "- Devolução", false);
        
        private String valor;
        private String descricao;
        private boolean geraConta;
        
        private CFOP(String valor, String descricao, boolean geraConta){
        
            this.valor = valor;
            this.descricao = descricao;
            this.geraConta = geraConta;
        }

        public String getValor() {
            return valor;
        }

        public String getDescricao() {
            return descricao;
        }

        public boolean isGeraConta() {
            return geraConta;
        }
       
    }
    
    public enum CadastroStatus {
    
        PENDENTE("Pendente", false),
        FATURADO("Faturado", false),
        ENTREGUE("Entregue", true),
        CONCLUIDO("Concluído", false),
        CANCELADO("Cancelado", false);
        
        private String valor;
        private boolean geraConta;   
        
        private CadastroStatus(String valor, boolean geraConta){
        
            this.valor = valor;
            this.geraConta = geraConta;
        }
        
        public String getValor(){
        
            return valor;
        }

        public boolean isGeraConta() {
            return geraConta;
        }

    }
    
    public enum PadraoBanco {
    
        BRADESCO("Bradesco"),
        ITAU("Itaú");
        
        private String valor;
        
        private PadraoBanco(String valor){
        
            this.valor = valor;
        }
        
        public String getValor(){
        
            return valor;
        }
    }
    
    public enum PadraoConta{
    
        CONTACORRENTE("Conta Corrente"),
        CONTAPOUPANCA("Conta Poupança");
        
        private String valor;
        
        private PadraoConta(String valor){
        
            this.valor = valor;
        }
        
        public String getValor(){
        
            return valor;
        }
    }
    
    public enum PrazoPagamento{
        
        
        MENSAL("1 Mes", 1, "Mensal", 0),
        SETEDIAS("07 Dias", 1, "Diário", 7),
        DEZTRINTANOVENTADIAS("10/30/90 Dias", 3, "Diário", 10,30,90);
        
        private String valor;
        private int parcelas;
        private String prazo;
        private int[] quantidades;
        
        private PrazoPagamento(String valor, int parcelas, String prazo, int... quantidades){
        
            this.valor = valor;
            this.parcelas = parcelas;
            this.prazo = prazo;
            this.quantidades = quantidades;
        }
        
        public String getValor(){
        
            return valor;
        }
        
        public int getParcelas(){
        
            return parcelas;
        }

        public String getPrazo() {
            return prazo;
        }

        public int[] getQuantidades() {
            return quantidades;
        }
        
        
    }
    
    public enum FormaPagamento{
    
        BOLETO("Boleto"),
        CHEQUE("Cheque"),
        DEPOSITO("Depósito"),
        DINHEIRO("Dinheiro"),
        TRANSFERENCIA("Transferência");
        
        private String valor;
        
        private FormaPagamento(String valor){
        
            this.valor = valor;
        }
        
        public String getValor(){
        
            return valor;
        }
    }
    
    public enum Atividade{
    
        A("12345", "Reparo e manutenção de máquina");
        
        private String valor;
        private String descricao;
        
        private Atividade(String valor, String descricao){
        
            this.valor = valor;
            this.descricao = descricao;
        }
        
        public String getValor(){
        
            return valor;
        }
        
        public String getDescricao(){
        
            return descricao;
        }
    }
    
    public enum NotaProdutoStatus{
    
        DIGITADA("Digitada", false),
        AUTORIZADA("Autorizada", true),
        CANCELADA("Cancelada", false);
        
        private String valor;
        private boolean geraConta;
        
        private NotaProdutoStatus(String valor, boolean geraConta){
        
            this.valor = valor;
            this.geraConta = geraConta;
            
        }

        public String getValor() {
            return valor;
        }

        public boolean isGeraConta() {
            return geraConta;
        }
 
    }
    
    public enum NotaServicoStatus{
    
        DIGITADA("Digitada", false),
        AUTORIZANDO("Autorizando...", false),
        AUTORIZADA("Autorizada", true),
        RECUSADA("Recusada", false),
        CANCELANDO("Cancelando", false),
        CANCELADA("Cancelada", false);
        
        private String valor;
        private boolean geraConta;
        
        private NotaServicoStatus(String valor, boolean geraConta){
        
            this.valor = valor;
            this.geraConta = geraConta;
            
        }

        public String getValor() {
            return valor;
        }

        public boolean isGeraConta() {
            return geraConta;
        }
 
    }
    
    
    

}
