package br.com.phdss.modelo.sped.blocoG;

import br.com.phdss.modelo.sped.Bloco;
import java.io.FileWriter;

public class BlocoG implements Bloco {

    private DadosG001 dG001;
    private DadosG990 dG990;

    public BlocoG() {
    }

    @Override
    public void gerar(FileWriter fw) throws Exception {
        if (dG001 != null) {
            dG001.gerar(fw);
        }
        if (dG990 != null) {
            dG990.gerar(fw);
        }
    }

    public DadosG001 getdG001() {
        return dG001;
    }

    public void setdG001(DadosG001 dG001) {
        this.dG001 = dG001;
    }

    public DadosG990 getdG990() {
        return dG990;
    }

    public void setdG990(DadosG990 dG990) {
        this.dG990 = dG990;
    }
}
