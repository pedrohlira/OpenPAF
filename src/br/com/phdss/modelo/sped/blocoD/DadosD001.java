package br.com.phdss.modelo.sped.blocoD;

import br.com.phdss.modelo.sped.Bean;

public class DadosD001 extends Bean {

    private int ind_mov;

    public DadosD001() {
        super("D001");
    }

    public int getInd_mov() {
        return ind_mov;
    }

    public void setInd_mov(int ind_mov) {
        this.ind_mov = ind_mov;
    }
}
