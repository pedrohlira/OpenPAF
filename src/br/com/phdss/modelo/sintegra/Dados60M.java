package br.com.phdss.modelo.sintegra;

import java.io.FileWriter;
import java.util.Date;
import java.util.List;

public class Dados60M extends Tipo {

    private String subtipo;
    private Date data;
    private String serie;
    private int caixa;
    private String modelo;
    private int cooInicial;
    private int cooFinal;
    private int crz;
    private int cro;
    private double valorBruto;
    private double valorGeral;
    private String brancos;
    //subdados
    private List<Dados60A> dados60A;
    private List<Dados60D> dados60D;
    private List<Dados60I> dados60I;

    public Dados60M() {
        super(60);
        subtipo = "M";
        brancos = "";
    }

    @Override
    public void gerar(FileWriter fw) throws Exception {
        super.gerar(fw);
        if (dados60A != null) {
            for (Dados60A d : dados60A) {
                d.gerar(fw);
            }
        }
        if (dados60D != null) {
            for (Dados60D d : dados60D) {
                d.gerar(fw);
            }
        }
        if (dados60I != null) {
            for (Dados60I d : dados60I) {
                d.gerar(fw);
            }
        }
    }

    public String getBrancos() {
        return brancos;
    }

    public void setBrancos(String brancos) {
        this.brancos = "";
    }

    public int getCaixa() {
        return caixa;
    }

    public void setCaixa(int caixa) {
        this.caixa = caixa;
    }

    public int getCooFinal() {
        return cooFinal;
    }

    public void setCooFinal(int cooFinal) {
        this.cooFinal = cooFinal;
    }

    public int getCooInicial() {
        return cooInicial;
    }

    public void setCooInicial(int cooInicial) {
        this.cooInicial = cooInicial;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(String subtipo) {
        this.subtipo = "M";
    }

    public double getValorBruto() {
        return valorBruto * 100;
    }

    public void setValorBruto(double valorBruto) {
        this.valorBruto = valorBruto;
    }

    public double getValorGeral() {
        return valorGeral * 100;
    }

    public void setValorGeral(double valorGeral) {
        this.valorGeral = valorGeral;
    }

    public List<Dados60A> getDados60A() {
        return dados60A;
    }

    public void setDados60A(List<Dados60A> dados60A) {
        this.dados60A = dados60A;
    }

    public List<Dados60D> getDados60D() {
        return dados60D;
    }

    public void setDados60D(List<Dados60D> dados60D) {
        this.dados60D = dados60D;
    }

    public List<Dados60I> getDados60I() {
        return dados60I;
    }

    public void setDados60I(List<Dados60I> dados60I) {
        this.dados60I = dados60I;
    }
}
