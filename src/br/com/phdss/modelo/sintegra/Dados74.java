package br.com.phdss.modelo.sintegra;

import java.util.Date;

public class Dados74 extends Tipo {

    private Date data;
    private String codigo;
    private double qtd;
    private double valor;
    private String posse;
    private String cnpj;
    private String ie;
    private String uf;
    private String brancos;

    public Dados74() {
        super(74);
        brancos = "";
    }

    public String getBrancos() {
        return brancos;
    }

    public void setBrancos(String brancos) {
        this.brancos = "";
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getPosse() {
        return posse;
    }

    public void setPosse(String posse) {
        this.posse = posse;
    }

    public double getQtd() {
        return qtd * 1000;
    }

    public void setQtd(double qtd) {
        this.qtd = qtd;
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
}
