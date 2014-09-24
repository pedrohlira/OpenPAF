package br.com.phdss.modelo.sped.bloco1;

import br.com.phdss.modelo.sped.Bloco;
import java.io.FileWriter;

public class Bloco1 implements Bloco {

    private Dados1001 d1001;
    private Dados1010 d1010;
    private Dados1990 d1990;

    public Bloco1() {
    }

    @Override
    public void gerar(FileWriter fw) throws Exception {
        if (d1001 != null) {
            d1001.gerar(fw);
        }
        if (d1010 != null) {
            d1010.gerar(fw);
        }
        if (d1990 != null) {
            d1990.gerar(fw);
        }
    }

    public Dados1001 getD1001() {
        return d1001;
    }

    public void setD1001(Dados1001 d1001) {
        this.d1001 = d1001;
    }

    public Dados1010 getD1010() {
        return d1010;
    }

    public void setD1010(Dados1010 d1010) {
        this.d1010 = d1010;
    }

    public Dados1990 getD1990() {
        return d1990;
    }

    public void setD1990(Dados1990 d1990) {
        this.d1990 = d1990;
    }
}
