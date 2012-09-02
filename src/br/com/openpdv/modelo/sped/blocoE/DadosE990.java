package br.com.openpdv.modelo.sped.blocoE;

import br.com.openpdv.modelo.sped.Bean;

public class DadosE990 extends Bean {

    private int qtd_lin;

    public DadosE990() {
        super("E990");
    }

    public int getQtd_lin() {
        return qtd_lin;
    }

    public void setQtd_lin(int qtd_lin) {
        this.qtd_lin = qtd_lin;
    }
}
