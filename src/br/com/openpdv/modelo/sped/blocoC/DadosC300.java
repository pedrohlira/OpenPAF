package br.com.openpdv.modelo.sped.blocoC;

import br.com.openpdv.modelo.sped.Bean;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

public class DadosC300 extends Bean {

    private String cod_mod;
    private String ser;
    private String sub;
    private int num_doc_ini;
    private int num_doc_fin;
    private Date dt_doc;
    private double vl_doc;
    private double vl_pis;
    private double vl_cofins;
    private String cod_cta;
    // subdados
    private List<DadosC310> dC310;
    private List<DadosC320> dC320;

    public DadosC300() {
        super("C300");
    }

    @Override
    public void gerar(FileWriter fw) throws Exception {
        super.gerar(fw);
        if (dC310 != null) {
            for (DadosC310 dados : dC310) {
                dados.gerar(fw);
            }
        }
        if (dC320 != null) {
            for (DadosC320 dados : dC320) {
                dados.gerar(fw);
            }
        }
    }

    public String getCod_cta() {
        return cod_cta;
    }

    public void setCod_cta(String cod_cta) {
        this.cod_cta = cod_cta;
    }

    public String getCod_mod() {
        return cod_mod;
    }

    public void setCod_mod(String cod_mod) {
        this.cod_mod = cod_mod;
    }

    public Date getDt_doc() {
        return dt_doc;
    }

    public void setDt_doc(Date dt_doc) {
        this.dt_doc = dt_doc;
    }

    public int getNum_doc_fin() {
        return num_doc_fin;
    }

    public void setNum_doc_fin(int num_doc_fin) {
        this.num_doc_fin = num_doc_fin;
    }

    public int getNum_doc_ini() {
        return num_doc_ini;
    }

    public void setNum_doc_ini(int num_doc_ini) {
        this.num_doc_ini = num_doc_ini;
    }

    public String getSer() {
        return ser;
    }

    public void setSer(String ser) {
        this.ser = ser;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public double getVl_cofins() {
        return vl_cofins;
    }

    public void setVl_cofins(double vl_cofins) {
        this.vl_cofins = vl_cofins;
    }

    public double getVl_doc() {
        return vl_doc;
    }

    public void setVl_doc(double vl_doc) {
        this.vl_doc = vl_doc;
    }

    public double getVl_pis() {
        return vl_pis;
    }

    public void setVl_pis(double vl_pis) {
        this.vl_pis = vl_pis;
    }

    public List<DadosC310> getdC310() {
        return dC310;
    }

    public void setdC310(List<DadosC310> dC310) {
        this.dC310 = dC310;
    }

    public List<DadosC320> getdC320() {
        return dC320;
    }

    public void setdC320(List<DadosC320> dC320) {
        this.dC320 = dC320;
    }
}
