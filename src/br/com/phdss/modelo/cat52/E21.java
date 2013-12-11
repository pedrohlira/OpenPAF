package br.com.phdss.modelo.cat52;

/**
 * Classe que representa o modelo E21 do CAT52.
 *
 * @author Pedro H. Lira
 */
public class E21 extends CabecalhoE {

    private int coo;
    private int ccf;
    private int gnf;
    private String descricao;
    private double valor;
    private char estorno;
    private double estornoValor;

    public E21() {
        padrao = "E21";
    }

    public int getCoo() {
        return coo;
    }

    public void setCoo(int coo) {
        this.coo = coo;
    }

    public int getCcf() {
        return ccf;
    }

    public void setCcf(int ccf) {
        this.ccf = ccf;
    }

    public int getGnf() {
        return gnf;
    }

    public void setGnf(int gnf) {
        this.gnf = gnf;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public char getEstorno() {
        return estorno;
    }

    public void setEstorno(char estorno) {
        this.estorno = estorno;
    }

    public double getEstornoValor() {
        return estornoValor;
    }

    public void setEstornoValor(double estornoValor) {
        this.estornoValor = estornoValor;
    }

}
