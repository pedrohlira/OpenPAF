package br.com.openpdv.modelo.sintegra;

public class Dados61R extends Tipo {

    private String subtipo;
    private int mesAno;
    private String codigo;
    private double qtd;
    private double bruto;
    private double base_icms;
    private double aliq_icms;
    private String brancos;

    public Dados61R() {
        super(61);
        subtipo = "R";
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

    public double getBruto() {
        return bruto * 100;
    }

    public void setBruto(double bruto) {
        this.bruto = bruto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getMesAno() {
        return mesAno;
    }

    public void setMesAno(int mesAno) {
        this.mesAno = mesAno;
    }

    public double getQtd() {
        return qtd * 1000;
    }

    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    public String getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(String subtipo) {
        this.subtipo = "R";
    }
}
