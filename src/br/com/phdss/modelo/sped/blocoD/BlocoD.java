package br.com.phdss.modelo.sped.blocoD;

import br.com.phdss.modelo.sped.Bloco;
import java.io.FileWriter;

public class BlocoD implements Bloco {

    private DadosD001 dD001;
    private DadosD990 dD990;

    public BlocoD() {
    }

    @Override
    public void gerar(FileWriter fw) throws Exception {
        if (dD001 != null) {
            dD001.gerar(fw);
        }
        if (dD990 != null) {
            dD990.gerar(fw);
        }
    }

    public DadosD001 getdD001() {
        return dD001;
    }

    public void setdD001(DadosD001 dD001) {
        this.dD001 = dD001;
    }

    public DadosD990 getdD990() {
        return dD990;
    }

    public void setdD990(DadosD990 dD990) {
        this.dD990 = dD990;
    }
}
