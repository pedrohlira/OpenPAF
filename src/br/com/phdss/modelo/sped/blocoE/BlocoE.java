package br.com.phdss.modelo.sped.blocoE;

import br.com.phdss.modelo.sped.Bloco;
import java.io.FileWriter;

public class BlocoE implements Bloco {

    private DadosE001 dE001;
    private DadosE990 dE990;

    public BlocoE() {
    }

    @Override
    public void gerar(FileWriter fw) throws Exception {
        if (dE001 != null) {
            dE001.gerar(fw);
        }
        if (dE990 != null) {
            dE990.gerar(fw);
        }
    }

    public DadosE001 getdE001() {
        return dE001;
    }

    public void setdE001(DadosE001 dE001) {
        this.dE001 = dE001;
    }

    public DadosE990 getdE990() {
        return dE990;
    }

    public void setdE990(DadosE990 dE990) {
        this.dE990 = dE990;
    }
}
