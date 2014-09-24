package br.com.phdss.modelo.sped.blocoC;

import br.com.phdss.modelo.sped.Bean;

public class DadosC110 extends Bean {

    private String cod_inf;
    private String txt_compl;

    public DadosC110() {
        super("C110");
    }

    public String getCod_inf() {
        return cod_inf;
    }

    public void setCod_inf(String cod_inf) {
        this.cod_inf = cod_inf;
    }

    public String getTxt_compl() {
        return txt_compl;
    }

    public void setTxt_compl(String txt_compl) {
        this.txt_compl = txt_compl;
    }
}
