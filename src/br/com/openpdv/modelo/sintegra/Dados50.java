package br.com.openpdv.modelo.sintegra;

import java.util.Date;

public class Dados50 extends Tipo {

    private String cnpj;
    private String ie;
    private Date data;
    private String uf;
    private int modelo;
    private String serie;
    private int numero;
    private int cfop;
    private String emitente;
    private double valor;
    private double base_icms;
    private double valor_icms;
    private double valor_isento;
    private double outras;
    private double aliq_icms;
    private String situacao;

    public Dados50() {
        super(50);
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

    public int getCfop() {
        return cfop;
    }

    public void setCfop(int cfop) {
        this.cfop = cfop;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getEmitente() {
        return emitente;
    }

    public void setEmitente(String emitente) {
        this.emitente = emitente;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public double getValor() {
        return valor * 100;
    }

    public void setValor(double valor) {
        this.valor = valor;
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
