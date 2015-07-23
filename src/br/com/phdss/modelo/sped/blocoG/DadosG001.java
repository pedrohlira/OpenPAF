package br.com.phdss.modelo.sped.blocoG;

import br.com.phdss.modelo.sped.Bean;

public class DadosG001 extends Bean {

    private int ind_mov;

    public DadosG001() {
        super("G001");
    }

    public int getInd_mov() {
        return ind_mov;
    }

    public void setInd_mov(int ind_mov) {
        this.ind_mov = ind_mov;
    }
}
