package br.com.phdss.modelo.anexo.iv;

/**
 * Classe que representa o modelo R05 do anexo IV.
 *
 * @author Pedro H. Lira
 */
public class R05 extends CabecalhoR {

    private int coo;
    private int documento;
    private int item;
    private String codigo;
    private String descricao;
    private Double quantidade;
    private String unidade;
    private Double bruto;
    private Double desconto;
    private Double acrescimo;
    private Double total;
    private String totalizador;
    private char cancelado;
    private Double canceladoQtd;
    private Double canceladoValor;
    private Double canceladoAcrescimo;
    private char iat;
    private char ippt;
    private int decimalQuantidade;
    private int decimalValor;
    
    public R05() {
        this.padrao = "R05";
    }

    public Double getAcrescimo() {
        return acrescimo * 100; // precisa informar os decimais sem separador
    }

    public void setAcrescimo(Double acrescimo) {
        this.acrescimo = acrescimo;
    }

    public Double getBruto() {
        return bruto * 100; // precisa informar os decimais sem separador
    }

    public void setBruto(Double bruto) {
        this.bruto = bruto;
    }

    public char getCancelado() {
        return cancelado;
    }

    public void setCancelado(char cancelado) {
        this.cancelado = cancelado;
    }

    public Double getCanceladoAcrescimo() {
        return canceladoAcrescimo * 100; // precisa informar os decimais sem separador
    }

    public void setCanceladoAcrescimo(Double canceladoAcrescimo) {
        this.canceladoAcrescimo = canceladoAcrescimo;
    }

    public Double getCanceladoQtd() {
        return canceladoQtd * 100; // precisa informar os decimais sem separador
    }

    public void setCanceladoQtd(Double canceladoQtd) {
        this.canceladoQtd = canceladoQtd;
    }

    public Double getCanceladoValor() {
        return canceladoValor * 100; // precisa informar os decimais sem separador
    }

    public void setCanceladoValor(Double canceladoValor) {
        this.canceladoValor = canceladoValor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCoo() {
        return coo;
    }

    public void setCoo(int coo) {
        this.coo = coo;
    }

    public int getDecimalQuantidade() {
        return decimalQuantidade;
    }

    public void setDecimalQuantidade(int decimalQuantidade) {
        this.decimalQuantidade = decimalQuantidade;
    }

    public int getDecimalValor() {
        return decimalValor;
    }

    public void setDecimalValor(int decimalValor) {
        this.decimalValor = decimalValor;
    }

    public Double getDesconto() {
        return desconto * 100; // precisa informar os decimais sem separador
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public char getIat() {
        return iat;
    }

    public void setIat(char iat) {
        this.iat = iat;
    }

    public char getIppt() {
        return ippt;
    }

    public void setIppt(char ippt) {
        this.ippt = ippt;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public Double getQuantidade() {
        return quantidade * 100; // precisa informar os decimais sem separador
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getTotal() {
        return total * 100; // precisa informar os decimais sem separador
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getTotalizador() {
        return totalizador;
    }

    public void setTotalizador(String totalizador) {
        this.totalizador = totalizador;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
}
