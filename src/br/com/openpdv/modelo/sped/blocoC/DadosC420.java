package br.com.openpdv.modelo.sped.blocoC;

import br.com.openpdv.modelo.sped.Bean;
import java.io.FileWriter;
import java.util.List;

public class DadosC420 extends Bean {

    private String cod_tot_par;
    private double vlr_acum_tot;
    private String nr_tot;
    private String descr_nr_tot;
    // subdados
    private List<DadosC425> dC425;

    public DadosC420() {
        super("C420");
    }

    @Override
    public void gerar(FileWriter fw) throws Exception {
        super.gerar(fw);
        if(dC425 != null){
            for(DadosC425 dados : dC425){
                dados.gerar(fw);
            }
        }
    }

    public String getCod_tot_par() {
        return cod_tot_par;
    }

    public void setCod_tot_par(String cod_tot_par) {
        this.cod_tot_par = cod_tot_par;
    }

    public double getVlr_acum_tot() {
        return vlr_acum_tot;
    }

    public void setVlr_acum_tot(double vlr_acum_tot) {
        this.vlr_acum_tot = vlr_acum_tot;
    }

    public String getNr_tot() {
        return nr_tot;
    }

    public void setNr_tot(String nr_tot) {
        this.nr_tot = nr_tot;
    }

    public String getDescr_nr_tot() {
        return descr_nr_tot;
    }

    public void setDescr_nr_tot(String descr_nr_tot) {
        this.descr_nr_tot = descr_nr_tot;
    }

    public List<DadosC425> getdC425() {
        return dC425;
    }

    public void setdC425(List<DadosC425> dC425) {
        this.dC425 = dC425;
    }
}
