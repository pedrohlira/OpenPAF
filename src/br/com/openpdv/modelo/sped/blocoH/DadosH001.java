package br.com.openpdv.modelo.sped.blocoH;

import br.com.openpdv.modelo.sped.Bean;

public class DadosH001 extends Bean {

    private int ind_mov;

    public DadosH001() {
        super("H001");
    }

    public int getInd_mov() {
        return ind_mov;
    }

    public void setInd_mov(int ind_mov) {
        this.ind_mov = ind_mov;
    }
}
