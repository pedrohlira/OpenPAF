package br.com.phdss.modelo.cat52;

import java.util.Date;

/**
 * Classe que representa o modelo E12 do CAT52.
 *
 * @author Pedro H. Lira
 */
public class E12 extends CabecalhoE {

    private int crz;
    private int coo;
    private int cro;
    private Date movimento;
    private Date emissao;
    private double bruto;
    private char issqn;

    public E12() {
        padrao = "E12";
    }

    public int getCrz() {
        return crz;
    }

    public void setCrz(int crz) {
        this.crz = crz;
    }

    public int getCoo() {
        return coo;
    }

    public void setCoo(int coo) {
        this.coo = coo;
    }

    public int getCro() {
        return cro;
    }

    public void setCro(int cro) {
        this.cro = cro;
    }

    public Date getMovimento() {
        return movimento;
    }

    public void setMovimento(Date movimento) {
        this.movimento = movimento;
    }

    public Date getEmissao() {
        return emissao;
    }

    public void setEmissao(Date emissao) {
        this.emissao = emissao;
    }

    public double getBruto() {
        return bruto * 100;
    }

    public void setBruto(double bruto) {
        this.bruto = bruto;
    }

    public char getIssqn() {
        return issqn;
    }

    public void setIssqn(char issqn) {
        this.issqn = issqn;
    }

}
