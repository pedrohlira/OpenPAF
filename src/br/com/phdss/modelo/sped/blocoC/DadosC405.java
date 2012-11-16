package br.com.phdss.modelo.sped.blocoC;

import br.com.phdss.modelo.sped.Bean;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

public class DadosC405 extends Bean {

    private Date dt_doc;
    private int cro;
    private int crz;
    private int num_coo_fin;
    private double gt_fin;
    private double vl_brt;
    // subdados
    private DadosC410 dC410;
    private List<DadosC420> dC420;
    private List<DadosC460> dC460;
    private List<DadosC490> dC490;

    public DadosC405() {
        super("C405");
    }

    @Override
    public void gerar(FileWriter fw) throws Exception {
        super.gerar(fw);
        if(dC410 != null){
            dC410.gerar(fw);
        }
        if (dC420 != null) {
            for (DadosC420 dados : dC420) {
                dados.gerar(fw);
            }
        }
        if (dC460 != null) {
            for (DadosC460 dados : dC460) {
                dados.gerar(fw);
            }
        }
        if (dC490 != null) {
            for (DadosC490 dados : dC490) {
                dados.gerar(fw);
            }
        }
    }

    public Date getDt_doc() {
        return dt_doc;
    }

    public void setDt_doc(Date dt_doc) {
        this.dt_doc = dt_doc;
    }

    public int getCro() {
        return cro;
    }

    public void setCro(int cro) {
        this.cro = cro;
    }

    public int getCrz() {
        return crz;
    }

    public void setCrz(int crz) {
        this.crz = crz;
    }

    public int getNum_coo_fin() {
        return num_coo_fin;
    }

    public void setNum_coo_fin(int num_coo_fin) {
        this.num_coo_fin = num_coo_fin;
    }

    public double getGt_fin() {
        return gt_fin;
    }

    public void setGt_fin(double gt_fin) {
        this.gt_fin = gt_fin;
    }

    public double getVl_brt() {
        return vl_brt;
    }

    public void setVl_brt(double vl_brt) {
        this.vl_brt = vl_brt;
    }

    public DadosC410 getdC410() {
        return dC410;
    }

    public void setdC410(DadosC410 dC410) {
        this.dC410 = dC410;
    }

    public List<DadosC420> getdC420() {
        return dC420;
    }

    public void setdC420(List<DadosC420> dC420) {
        this.dC420 = dC420;
    }

    public List<DadosC460> getdC460() {
        return dC460;
    }

    public void setdC460(List<DadosC460> dC460) {
        this.dC460 = dC460;
    }

    public List<DadosC490> getdC490() {
        return dC490;
    }

    public void setdC490(List<DadosC490> dC490) {
        this.dC490 = dC490;
    }
}
