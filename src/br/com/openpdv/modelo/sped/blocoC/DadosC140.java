package br.com.openpdv.modelo.sped.blocoC;

import br.com.openpdv.modelo.sped.Bean;
import java.io.FileWriter;
import java.util.List;

public class DadosC140 extends Bean {

    private String ind_emit;
    private String ind_tit;
    private String desc_tit;
    private String num_tit;
    private int qtd_parc;
    private double vl_tit;
    // subdados
    private List<DadosC141> dC141;

    public DadosC140() {
        super("C140");
    }

    @Override
    public void gerar(FileWriter fw) throws Exception {
        super.gerar(fw);
        if (dC141 != null) {
            for (DadosC141 dados : dC141) {
                dados.gerar(fw);
            }
        }
    }

    public String getInd_emit() {
        return ind_emit;
    }

    public void setInd_emit(String ind_emit) {
        this.ind_emit = ind_emit;
    }

    public String getInd_tit() {
        return ind_tit;
    }

    public void setInd_tit(String ind_tit) {
        this.ind_tit = ind_tit;
    }

    public String getDesc_tit() {
        return desc_tit;
    }

    public void setDesc_tit(String desc_tit) {
        this.desc_tit = desc_tit;
    }

    public String getNum_tit() {
        return num_tit;
    }

    public void setNum_tit(String num_tit) {
        this.num_tit = num_tit;
    }

    public int getQtd_parc() {
        return qtd_parc;
    }

    public void setQtd_parc(int qtd_parc) {
        this.qtd_parc = qtd_parc;
    }

    public double getVl_tit() {
        return vl_tit;
    }

    public void setVl_tit(double vl_tit) {
        this.vl_tit = vl_tit;
    }

    public List<DadosC141> getdC141() {
        return dC141;
    }

    public void setdC141(List<DadosC141> dC141) {
        this.dC141 = dC141;
    }
}
