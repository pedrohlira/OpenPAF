package br.com.phdss.modelo.sped.blocoC;

import br.com.phdss.modelo.sped.Bean;
import java.io.FileWriter;
import java.util.List;

public class DadosC400 extends Bean {

    private String cod_mod;
    private String ecf_mod;
    private String ecf_fab;
    private int ecf_cx;
    // subdados
    private List<DadosC405> dC405;

    public DadosC400() {
        super("C400");
    }

    @Override
    public void gerar(FileWriter fw) throws Exception {
        super.gerar(fw);
        if (dC405 != null) {
            for (DadosC405 dados : dC405) {
                dados.gerar(fw);
            }
        }
    }

    public String getCod_mod() {
        return cod_mod;
    }

    public void setCod_mod(String cod_mod) {
        this.cod_mod = cod_mod;
    }

    public String getEcf_mod() {
        return ecf_mod;
    }

    public void setEcf_mod(String ecf_mod) {
        this.ecf_mod = ecf_mod;
    }

    public String getEcf_fab() {
        return ecf_fab;
    }

    public void setEcf_fab(String ecf_fab) {
        this.ecf_fab = ecf_fab;
    }

    public int getEcf_cx() {
        return ecf_cx;
    }

    public void setEcf_cx(int ecf_cx) {
        this.ecf_cx = ecf_cx;
    }

    public List<DadosC405> getdC405() {
        return dC405;
    }

    public void setdC405(List<DadosC405> dC405) {
        this.dC405 = dC405;
    }
}
