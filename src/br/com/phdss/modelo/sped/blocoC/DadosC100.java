package br.com.phdss.modelo.sped.blocoC;

import br.com.phdss.modelo.sped.Bean;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

public class DadosC100 extends Bean {

    private String ind_oper;
    private String ind_emit;
    private String cod_part;
    private String cod_mod;
    private String cod_sit;
    private String ser;
    private int num_doc;
    private String chv_nfe;
    private Date dt_doc;
    private Date dt_e_s;
    private double vl_doc;
    private String ind_pgto;
    private double vl_desc;
    private double vl_abat_nt;
    private double vl_merc;
    private String ind_frt;
    private double vl_frt;
    private double vl_seg;
    private double vl_out_da;
    private double vl_bc_icms;
    private double vl_icms;
    private double vl_bc_icms_st;
    private double vl_icms_st;
    private double vl_ipi;
    private double vl_pis;
    private double vl_cofins;
    private double vl_pis_st;
    private double vl_cofins_st;
    // subdados
    private DadosC110 dC110;
    private DadosC140 dC140;
    private List<DadosC170> dC170;
    private List<DadosC190> dC190;

    public DadosC100() {
        super("C100");
    }

    @Override
    public void gerar(FileWriter fw) throws Exception {
        super.gerar(fw);
        if (dC110 != null) {
            dC110.gerar(fw);
        }
        if (dC140 != null) {
            dC140.gerar(fw);
        }
        if (dC170 != null) {
            for (DadosC170 dados : dC170) {
                dados.gerar(fw);
            }
        }
        if (dC190 != null) {
            for (DadosC190 dados : dC190) {
                dados.gerar(fw);
            }
        }
    }

    public String getInd_oper() {
        return ind_oper;
    }

    public void setInd_oper(String ind_oper) {
        this.ind_oper = ind_oper;
    }

    public String getInd_emit() {
        return ind_emit;
    }

    public void setInd_emit(String ind_emit) {
        this.ind_emit = ind_emit;
    }

    public String getCod_part() {
        return cod_part;
    }

    public void setCod_part(String cod_part) {
        this.cod_part = cod_part;
    }

    public String getCod_mod() {
        return cod_mod;
    }

    public void setCod_mod(String cod_mod) {
        this.cod_mod = cod_mod;
    }

    public String getCod_sit() {
        return cod_sit;
    }

    public void setCod_sit(String cod_sit) {
        this.cod_sit = cod_sit;
    }

    public String getSer() {
        return ser;
    }

    public void setSer(String ser) {
        this.ser = ser;
    }

    public int getNum_doc() {
        return num_doc;
    }

    public void setNum_doc(int num_doc) {
        this.num_doc = num_doc;
    }

    public String getChv_nfe() {
        return chv_nfe;
    }

    public void setChv_nfe(String chv_nfe) {
        this.chv_nfe = chv_nfe;
    }

    public Date getDt_doc() {
        return dt_doc;
    }

    public void setDt_doc(Date dt_doc) {
        this.dt_doc = dt_doc;
    }

    public Date getDt_e_s() {
        return dt_e_s;
    }

    public void setDt_e_s(Date dt_e_s) {
        this.dt_e_s = dt_e_s;
    }

    public double getVl_doc() {
        return vl_doc;
    }

    public void setVl_doc(double vl_doc) {
        this.vl_doc = vl_doc;
    }

    public String getInd_pgto() {
        return ind_pgto;
    }

    public void setInd_pgto(String ind_pgto) {
        this.ind_pgto = ind_pgto;
    }

    public double getVl_desc() {
        return vl_desc;
    }

    public void setVl_desc(double vl_desc) {
        this.vl_desc = vl_desc;
    }

    public double getVl_abat_nt() {
        return vl_abat_nt;
    }

    public void setVl_abat_nt(double vl_abat_nt) {
        this.vl_abat_nt = vl_abat_nt;
    }

    public double getVl_merc() {
        return vl_merc;
    }

    public void setVl_merc(double vl_merc) {
        this.vl_merc = vl_merc;
    }

    public String getInd_frt() {
        return ind_frt;
    }

    public void setInd_frt(String ind_frt) {
        this.ind_frt = ind_frt;
    }

    public double getVl_frt() {
        return vl_frt;
    }

    public void setVl_frt(double vl_frt) {
        this.vl_frt = vl_frt;
    }

    public double getVl_seg() {
        return vl_seg;
    }

    public void setVl_seg(double vl_seg) {
        this.vl_seg = vl_seg;
    }

    public double getVl_out_da() {
        return vl_out_da;
    }

    public void setVl_out_da(double vl_out_da) {
        this.vl_out_da = vl_out_da;
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

    public double getVl_bc_icms_st() {
        return vl_bc_icms_st;
    }

    public void setVl_bc_icms_st(double vl_bc_icms_st) {
        this.vl_bc_icms_st = vl_bc_icms_st;
    }

    public double getVl_icms_st() {
        return vl_icms_st;
    }

    public void setVl_icms_st(double vl_icms_st) {
        this.vl_icms_st = vl_icms_st;
    }

    public double getVl_ipi() {
        return vl_ipi;
    }

    public void setVl_ipi(double vl_ipi) {
        this.vl_ipi = vl_ipi;
    }

    public double getVl_pis() {
        return vl_pis;
    }

    public void setVl_pis(double vl_pis) {
        this.vl_pis = vl_pis;
    }

    public double getVl_cofins() {
        return vl_cofins;
    }

    public void setVl_cofins(double vl_cofins) {
        this.vl_cofins = vl_cofins;
    }

    public double getVl_pis_st() {
        return vl_pis_st;
    }

    public void setVl_pis_st(double vl_pis_st) {
        this.vl_pis_st = vl_pis_st;
    }

    public double getVl_cofins_st() {
        return vl_cofins_st;
    }

    public void setVl_cofins_st(double vl_cofins_st) {
        this.vl_cofins_st = vl_cofins_st;
    }

    public DadosC110 getdC110() {
        return dC110;
    }

    public void setdC110(DadosC110 dC110) {
        this.dC110 = dC110;
    }

    public DadosC140 getdC140() {
        return dC140;
    }

    public void setdC140(DadosC140 dC140) {
        this.dC140 = dC140;
    }

    public List<DadosC170> getdC170() {
        return dC170;
    }

    public void setdC170(List<DadosC170> dC170) {
        this.dC170 = dC170;
    }

    public List<DadosC190> getdC190() {
        return dC190;
    }

    public void setdC190(List<DadosC190> dC190) {
        this.dC190 = dC190;
    }
}
