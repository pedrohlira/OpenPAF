package br.com.openpdv.modelo.sintegra;

import java.util.Date;

public class Dados75 extends Tipo {

    private Date inicio;
    private Date fim;
    private String codigo;
    private String ncm;
    private String descricao;
    private String und;
    private double aliq_ipi;
    private double aliq_icms;
    private double reducao;
    private double base_icmsST;

    public Dados75() {
        super(75);
    }

    public double getAliq_icms() {
        return aliq_icms * 100;
    }

    public void setAliq_icms(double aliq_icms) {
        this.aliq_icms = aliq_icms;
    }

    public double getAliq_ipi() {
        return aliq_ipi * 100;
    }

    public void setAliq_ipi(double aliq_ipi) {
        this.aliq_ipi = aliq_ipi;
    }

    public double getBase_icmsST() {
        return base_icmsST * 100;
    }

    public void setBase_icmsST(double base_icmsST) {
        this.base_icmsST = base_icmsST;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public double getReducao() {
        return reducao * 100;
    }

    public void setReducao(double reducao) {
        this.reducao = reducao;
    }

    public String getUnd() {
        return und;
    }

    public void setUnd(String und) {
        this.und = und;
    }
}
