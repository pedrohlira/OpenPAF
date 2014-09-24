package br.com.phdss.modelo.sped.bloco1;

import br.com.phdss.modelo.sped.Bean;

public class Dados1990 extends Bean {

    private int qtd_lin;

    public Dados1990() {
        super("1990");
    }

    public int getQtd_lin() {
        return qtd_lin;
    }

    public void setQtd_lin(int qtd_lin) {
        this.qtd_lin = qtd_lin;
    }
}
