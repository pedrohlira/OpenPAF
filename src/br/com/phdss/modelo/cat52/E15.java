package br.com.phdss.modelo.cat52;

/**
 * Classe que representa o modelo E15 do CAT52.
 *
 * @author Pedro H. Lira
 */
public class E15 extends CabecalhoE {

    private int coo;
    private int ccf;
    private int item;
    private String codigo;
    private String descricao;
    private double qtd;
    private String und;
    private double bruto;
    private double desconto;
    private double acrescimo;
    private double liquido;
    private String totalizador;
    private char cancelado;
    private double qtdCancelado;
    private double valorCancelado;
    private double acrescimoCancelado;
    private char iat;
    private int qtdDecimais;
    private int valorDecimais;

    public E15() {
        padrao = "E15";
    }

    public int getCoo() {
        return coo;
    }

    public void setCoo(int coo) {
        this.coo = coo;
    }

    public int getCcf() {
        return ccf;
    }

    public void setCcf(int ccf) {
        this.ccf = ccf;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getQtd() {
        return qtd * 100;
    }

    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    public String getUnd() {
        return und;
    }

    public void setUnd(String und) {
        this.und = und;
    }

    public double getBruto() {
        return bruto * 100;
    }

    public void setBruto(double bruto) {
        this.bruto = bruto;
    }

    public double getDesconto() {
        return desconto * 100;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getAcrescimo() {
        return acrescimo * 100;
    }

    public void setAcrescimo(double acrescimo) {
        this.acrescimo = acrescimo;
    }

    public double getLiquido() {
        return liquido * 100;
    }

    public void setLiquido(double liquido) {
        this.liquido = liquido;
    }

    public String getTotalizador() {
        return totalizador;
    }

    public void setTotalizador(String totalizador) {
        this.totalizador = totalizador;
    }

    public char getCancelado() {
        return cancelado;
    }

    public void setCancelado(char cancelado) {
        this.cancelado = cancelado;
    }

    public double getQtdCancelado() {
        return qtdCancelado * 100;
    }

    public void setQtdCancelado(double qtdCancelado) {
        this.qtdCancelado = qtdCancelado;
    }

    public double getValorCancelado() {
        return valorCancelado * 100;
    }

    public void setValorCancelado(double valorCancelado) {
        this.valorCancelado = valorCancelado;
    }

    public double getAcrescimoCancelado() {
        return acrescimoCancelado * 100;
    }

    public void setAcrescimoCancelado(double acrescimoCancelado) {
        this.acrescimoCancelado = acrescimoCancelado;
    }

    public char getIat() {
        return iat;
    }

    public void setIat(char iat) {
        this.iat = iat;
    }

    public int getQtdDecimais() {
        return qtdDecimais;
    }

    public void setQtdDecimais(int qtdDecimais) {
        this.qtdDecimais = qtdDecimais;
    }

    public int getValorDecimais() {
        return valorDecimais;
    }

    public void setValorDecimais(int valorDecimais) {
        this.valorDecimais = valorDecimais;
    }

}
