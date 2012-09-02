package br.com.openpdv.modelo.sped.blocoH;

import br.com.openpdv.modelo.sped.Bean;

public class DadosH990 extends Bean {

    private int qtd_lin;

    public DadosH990() {
        super("H990");
    }

    public int getQtd_lin() {
        return qtd_lin;
    }

    public void setQtd_lin(int qtd_lin) {
        this.qtd_lin = qtd_lin;
    }
}
