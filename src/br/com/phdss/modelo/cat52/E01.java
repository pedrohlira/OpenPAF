package br.com.phdss.modelo.cat52;

import java.util.Date;

/**
 * Classe que representa o modelo E01 do CAT52.
 *
 * @author Pedro H. Lira
 */
public class E01 extends CabecalhoE {

    private String tipoEcf;
    private String marcaEcf;
    private String modeloEcf;
    private String versaoSB;
    private Date dataSB;
    private int sequencial;
    private String cnpj;
    private String comando;
    private int crzIni;
    private int crzFim;
    private Date dataIni;
    private Date dataFim;
    private String biblioteca;
    private String atoCotepe;

    public E01() {
        padrao = "E01";
    }

    public String getTipoEcf() {
        return tipoEcf;
    }

    public void setTipoEcf(String tipoEcf) {
        this.tipoEcf = tipoEcf;
    }

    public String getMarcaEcf() {
        return marcaEcf;
    }

    public void setMarcaEcf(String marcaEcf) {
        this.marcaEcf = marcaEcf;
    }

    public String getModeloEcf() {
        return modeloEcf;
    }

    public void setModeloEcf(String modeloEcf) {
        this.modeloEcf = modeloEcf;
    }

    public String getVersaoSB() {
        return versaoSB;
    }

    public void setVersaoSB(String versaoSB) {
        this.versaoSB = versaoSB;
    }

    public Date getDataSB() {
        return dataSB;
    }

    public void setDataSB(Date dataSB) {
        this.dataSB = dataSB;
    }

    public int getSequencial() {
        return sequencial;
    }

    public void setSequencial(int sequencial) {
        this.sequencial = sequencial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public int getCrzIni() {
        return crzIni;
    }

    public void setCrzIni(int crzIni) {
        this.crzIni = crzIni;
    }

    public int getCrzFim() {
        return crzFim;
    }

    public void setCrzFim(int crzFim) {
        this.crzFim = crzFim;
    }

    public Date getDataIni() {
        return dataIni;
    }

    public void setDataIni(Date dataIni) {
        this.dataIni = dataIni;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(String biblioteca) {
        this.biblioteca = biblioteca;
    }

    public String getAtoCotepe() {
        return atoCotepe;
    }

    public void setAtoCotepe(String atoCotepe) {
        this.atoCotepe = atoCotepe;
    }

}
