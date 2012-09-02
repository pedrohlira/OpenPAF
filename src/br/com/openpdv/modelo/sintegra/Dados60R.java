package br.com.openpdv.modelo.sintegra;

public class Dados60R extends Tipo {

    private String subtipo;
    private int mesAno;
    private String codigo;
    private double qtd;
    private double liquido;
    private double base_icms;
    private String tributacao;
    private String brancos;

    public Dados60R() {
        super(60);
        subtipo = "R";
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

    public double getLiquido() {
        return liquido;
    }

    public void setLiquido(double liquido) {
        this.liquido = liquido;
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

    public String getTributacao() {
        return tributacao;
    }

    public void setTributacao(String tributacao) {
        this.tributacao = tributacao;
    }
}
