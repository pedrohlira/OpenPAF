package br.com.openpdv.modelo.sped.bloco0;

import br.com.openpdv.modelo.sped.Bloco;
import java.io.FileWriter;
import java.util.List;

public class Bloco0 implements Bloco {

    private Dados0000 d0000;
    private Dados0001 d0001;
    private Dados0005 d0005;
    private Dados0100 d0100;
    private List<Dados0150> d0150;
    private List<Dados0190> d0190;
    private List<Dados0200> d0200;
    private List<Dados0220> d0220;
    private List<Dados0400> d0400;
    private List<Dados0450> d0450;
    private Dados0500 d0500;
    private Dados0990 d0990;

    public Bloco0() {
    }

    @Override
    public void gerar(FileWriter fw) throws Exception {
        if (d0000 != null) {
            d0000.gerar(fw);
        }
        if (d0001 != null) {
            d0001.gerar(fw);
        }
        if (d0005 != null) {
            d0005.gerar(fw);
        }
        if (d0100 != null) {
            d0100.gerar(fw);
        }
        if (d0150 != null) {
            for (Dados0150 dados : d0150) {
                dados.gerar(fw);
            }
        }
        if (d0190 != null) {
            for (Dados0190 dados : d0190) {
                dados.gerar(fw);
            }
        }
        if (d0200 != null) {
            for (Dados0200 dados : d0200) {
                dados.gerar(fw);
            }
        }
        if (d0220 != null) {
            for (Dados0220 dados : d0220) {
                dados.gerar(fw);
            }
        }
        if (d0400 != null) {
            for (Dados0400 dados : d0400) {
                dados.gerar(fw);
            }
        }
        if (d0450 != null) {
            for (Dados0450 dados : d0450) {
                dados.gerar(fw);
            }
        }
        if (d0500 != null) {
            d0500.gerar(fw);
        }
        if (d0990 != null) {
            d0990.gerar(fw);
        }
    }

    public Dados0000 getD0000() {
        return d0000;
    }

    public void setD0000(Dados0000 d0000) {
        this.d0000 = d0000;
    }

    public Dados0001 getD0001() {
        return d0001;
    }

    public void setD0001(Dados0001 d0001) {
        this.d0001 = d0001;
    }

    public Dados0005 getD0005() {
        return d0005;
    }

    public void setD0005(Dados0005 d0005) {
        this.d0005 = d0005;
    }

    public Dados0100 getD0100() {
        return d0100;
    }

    public void setD0100(Dados0100 d0100) {
        this.d0100 = d0100;
    }

    public List<Dados0150> getD0150() {
        return d0150;
    }

    public void setD0150(List<Dados0150> d0150) {
        this.d0150 = d0150;
    }

    public List<Dados0190> getD0190() {
        return d0190;
    }

    public void setD0190(List<Dados0190> d0190) {
        this.d0190 = d0190;
    }

    public List<Dados0200> getD0200() {
        return d0200;
    }

    public void setD0200(List<Dados0200> d0200) {
        this.d0200 = d0200;
    }

    public List<Dados0220> getD0220() {
        return d0220;
    }

    public void setD0220(List<Dados0220> d0220) {
        this.d0220 = d0220;
    }

    public List<Dados0400> getD0400() {
        return d0400;
    }

    public void setD0400(List<Dados0400> d0400) {
        this.d0400 = d0400;
    }

    public List<Dados0450> getD0450() {
        return d0450;
    }

    public void setD0450(List<Dados0450> d0450) {
        this.d0450 = d0450;
    }

    public Dados0500 getD0500() {
        return d0500;
    }

    public void setD0500(Dados0500 d0500) {
        this.d0500 = d0500;
    }

    public Dados0990 getD0990() {
        return d0990;
    }

    public void setD0990(Dados0990 d0990) {
        this.d0990 = d0990;
    }
}
