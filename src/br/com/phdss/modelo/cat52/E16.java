package br.com.phdss.modelo.cat52;

import java.util.Date;

/**
 * Classe que representa o modelo E16 do CAT52.
 *
 * @author Pedro H. Lira
 */
public class E16 extends CabecalhoE {

    private int coo;
    private int gnf;
    private int grg;
    private int cdc;
    private int crz;
    private String denominacao;
    private Date data;

    public E16() {
        padrao = "E16";
    }

    public int getCoo() {
        return coo;
    }

    public void setCoo(int coo) {
        this.coo = coo;
    }

    public int getGnf() {
        return gnf;
    }

    public void setGnf(int gnf) {
        this.gnf = gnf;
    }

    public int getGrg() {
        return grg;
    }

    public void setGrg(int grg) {
        this.grg = grg;
    }

    public int getCdc() {
        return cdc;
    }

    public void setCdc(int cdc) {
        this.cdc = cdc;
    }

    public int getCrz() {
        return crz;
    }

    public void setCrz(int crz) {
        this.crz = crz;
    }

    public String getDenominacao() {
        return denominacao;
    }

    public void setDenominacao(String denominacao) {
        this.denominacao = denominacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
