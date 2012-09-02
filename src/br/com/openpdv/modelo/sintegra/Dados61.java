package br.com.openpdv.modelo.sintegra;

import java.util.Date;

public class Dados61 extends Tipo {

    private String brancos;
    private Date data;
    private int modelo;
    private String serie;
    private String subserie;
    private int numInicial;
    private int numFinal;
    private double valorTotal;
    private double base_icms;
    private double valor_icms;
    private double valor_isento;
    private double outras;
    private double aliq_icms;

    public Dados61() {
        super(61);
        brancos = "";
    }

    public double getAliq_icms() {
        return aliq_icms * 100;
    }

    public void setAliq_icms(double aliq_icms) {
        this.aliq_icms = aliq_icms;
    }

    public double getBase_icms() {
        return base_icms * 100;
    }

    public void setBase_icms(double base_icms) {
        this.base_icms = base_icms;
    }

    public String getBrancos() {
        return brancos;
    }

    public void setBrancos(String brancos) {
        this.brancos = "";
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public int getNumFinal() {
        return numFinal;
    }

    public void setNumFinal(int numFinal) {
        this.numFinal = numFinal;
    }

    public int getNumInicial() {
        return numInicial;
    }

    public void setNumInicial(int numInicial) {
        this.numInicial = numInicial;
    }

    public double getOutras() {
        return outras * 100;
    }

    public void setOutras(double outras) {
        this.outras = outras;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getSubserie() {
        return subserie;
    }

    public void setSubserie(String subserie) {
        this.subserie = subserie;
    }

    public double getValorTotal() {
        return valorTotal * 100;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValor_icms() {
        return valor_icms * 100;
    }

    public void setValor_icms(double valor_icms) {
        this.valor_icms = valor_icms;
    }

    public double getValor_isento() {
        return valor_isento * 100;
    }

    public void setValor_isento(double valor_isento) {
        this.valor_isento = valor_isento;
    }
}
