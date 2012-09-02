package br.com.openpdv.modelo.sped.blocoE;

import br.com.openpdv.modelo.sped.Bean;

public class DadosE001 extends Bean {

    private int ind_mov;

    public DadosE001() {
        super("E001");
    }

    public int getInd_mov() {
        return ind_mov;
    }

    public void setInd_mov(int ind_mov) {
        this.ind_mov = ind_mov;
    }
}
