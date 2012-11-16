package br.com.phdss.modelo.sped.blocoC;

import br.com.phdss.modelo.sped.Bean;

public class DadosC001 extends Bean {

    private int ind_mov;

    public DadosC001() {
        super("C001");
    }

    public int getInd_mov() {
        return ind_mov;
    }

    public void setInd_mov(int ind_mov) {
        this.ind_mov = ind_mov;
    }
}
