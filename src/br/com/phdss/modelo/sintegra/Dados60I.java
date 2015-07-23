package br.com.phdss.modelo.sintegra;

import java.util.Date;

public class Dados60I extends Tipo {

    private String subtipo;
    private Date data;
    private String serie;
    private String modelo;
    private int coo;
    private int item;
    private String codigo;
    private double qtd;
    private double liquido;
    private double base_icms;
    private String tributacao;
    private double valor_icms;
    private String brancos;

    public Dados60I() {
        super(60);
        subtipo = "I";
        brancos = "";
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public double getLiquido() {
        return liquido * 100;
    }

    public void setLiquido(double liquido) {
        this.liquido = liquido;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getQtd() {
        return qtd * 1000;
    }

    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(String subtipo) {
        this.subtipo = "I";
    }

    public String getTributacao() {
        return tributacao;
    }

    public void setTributacao(String tributacao) {
        this.tributacao = tributacao;
    }

    public double getValor_icms() {
        return valor_icms * 100;
    }

    public void setValor_icms(double valor_icms) {
        this.valor_icms = valor_icms;
    }
}
