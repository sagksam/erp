$(document).ready(function () {
    
    $(".cep").change(function () {
        var servico = "https://viacep.com.br/ws/";
        var cep = $(this).val();
        $.getJSON(servico + cep + "/json", function (data) {
            $(".endereco").val(data.logradouro);
            $(".bairro").val(data.bairro);
            $(".cidade").val(data.localidade);
            $(".ibge").val(data.ibge);
            PF('estado').selectValue(data.uf);
            $(".pais").val("Brasil");
        });
    });
    function calculaValorFinal() {

        var valorCusto = parseFloat($("#valorCusto_input").val());
        var margemLucro = parseFloat($("#margemLucro_input").val()) / 100;
        var valorVenda = valorCusto * (1 + margemLucro);
        $("#valorVenda_input").val(valorVenda.toLocaleString('pt-br', {minimumFractionDigits: 2}).toString());
        var desconto = parseFloat($("#desconto_input").val()) / 100;
        var valorDesconto = valorVenda * desconto;
        $("#valorDesconto_input").val(valorDesconto.toLocaleString('pt-br', {minimumFractionDigits: 2}).toString());
        var valorFinal = valorVenda - valorDesconto;
        $("#valorFinal_input").val(valorFinal.toLocaleString('pt-br', {minimumFractionDigits: 2}).toString());
    }
    $(".valorCusto, .margemLucro, .valorVenda, .desconto, .valorDesconto, .valorFinal").change(function () {

        calculaValorFinal();
    });
      

});

function pesquisarCep() {

    $('#form\\:cepEnderecos').change(function () {
        var servico = "https://viacep.com.br/ws/";
        var cep = $(this).val();
        $.getJSON(servico + cep + "/json", function (data) {
            $("#form\\:enderecoEnderecos").val(data.logradouro);
            $("#form\\:bairroEnderecos").val(data.bairro);
            $("#form\\:cidadeEnderecos").val(data.localidade);
            $("#form\\:ibgeEndrecos").val(data.ibge);
            PF('widgetEstadoEnderecos').selectValue(data.uf);
            $("#form\\:paisEnderecos").val("Brasil");
        });

    });
}

function selecionarEmpresa() {

    var descricao = PF('widgetEmpresaLista').getSelectedLabel();
    $('#form\\:descricao').val(descricao);
}


function selecionarPessoa() {

    var descricao = PF('widgetPessoaLista').getSelectedLabel();
    $('#form\\:descricao').val(descricao);
}

function calcularValoresServico() {

    var quantidadeItem = parseFloat($('#form\\:quantidadeServico').val());
    var valorUnitarioItem = parseFloat($('#form\\:valorUnitarioServico_input').val());
    var valorServicoItem = quantidadeItem * valorUnitarioItem;  
    var descontoItem = parseFloat($("#form\\:descontoServico_input").val()) / 100;
    var valorDescontoItem = valorServicoItem * descontoItem;
    var valorTotalItem = valorServicoItem - valorDescontoItem;
    $('#form\\:valorServicoServico_input').val(valorServicoItem.toLocaleString('pt-br', {minimumFractionDigits: 2}).toString());
    $('#form\\:valorDescontoServico_input').val(valorDescontoItem.toLocaleString('pt-br', {minimumFractionDigits: 2}).toString());
    $('#form\\:valorTotalServico_input').val(valorTotalItem.toLocaleString('pt-br', {minimumFractionDigits: 2}).toString());

    $('#form\\:quantidadeServico, #form\\:valorUnitarioServico_input, #form\\:valorServicoServico_input, #form\\:descontoServico_input, #form\\:valorTotalServico_input').change(function () {

        calcularValoresServico();
    });
    return valorTotalItem;
}

function calcularValoresProduto(){
    
    var quantidadeItem = parseFloat($('#form\\:quantidadeProduto').val());
    var valorUnitarioItem = parseFloat($('#form\\:valorUnitarioProduto_input').val());
    var valorProdutoItem = quantidadeItem * valorUnitarioItem; 
    var descontoItem = parseFloat($('#form\\:descontoProduto_input').val()) / 100;
    var valorDescontoItem = valorProdutoItem * descontoItem;
    var valorFreteItem = parseFloat($('#form\\:valorFreteProduto_input').val());
    var margemIPIItem = parseFloat($('#form\\:margemIPIProduto_input').val());
    var aliquotaIPIItem = parseFloat($('#form\\:aliquotaIPIProduto_input').val()) / 100;
    var baseIPIItem = (valorProdutoItem - valorDescontoItem) * (margemIPIItem / 100);
    var valorIPIItem = baseIPIItem * (aliquotaIPIItem);    
    var margemICMSItem = parseFloat($('#form\\:margemICMSProduto_input').val());
    var aliquotaICMSItem = parseFloat($('#form\\:aliquotaICMSProduto_input').val()) / 100;
    var baseICMSItem = (valorProdutoItem - valorDescontoItem) * (margemICMSItem / 100);
    var valorICMSItem = baseICMSItem * (aliquotaICMSItem);
    var margemICMSSTItem = parseFloat($('#form\\:margemICMSSTProduto_input').val());
    var aliquotaICMSSTItem = parseFloat($('#form\\:aliquotaICMSSTProduto_input').val()) / 100;
    var baseICMSSTItem = (valorProdutoItem - valorDescontoItem) * (margemICMSSTItem / 100);
    var valorICMSSTItem = baseICMSSTItem * (aliquotaICMSSTItem);
    var valorSeguroItem = parseFloat($('#form\\:valorSeguroProduto_input').val());
    var outrasDespesasItem = parseFloat($('#form\\:outrasDespesasProduto_input').val());
    var valorTotalItem = valorProdutoItem - valorDescontoItem + valorICMSItem + valorICMSSTItem + valorFreteItem + valorSeguroItem + outrasDespesasItem;
    $('#form\\:valorProdutoProduto_input').val(valorProdutoItem.toLocaleString('pt-br', {minimumFractionDigits: 2}).toString());
    $('#form\\:valorDescontoProduto_input').val(valorDescontoItem.toLocaleString('pt-br', {minimumFractionDigits: 2}).toString());
    $('#form\\:baseIPIProduto_input').val(baseIPIItem.toLocaleString('pt-br', {minimumFractionDigits: 2}).toString());
    $('#form\\:valorIPIProduto_input').val(valorIPIItem.toLocaleString('pt-br', {minimumFractionDigits: 2}).toString());
    $('#form\\:baseICMSProduto_input').val(baseICMSItem.toLocaleString('pt-br', {minimumFractionDigits: 2}).toString());
    $('#form\\:valorICMSProduto_input').val(valorICMSItem.toLocaleString('pt-br', {minimumFractionDigits: 2}).toString());
    $('#form\\:baseICMSSTProduto_input').val(baseICMSSTItem.toLocaleString('pt-br', {minimumFractionDigits: 2}).toString());
    $('#form\\:valorICMSSTProduto_input').val(valorICMSSTItem.toLocaleString('pt-br', {minimumFractionDigits: 2}).toString());
    $('#form\\:valorTotalProduto_input').val(valorTotalItem.toLocaleString('pt-br', {minimumFractionDigits: 2}).toString());
    
    $('#form\\:quantidadeProduto, #form\\:valorUnitarioProduto_input, #form\\:valorProdutoProduto_input, #form\\:descontoProduto_input, #form\\:margemIPIProduto_input, #form\\:aliquotaIPIProduto_input, #form\\:margemICMSProduto_input, #form\\:aliquotaICMSProduto_input, #form\\:margemICMSSTProduto_input, #form\\:aliquotaICMSSTProduto_input, #form\\:valorFreteProduto_input, #form\\:valorSeguroProduto_input, #form\\:outrasDespesasProduto_input').change(function(){
        calcularValoresProduto();
    });
    return valorTotalItem;

}

function calcularItensEstoqueCompra(){
    
    var estoqueAtualItem = parseFloat($('#form\\:estoqueAtualProduto').val());
    var quantidadeItem = parseFloat($('#form\\:quantidadeProduto').val());
    var valorUnitarioItem =  parseFloat($('#form\\:valorUnitarioProduto_input').val());
    var saldoItem = 0;
    var valorTotalItem = 0;
    saldoItem = quantidadeItem + estoqueAtualItem;
    valorTotalItem = quantidadeItem * valorUnitarioItem;
    $('#form\\:saldoProduto').val(saldoItem);
    $('#form\\:valorTotalProduto_input').val(valorTotalItem.toLocaleString('pt-br', {minimumFractionDigits: 2}).toString());
    $('#form\\:estoqueAtualProduto, #form\\:quantidadeProduto, #form\\:valorUnitarioProduto').change(function(){
       calcularItensEstoqueCompra(); 
    });
}

function calcularItensEstoqueVenda(){
    
    var estoqueAtualItem = parseFloat($('#form\\:estoqueAtualProduto').val());
    var quantidadeItem = parseFloat($('#form\\:quantidadeProduto').val());
    var valorUnitarioItem =  parseFloat($('#form\\:valorUnitarioProduto_input').val());
    var saldoItem = 0;
    var valorTotalItem = 0;
    saldoItem = estoqueAtualItem - quantidadeItem;
    valorTotalItem = quantidadeItem * valorUnitarioItem;
    $('#form\\:saldoProduto').val(saldoItem);
    $('#form\\:valorTotalProduto_input').val(valorTotalItem.toLocaleString('pt-br', {minimumFractionDigits: 2}).toString());
    $('#form\\:estoqueAtualProduto, #form\\:quantidadeProduto, #form\\:valorUnitarioProduto').change(function(){
       calcularItensEstoqueVenda(); 
    });
}

function iniciar() {
    PF('statusDialog').show();
}
 
function parar() {
    PF('statusDialog').hide();
}







