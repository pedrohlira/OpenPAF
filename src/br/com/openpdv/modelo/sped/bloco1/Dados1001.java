package br.com.openpdv.modelo.sped.bloco1;

import br.com.openpdv.modelo.sped.Bean;

public class Dados1001 extends Bean {

    private int ind_mov;

    public Dados1001() {
        super("1001");
    }

    public int getInd_mov() {
        return ind_mov;
    }

    public void setInd_mov(int ind_mov) {
        this.ind_mov = ind_mov;
    }
}
