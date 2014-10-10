package br.com.phdss.modelo.sintegra;

import java.util.Date;

public class Dados60D extends Tipo {

    private String subtipo;
    private Date data;
    private String serie;
    private String codigo;
    private double qtd;
    private double liquido;
    private double base_icms;
    private String tributacao;
    private double valor_icms;
    private String brancos;

    public Dados60D() {
        super(60);
        subtipo = "D";
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getLiquido() {
        return liquido * 100;
    }

    public void setLiquido(double liquido) {
        this.liquido = liquido;
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
        this.subtipo = "D";
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
