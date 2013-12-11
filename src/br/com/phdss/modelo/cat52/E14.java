package br.com.phdss.modelo.cat52;

import java.util.Date;

/**
 * Classe que representa o modelo E14 do CAT52.
 *
 * @author Pedro H. Lira
 */
public class E14 extends CabecalhoE {

    private int ccf;
    private int coo;
    private Date data;
    private double bruto;
    private double desconto;
    private char descontoTipo;
    private double acrescimo;
    private char acrescimoTipo;
    private double liquido;
    private char cancelado;
    private double canceladoAcrescimo;
    private char ordemDA;
    private String cliente;
    private String cnpj_cpf;

    public E14() {
        padrao = "E14";
    }

    public int getCcf() {
        return ccf;
    }

    public void setCcf(int ccf) {
        this.ccf = ccf;
    }

    public int getCoo() {
        return coo;
    }

    public void setCoo(int coo) {
        this.coo = coo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getBruto() {
        return bruto;
    }

    public void setBruto(double bruto) {
        this.bruto = bruto;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public char getDescontoTipo() {
        return descontoTipo;
    }

    public void setDescontoTipo(char descontoTipo) {
        this.descontoTipo = descontoTipo;
    }

    public double getAcrescimo() {
        return acrescimo;
    }

    public void setAcrescimo(double acrescimo) {
        this.acrescimo = acrescimo;
    }

    public char getAcrescimoTipo() {
        return acrescimoTipo;
    }

    public void setAcrescimoTipo(char acrescimoTipo) {
        this.acrescimoTipo = acrescimoTipo;
    }

    public double getLiquido() {
        return liquido;
    }

    public void setLiquido(double liquido) {
        this.liquido = liquido;
    }

    public char getCancelado() {
        return cancelado;
    }

    public void setCancelado(char cancelado) {
        this.cancelado = cancelado;
    }

    public double getCanceladoAcrescimo() {
        return canceladoAcrescimo;
    }

    public void setCanceladoAcrescimo(double canceladoAcrescimo) {
        this.canceladoAcrescimo = canceladoAcrescimo;
    }

    public char getOrdemDA() {
        return ordemDA;
    }

    public void setOrdemDA(char ordemDA) {
        this.ordemDA = ordemDA;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCnpj_cpf() {
        return cnpj_cpf;
    }

    public void setCnpj_cpf(String cnpj_cpf) {
        this.cnpj_cpf = cnpj_cpf;
    }

}
