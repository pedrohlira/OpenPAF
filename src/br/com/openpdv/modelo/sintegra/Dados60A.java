package br.com.openpdv.modelo.sintegra;

import java.util.Date;

public class Dados60A extends Tipo {

    private String subtipo;
    private Date data;
    private String serie;
    private String totalizador;
    private double valor;
    private String brancos;

    public Dados60A() {
        super(60);
        subtipo = "A";
        brancos = "";
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
        this.subtipo = "A";
    }

    public String getTotalizador() {
        return totalizador;
    }

    public void setTotalizador(String totalizador) {
        this.totalizador = totalizador;
    }

    public double getValor() {
        return valor * 100;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
