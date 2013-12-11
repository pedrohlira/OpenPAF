package br.com.phdss.modelo.cat52;

/**
 * Classe que representa o modelo E13 do CAT52.
 *
 * @author Pedro H. Lira
 */
public class E13 extends CabecalhoE {

    private int crz;
    private String totalizador;
    private double valor;

    public E13() {
        padrao = "E13";
    }

    public int getCrz() {
        return crz;
    }

    public void setCrz(int crz) {
        this.crz = crz;
    }

    public String getTotalizador() {
        return totalizador;
    }

    public void setTotalizador(String totalizador) {
        this.totalizador = totalizador;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
