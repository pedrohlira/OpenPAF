package br.com.phdss.modelo.anexo.iv;

import java.util.Date;

/**
 * Classe que representa o modelo R06 do anexo IV.
 *
 * @author Pedro H. Lira
 */
public class R06 extends CabecalhoR {

    private int coo;
    private int gnf;
    private int grg;
    private int cdc;
    private String tipo;
    private Date data;
    
    public R06() {
        this.padrao = "R06";
    }

    public int getCdc() {
        return cdc;
    }

    public void setCdc(int cdc) {
        this.cdc = cdc;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
