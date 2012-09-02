package br.com.openpdv.modelo.sintegra;

public class Dados90 extends Tipo {

    private String cnpj;
    private String ie;
    private String totalizadores;
    private int registro90;

    public Dados90() {
        super(90);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public int getRegistro90() {
        return registro90;
    }

    public void setRegistro90(int registro90) {
        this.registro90 = registro90;
    }

    public String getTotalizadores() {
        return totalizadores;
    }

    public void setTotalizadores(String totalizadores) {
        this.totalizadores = totalizadores;
    }
}
