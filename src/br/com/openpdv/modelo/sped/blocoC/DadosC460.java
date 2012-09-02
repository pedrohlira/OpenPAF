package br.com.openpdv.modelo.sped.blocoC;

import br.com.openpdv.modelo.sped.Bean;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

public class DadosC460 extends Bean {

    private String cod_mod;
    private String cod_sit;
    private int num_doc;
    private Date dt_doc;
    private double vl_doc;
    private double vl_pis;
    private double vl_cofins;
    private String cpf_cnpj;
    private String nom_adq;
    // subdados
    private List<DadosC470> dC470;

    public DadosC460() {
        super("C460");
    }

    @Override
    public void gerar(FileWriter fw) throws Exception {
        super.gerar(fw);
        if(dC470 != null){
            for(DadosC470 dados : dC470){
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

    public String getCod_sit() {
        return cod_sit;
    }

    public void setCod_sit(String cod_sit) {
        this.cod_sit = cod_sit;
    }

    public int getNum_doc() {
        return num_doc;
    }

    public void setNum_doc(int num_doc) {
        this.num_doc = num_doc;
    }

    public Date getDt_doc() {
        return dt_doc;
    }

    public void setDt_doc(Date dt_doc) {
        this.dt_doc = dt_doc;
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

    public double getVl_cofins() {
        return vl_cofins;
    }

    public void setVl_cofins(double vl_cofins) {
        this.vl_cofins = vl_cofins;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getNom_adq() {
        return nom_adq;
    }

    public void setNom_adq(String nom_adq) {
        this.nom_adq = nom_adq;
    }

    public List<DadosC470> getdC470() {
        return dC470;
    }

    public void setdC470(List<DadosC470> dC470) {
        this.dC470 = dC470;
    }
}
