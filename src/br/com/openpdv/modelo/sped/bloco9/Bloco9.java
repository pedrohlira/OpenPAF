package br.com.openpdv.modelo.sped.bloco9;

import br.com.openpdv.modelo.sped.Bloco;
import java.io.FileWriter;
import java.util.List;

public class Bloco9 implements Bloco{

    private Dados9001 d9001;
    private List<Dados9900> d9900;
    private Dados9990 d9990;
    private Dados9999 d9999;

    public Bloco9() {
    }

    @Override
    public void gerar(FileWriter fw) throws Exception {
        if (d9001 != null) {
            d9001.gerar(fw);
        }
        if (d9900 != null) {
            for (Dados9900 dados : d9900) {
                dados.gerar(fw);
            }
        }
        if (d9990 != null) {
            d9990.gerar(fw);
        }
        if (d9999 != null) {
            d9999.gerar(fw);
        }
    }

    public Dados9001 getD9001() {
        return d9001;
    }

    public void setD9001(Dados9001 d9001) {
        this.d9001 = d9001;
    }

    public List<Dados9900> getD9900() {
        return d9900;
    }

    public void setD9900(List<Dados9900> d9900) {
        this.d9900 = d9900;
    }

    public Dados9990 getD9990() {
        return d9990;
    }

    public void setD9990(Dados9990 d9990) {
        this.d9990 = d9990;
    }

    public Dados9999 getD9999() {
        return d9999;
    }

    public void setD9999(Dados9999 d9999) {
        this.d9999 = d9999;
    }
}
