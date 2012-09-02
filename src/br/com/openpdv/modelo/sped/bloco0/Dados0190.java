package br.com.openpdv.modelo.sped.bloco0;

import br.com.openpdv.modelo.sped.Bean;

public class Dados0190 extends Bean {

    private String unid;
    private String descr;

    public Dados0190() {
        super("0190");
    }

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unid) {
        this.unid = unid;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
