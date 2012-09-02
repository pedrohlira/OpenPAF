package br.com.openpdv.modelo.sped.blocoC;

import br.com.openpdv.modelo.sped.Bean;
import java.io.FileWriter;
import java.util.List;

public class DadosC320 extends Bean {

    private String cst_icms;
    private int cfop;
    private double aliq_icms;
    private double vl_opr;
    private double vl_bc_icms;
    private double vl_icms;
    private double vl_red_bc;
    private String cod_obs;
    // subdados
    private List<DadosC321> dC321;

    public DadosC320() {
        super("C320");
    }

    @Override
    public void gerar(FileWriter fw) throws Exception {
        super.gerar(fw);
        if (dC321 != null) {
            for (DadosC321 dados : dC321) {
                dados.gerar(fw);
            }
        }
    }

    public double getAliq_icms() {
        return aliq_icms;
    }

    public void setAliq_icms(double aliq_icms) {
        this.aliq_icms = aliq_icms;
    }

    public int getCfop() {
        return cfop;
    }

    public void setCfop(int cfop) {
        this.cfop = cfop;
    }

    public String getCod_obs() {
        return cod_obs;
    }

    public void setCod_obs(String cod_obs) {
        this.cod_obs = cod_obs;
    }

    public String getCst_icms() {
        return cst_icms;
    }

    public void setCst_icms(String cst_icms) {
        this.cst_icms = cst_icms;
    }

    public double getVl_bc_icms() {
        return vl_bc_icms;
    }

    public void setVl_bc_icms(double vl_bc_icms) {
        this.vl_bc_icms = vl_bc_icms;
    }

    public double getVl_icms() {
        return vl_icms;
    }

    public void setVl_icms(double vl_icms) {
        this.vl_icms = vl_icms;
    }

    public double getVl_opr() {
        return vl_opr;
    }

    public void setVl_opr(double vl_opr) {
        this.vl_opr = vl_opr;
    }

    public double getVl_red_bc() {
        return vl_red_bc;
    }

    public void setVl_red_bc(double vl_red_bc) {
        this.vl_red_bc = vl_red_bc;
    }

    public List<DadosC321> getdC321() {
        return dC321;
    }

    public void setdC321(List<DadosC321> dC321) {
        this.dC321 = dC321;
    }
}
